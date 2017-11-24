var ctx = "";//项目部署的工程名
var SysOrgList;
var SysOrgForm;

//其它组件
var parentOrg;//上级部门

var SysOrg = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/org/ui/input.html";
        },
        listUI: function () {
            return ctx + "/sys/org/ui/list.html";
        },
        list: function () {
            return ctx + "/sys/org/list";
        },
        tree: function () {
            return ctx + "/sys/org/tree";
        },
        save: function () {
            return ctx + "/sys/org/save";
        },
        delete: function () {
            return ctx + "/sys/org/delete";
        },
        get: function (id) {
            return ctx + "/sys/org/" + id;
        }
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysOrg.input.initComponent();
            SysOrg.input.initForm();
        },
        initComponent: function () {
            SysOrgForm = $("#SysOrgForm");
            parentOrg = $("#parentOrg");
        },
        initForm: function () {
            SysOrgForm.form({
                url: SysOrg.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (checkResp(data)) {
                        SysOrg.input.close();
                        SysOrg.list.reload();
                    }else {
                        errorAlert(data.message);
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysOrgForm.submit();
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
                SysOrg.list.add();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                SysOrg.list.delete();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                SysOrg.list.edit();
            }
        }, '-', {
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                SysOrg.list.reload();
            }
        }],
        init: function (ct) {
            ctx = ct;
            SysOrg.list.initComponent();
            SysOrg.list.initList();
        },
        initComponent: function () {
            SysOrgList = $("#SysOrgList");
        },
        initList: function () {
            SysOrgList.treegrid({
                url: SysOrg.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 30,
                toolbar: '#SysOrgToolbar',//SysOrg.list.toolbar,
                singleSelect: false,
                collapsible: false,
                fit: true,
                idField: 'id',
                treeField: 'name',
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键id', hidden: true},
                    {field: 'name', title: '组织名', width: '13.571%', hidden: false},
                    {field: 'address', title: '地址', width: '13.571%', hidden: false},
                    {field: 'code', title: '编号', width: '13.571%', hidden: false},
                    {field: 'icon', title: '图标', width: '13.571%', hidden: false},
                    {field: 'pid', title: '父级主键', width: '13.571%', hidden: true},
                    {field: 'seq', title: '排序', width: '13.571%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '13.571%', hidden: false},
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (row) {
                    SysOrgList.treegrid("unselectAll");
                    SysOrgList.treegrid("selectRow", row.id);
                },
                //替换分页请求参数
                onBeforeLoad: function (row, params) {
                    params.pageNo = params.page;
                    params.pageSize = params.rows;
                    delete params.rows
                },
                loadFilter: function (data) {
                    //将格式转换为TreeGrid需要的数据格式
                    if (checkResp(data)) {
                        var jsonStr = JSON.stringify({total: data.data.total, rows: data.data.list}); //可以将json对象转换成json字符串
                        jsonStr = jsonStr.replace(new RegExp("pid", "gm"), "_parentId");
                        jsonStr = jsonStr.replace(new RegExp("result", "gm"), "rows");
                        return JSON.parse(jsonStr); //可以将json字符串转换成json对象
                    }
                    errorAlert(data.message);
                }
            });
        },
        getSelectionsIds: function () {
            var sels = SysOrgList.treegrid("getSelections");
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
                href: SysOrg.URL.inputUI(),
                title: '新增组织机构',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysOrg.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysOrg.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //加载部门树
                    parentOrg.combotree({
                        url: SysOrg.URL.tree(),
                        method: 'get',
                        panelHeight: 'auto',
                        loadFilter: function (data, parent) {
                            if (checkResp(data)) {
                                return data.data;
                            }
                        }
                    });
                }
            });
        },
        //改
        edit: function () {
            var sels = SysOrgList.treegrid("getSelections");
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
                href: SysOrg.URL.inputUI(),
                title: '修改组织机构',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysOrg.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysOrg.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //方案一：使用Form的load去load数据
                    //SysOrgForm.form("load", SysOrg.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SysOrgForm.form("load",sels[0]);
                    //方案三：使用Ajax请求数据
                    $.ajax({
                        type: "GET",
                        url: SysOrg.URL.get(sels[0].id),
                        success: function (data) {
                            if (checkResp(data)) {
                                SysOrgForm.form("load", data.data);
                                parentOrg.combotree({
                                    url: SysOrg.URL.tree(),
                                    method: 'get',
                                    panelHeight: 'auto',
                                    loadFilter: function (data, parent) {
                                        if (checkResp(data)) {
                                            return data.data;
                                        }
                                    },
                                    onLoadSuccess: function () {
                                        parentOrg.combotree('setValue', data.data.pid);
                                    }
                                });
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
            var ids = SysOrg.list.getSelectionsIds();
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
                            url: SysOrg.URL.delete(),
                            data: {ids: ids},
                            success: function (data) {
                                if (checkResp(data)) {
                                    SysOrg.list.reload();
                                    //如果不清空，删除还可以编辑BUG
                                    SysOrg.list.clearSelectionsAndChecked();
                                }
                                errorAlert(data.message);
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysOrgList.treegrid("reload");
        },
        clearSelectionsAndChecked: function () {
            SysOrgList.treegrid("clearChecked");
            SysOrgList.treegrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysOrg']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysOrgList.treegrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysOrg']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysOrg']").each(function (i) {
                $(this).val("");
            });
        }
    }
}
