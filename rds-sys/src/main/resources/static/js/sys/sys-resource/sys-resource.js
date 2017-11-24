var ctx = "";//项目部署的工程名
var SysResourceList;
var SysResourceForm;

//其它组件
var parentResource;//上级资源
var iconText;//图片输入框

var SysResource = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/resources/ui/input.html";
        },
        listUI: function () {
            return ctx + "/sys/resources/ui/list.html";
        },
        iconUI: function () {
            return ctx + "/sys/resources/ui/icon.html";
        },
        list: function () {
            return ctx + "/sys/resources/";
        },
        save: function () {
            return ctx + "/sys/resources/save";
        },
        delete: function () {
            return ctx + "/sys/resources/delete";
        },
        get: function (id) {
            return ctx + "/sys/resources/" + id;
        },
        tree: function () {
            return ctx + "/sys/resources/tree";
        }
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysResource.input.initComponent();
            SysResource.input.initForm();
        },
        initComponent: function () {
            SysResourceForm = $("#SysResourceForm");
            parentResource = $("#parentResource");
            iconText = $("input[name='icon']");
        },
        initForm: function () {
            SysResourceForm.form({
                url: SysResource.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (data.code == 200) {
                        SysResource.input.close();
                        SysResource.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysResourceForm.submit();
        },
        close: function () {
            $("#dialog").dialog('close');
        },
        chooseIcon: function () {
            $("<div></div>").dialog({
                id: 'iconDialog',
                title: '选择图标',
                width: 605,
                height: 400,
                modal: true,
                href: SysResource.URL.iconUI(),
                cache: false,
                onClose: function () {
                    $("#iconDialog").dialog('destroy');
                }
            });
        },
        setIcon: function (value) {
            iconText.textbox("setValue", value);
            $("#iconDialog").dialog('close');
        }
    },
    list: {
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                SysResource.list.add();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                SysResource.list.delete();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                SysResource.list.edit();
            }
        }, '-', {
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                SysResource.list.reload();
            }
        }],
        init: function (ct) {
            ctx = ct;
            SysResource.list.initComponent();
            SysResource.list.initList();
        },
        initComponent: function () {
            SysResourceList = $("#SysResourceList");
        },
        initList: function () {
            SysResourceList.treegrid({
                url: SysResource.URL.list(),
                method: 'get',
                fit:true,
                pagination: true,
                pageSize: 30,
                toolbar: '#SysResourceToolbar',//SysResource.list.toolbar,
                singleSelect: false,
                collapsible: false,
                idField: 'id',
                treeField: 'name',
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键', hidden: true},
                    {field: 'name', title: '资源名称', width: '10%', hidden: false},
                    {field: 'url', title: '资源路径', width: '10%', hidden: false},
                    {
                        field: 'openMode',
                        title: '打开方式',
                        width: '7.917%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == '0' ? 'ajax' : 'iframe';
                        }
                    },
                    {field: 'description', title: '资源介绍', width: '7.917%', hidden: false},
                    {
                        field: 'icon', title: '资源图标', width: '7.917%', hidden: false,
                        formatter: function (value, row, index) {
                            if (value) {
                                return '<img src="' + value + '" style="margin:auto;width: 20px;height: 20px;"/>';
                            }
                            return value;
                        }
                    },
                    {field: 'seq', title: '排序', width: '7.917%', hidden: false},
                    {
                        field: 'status',
                        title: '状态',
                        width: '7.917%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == 0 ? '启用' : '停用';
                        }
                    },
                    {
                        field: 'resourceType',
                        title: '资源类别',
                        width: '7.917%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == 0 ? '菜单' : '按钮';
                        }
                    },
                    {field: 'delFlag', title: '删除标记', width: '7.917%', hidden: true},
                    {field: 'updateTime', title: '更新时间', width: '9%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '9%', hidden: false},
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (row) {
                    $(this).treegrid("unselectAll");
                    $(this).treegrid("selectRow", row.id);
                },
                //替换分页请求参数
                onBeforeLoad: function (row, params) {
                    params.page = params.page
                    params.size = params.rows
                    delete params.rows
                },
                loadFilter: function (data) {
                    //将格式转换为TreeGrid需要的数据格式
                    if (data.data) {
                        var jsonStr = JSON.stringify(data.data); //可以将json对象转换成json字符串
                        jsonStr = jsonStr.replace(new RegExp("pid", "gm"), "_parentId");
                        jsonStr = jsonStr.replace(new RegExp("result", "gm"), "rows");
                        return JSON.parse(jsonStr); //可以将json字符串转换成json对象
                    } else {
                        return data;
                    }
                }
            });
        },
        getSelectionsIds: function () {
            var sels = SysResourceList.treegrid("getSelections");
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
                href: SysResource.URL.inputUI(),
                title: '新增资源',
                width: 600,
                height: 500,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysResource.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysResource.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //上级资源
                    parentResource.combotree({
                        url: SysResource.URL.tree(),
                        method: 'get',
                        panelHeight: 'auto',
                        loadFilter: function (data, parent) {
                            if (data.code == 200) {
                                return data.data;
                            }
                        }
                    });
                }
            });
        },
        //改
        edit: function () {
            var sels = SysResourceList.treegrid("getSelections");
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
                href: SysResource.URL.inputUI(),
                title: '修改资源',
                width: 600,
                height: 500,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysResource.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysResource.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //方案一：使用Form的load去load数据
                    //SysResourceForm.form("load", SysResource.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SysResourceForm.form("load",sels[0]);
                    //方案三：使用Ajax请求数据
                    $.ajax({
                        type: "GET",
                        url: SysResource.URL.get(sels[0].id),
                        success: function (data) {
                            if (data.code == 200) {
                                SysResourceForm.form("load", data.data);

                                //上级资源
                                parentResource.combotree({
                                    url: SysResource.URL.tree(),
                                    method: 'get',
                                    panelHeight: 'auto',
                                    loadFilter: function (data, parent) {
                                        if (data.code == 200) {
                                            return data.data;
                                        }
                                    },
                                    onLoadSuccess: function () {
                                        parentResource.combotree('setValue', data.data.pid);
                                    }
                                });
                            }
                        }
                    });
                }
            });

        },
        //删
        delete: function () {
            var ids = SysResource.list.getSelectionsIds();
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
                            url: SysResource.URL.delete(),
                            data:{ids:ids},
                            success: function () {
                                SysResource.list.reload();
                                //如果不清空，删除还可以编辑BUG
                                SysResource.list.clearSelectionsAndChecked();
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysResourceList.treegrid("reload");
        },
        clearSelectionsAndChecked: function () {
            SysResourceList.treegrid("clearChecked");
            SysResourceList.treegrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysResource']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysResourceList.treegrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysResource']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysResource']").each(function (i) {
                $(this).val("");
            });
        },
        //折叠
        collapseAll: function () {
            var roots = SysResourceList.treegrid("getRoots");
            for (var i in roots) {
                SysResourceList.treegrid("collapseAll", roots[i].id);
            }
        },
        //展开
        expandAll: function () {
            var roots = SysResourceList.treegrid("getRoots");
            for (var i in roots) {
                SysResourceList.treegrid("expandAll", roots[i].id);
            }
        }
    }
}
