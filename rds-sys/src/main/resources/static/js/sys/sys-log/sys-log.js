var ctx = "";//项目部署的工程名
var SysLogList;
var SysLogForm;

//其它组件

var SysLog = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/logs/ui/input.html";
        },
        listUI: function () {
            return ctx + "/sys/logs/ui/list.html";
        },
        importUI: function () {
            return ctx + "/sys/logs/ui/import.html";
        },
        list: function () {
            return ctx + "/sys/logs/";
        },
        save: function () {
            return ctx + "/sys/logs/save";
        },
        delete: function () {
            return ctx + "/sys/logs/delete";
        },
        get: function (id) {
            return ctx + "/sys/logs/" + id;
        },
        exportXls: function () {
            return ctx + "/sys/logs/exportXls";
        },
        importXls: function () {
            return ctx + "/sys/logs/importXls";
        }
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysLog.input.initComponent();
            SysLog.input.initForm();
        },
        initComponent: function () {
            SysLogForm = $("#SysLogForm");
        },
        initForm: function () {
            SysLogForm.form({
                url: SysLog.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (data.code == 200) {
                        SysLog.input.close();
                        SysLog.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysLogForm.submit();
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
                SysLog.list.add();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                SysLog.list.delete();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                SysLog.list.edit();
            }
        }, '-', {
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                SysLog.list.reload();
            }
        }],
        init: function (ct) {
            ctx = ct;
            SysLog.list.initComponent();
            SysLog.list.initList();
        },
        initComponent: function () {
            SysLogList = $("#SysLogList");
        },
        initList: function () {
            SysLogList.datagrid({
                url: SysLog.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 20,
                fit: true,
                rownumbers: true,
                toolbar: '#SysLogToolbar',//SysLog.list.toolbar,
                singleSelect: false,
                collapsible: false,
                striped: true,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键', hidden: true},
                    {field: 'time', title: '时间', width: '10%', hidden: false},
                    {field: 'ip', title: 'IP地址', width: '10%', hidden: false},
                    {field: 'loginName', title: '登录名', width: '10%', hidden: false},
                    {field: 'url', title: '访问链接', width: '22%', hidden: false},
                    {field: 'clazz', title: '访问的类', width: '21%', hidden: false},
                    {field: 'method', title: '访问的方法', width: '9%', hidden: false},
                    {field: 'moudle', title: '模块', width: '9%', hidden: false},
                    {field: 'function', title: '功能', width: '8%', hidden: false}
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (index, row) {
                    $(this).datagrid("unselectAll");
                    $(this).datagrid("selectRow", index);
                },
                //替换分页请求参数
                onBeforeLoad: function (params) {
                    params.page = params.page
                    params.size = params.rows
                    delete params.rows
                },
                loadFilter: function (data) {
                    if (data.data) {
                        //返回结果进行封装
                        return {total: data.data.total, rows: data.data.result};
                    } else {
                        return data;
                    }
                }
            });
        },
        getSelectionsIds: function () {
            var sels = SysLogList.datagrid("getSelections");
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
                href: SysLog.URL.inputUI(),
                title: '新增日志',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysLog.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysLog.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                }
            });
        },
        //改
        edit: function () {
            var sels = SysLogList.datagrid("getSelections");
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
                href: SysLog.URL.inputUI(),
                title: '修改日志',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysLog.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysLog.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //方案一：使用Form的load去load数据
                    //SysLogForm.form("load", SysLog.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SysLogForm.form("load",sels[0]);
                    //方案三：使用Ajax请求数据
                    $.ajax({
                        type: "GET",
                        url: SysLog.URL.get(sels[0].id),
                        success: function (data) {
                            if (data.code == 200) {
                                SysLogForm.form("load", data.data);
                            }
                        }
                    });
                }
            });

        },
        //删
        delete: function () {
            var ids = SysLog.list.getSelectionsIds();
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
                            url: SysLog.URL.delete(),
                            data: {ids: ids},
                            success: function () {
                                SysLog.list.reload();
                                //如果不清空，删除还可以编辑BUG
                                SysLog.list.clearSelectionsAndChecked();
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysLogList.datagrid("reload");
        },
        clearSelectionsAndChecked: function () {
            SysLogList.datagrid("clearChecked");
            SysLogList.datagrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysLog']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysLogList.datagrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysLog']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysLog']").each(function (i) {
                $(this).val("");
            });
        },
        //导出excel
        exportXls: function () {
            var ids = SysLog.list.getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            $.messager.confirm({
                title: '确认提示框',
                msg: '你确定要导出Excel吗？',
                fn: function (r) {
                    if (r) {
                        $.ajax({
                            type: "post",
                            url: SysLog.URL.exportXls(),
                            data: {ids: ids},
                            success: function (data) {
                                if (data.code == 200) {
                                    window.location.href = "/sys/files/download/xls?fileName=" + data.data;
                                }
                            }
                        });
                    }
                }
            });
        },
        //导入excel
        importXls: function () {
            $("<div></div>").dialog({
                id: 'dialog',
                href: SysLog.URL.importUI(),
                title: '导入日志',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        saveXls();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        cancelXls();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                }
            });
        }
    }
}
