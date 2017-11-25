var ctx = "";//项目部署的工程名
var SysIconList;
var SysIconForm;

//其它组件

var SysIcon = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/icon/ui/input.html";
        },
        listUI: function () {
            return ctx + "/sys/icon/ui/list.html";
        },
        list: function () {
            return ctx + "/sys/icon/list";
        },
        save: function () {
            return ctx + "/sys/icon/save";
        },
        delete: function () {
            return ctx + "/sys/icon/delete";
        },
        get: function (id) {
            return ctx + "/sys/icon/" + id;
        },
        load: function () {
            return ctx + "/sys/icon/load";
        }
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysIcon.input.initComponent();
            SysIcon.input.initForm();
        },
        initComponent: function () {
            SysIconForm = $("#SysIconForm");
        },
        initForm: function () {
            SysIconForm.form({
                url: SysIcon.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (checkResp(data)) {
                        SysIcon.input.close();
                        SysIcon.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysIconForm.submit();
        },
        close: function () {
            $("#dialog").dialog('close');
        }
    },
    list: {
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                SysIcon.list.add();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                SysIcon.list.delete();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                SysIcon.list.edit();
            }
        }, '-', {
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                SysIcon.list.reload();
            }
        }],
        init: function (ct) {
            ctx = ct;
            SysIcon.list.initComponent();
            SysIcon.list.initList();
        },
        initComponent: function () {
            SysIconList = $("#SysIconList");
        },
        initList: function () {
            SysIconList.datagrid({
                url: SysIcon.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 10,
                fit: true,
                toolbar: '#SysIconToolbar',//SysIcon.list.toolbar,
                singleSelect: false,
                collapsible: false,
                striped: true,
                rownumbers: true,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键', hidden: true},
                    {field: 'name', title: '名称', width: '20%', hidden: false},
                    {field: 'url', title: '链接', width: '20%', hidden: false},
                    {field: 'type', title: '类型', width: '20%', hidden: false},
                    {field: 'updateTime', title: '更新时间', width: '18%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '18%', hidden: false}
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (index, row) {
                    $(this).datagrid("unselectAll");
                    $(this).datagrid("selectRow", index);
                },
                //替换分页请求参数
                onBeforeLoad: function (params) {
                    params.pageNo = params.page;
                    params.pageSize = params.rows;
                    delete params.rows
                },
                loadFilter: function (data) {
                    //{ "data":{ "list":[ ], "totalRecordCount":102 }, "message":"操作成功！", "resultCode":"SUCCESS" }
                    if (checkResp(data)) {
                        //返回结果进行封装
                        return {total: data.data.total, rows: data.data.list};
                    }
                    //其它情况
                    errorAlert(data.message);
                }
            });
        },
        getSelectionsIds: function () {
            var sels = SysIconList.datagrid("getSelections");
            var ids = [];
            for (var i in sels) {
                ids.push(sels[i].id);
            }
            ids = ids.join(",");
            return ids;
        },
        //增
        add: function () {
            $("<div></div>").dialog({
                id: 'dialog',
                href: SysIcon.URL.inputUI(),
                title: '新增图标',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysIcon.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysIcon.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                }
            });
        },
        //改
        edit: function () {
            var sels = SysIconList.datagrid("getSelections");
            if (sels.length < 1) {
                $.messager.alert("警告", "至少选择一行数据进行编辑");
                return;
            }

            if (sels.length > 1) {
                $.messager.alert("警告", "只能选择一行数据进行编辑");
                return;
            }

            $("<div></div>").dialog({
                id: 'dialog',
                href: SysIcon.URL.inputUI(),
                title: '修改图标',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysIcon.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysIcon.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //方案一：使用Form的load去load数据
                    //SysIconForm.form("load", SysIcon.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SysIconForm.form("load",sels[0]);
                    //方案三：使用Ajax请求数据
                    $.ajax({
                        type: "GET",
                        url: SysIcon.URL.get(sels[0].id),
                        success: function (data) {
                            if (checkResp(data)) {
                                SysIconForm.form("load", data.data);
                            } else {
                                errorAlert(data.message);
                            }
                        }
                    });
                }
            });

        },
        //删
        delete: function () {
            var ids = SysIcon.list.getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            $.messager.confirm({
                title: '确认提示框',
                msg: '你确定要删除吗？',
                fn: function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: SysIcon.URL.delete(),
                            data: {ids: ids},
                            success: function () {
                                SysIcon.list.reload();
                                //如果不清空，删除还可以编辑BUG
                                SysIcon.list.clearSelectionsAndChecked();
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysIconList.datagrid("reload");
        },
        clearSelectionsAndChecked: function () {
            SysIconList.datagrid("clearChecked");
            SysIconList.datagrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysIcon']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysIconList.datagrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysIcon']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysIcon']").each(function (i) {
                $(this).val("");
            });
        },
        //初始化
        load: function () {
            $.messager.confirm({
                title: '确认提示框',
                msg: '初始化必须在开发模式使用，会删除数据库的数据，并重新扫描/static/images下的图标入库，你确定要初始化吗？',
                fn: function (r) {
                    if (r) {
                        $.ajax({
                            type: "get",
                            url: SysIcon.URL.load(),
                            success: function (data) {
                                if (checkResp(data)) {
                                    SysIcon.list.reload();
                                } else {
                                    errorAlert(data.message);
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}
