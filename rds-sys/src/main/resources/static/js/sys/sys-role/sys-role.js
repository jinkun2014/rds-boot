var ctx = "";//项目部署的工程名
var SysRoleList;
var SysRoleForm;

//其它组件
var SysResourceTree; //资源树

var SysRole = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/role/ui/input.html";
        },
        listUI: function () {
            return ctx + "/sys/role/ui/list.html";
        },
        resourceUI: function () {
            return ctx + "/sys/role/ui/resource.html";
        },
        list: function () {
            return ctx + "/sys/role/list";
        },
        save: function () {
            return ctx + "/sys/role/save";
        },
        delete: function () {
            return ctx + "/sys/role/delete";
        },
        get: function (id) {
            return ctx + "/sys/role/" + id;
        },
        resourceTree: function () {
            return ctx + "/sys/resource/tree";
        },
        getResources: function (id) {
            return ctx + "/sys/role/" + id + "/resources";
        },
        saveResources: function (id) {
            return ctx + "/sys/role/" + id + "/resources";
        }
    },
    resource: {
        init: function (ct, id) {
            ctx = ct;
            SysRole.resource.initComponent();
            SysRole.resource.initList(id);
        },
        initComponent: function () {
            SysResourceTree = $("#SysResourceTree");
        },
        initList: function (id) {
            SysResourceTree.tree({
                method: 'get',
                url: SysRole.URL.resourceTree(),
                checkbox: function (node) {
                    return true;
                },
                loadFilter: function (data, parent) {
                    if (checkResp(data)) {
                        return data.data;
                    }
                },
                onLoadSuccess: function (node, data) {

                    //回显资源列表
                    $.ajax({
                        type: "GET",
                        url: SysRole.URL.getResources(id),
                        success: function (data) {
                            if (checkResp(data)) {
                                //回显已有的权限
                                var root = SysResourceTree.tree('getRoots'); // 取到树的根节点
                                for (var i in root) {
                                    for (var j in data.data) {
                                        SysRole.resource.checkTreeNode(root, data.data[j]);
                                    }
                                }
                            }
                        }
                    });
                }
            });
        },
        save: function () {
            var nodes = SysResourceTree.tree('getChecked', ['checked', 'indeterminate']);
            if (nodes != null) {
                var ids = [];
                for (var i in nodes) {
                    ids.push(nodes[i].id);
                }
                ids = ids.join(",");

                $.ajax({
                    type: "POST",
                    url: SysRole.URL.saveResources(SysRoleList.datagrid("getSelections")[0].id),
                    data: {ids: ids},
                    success: function (data) {
                        if (checkResp(data)) {
                            SysRole.resource.close();
                        }
                    }
                });
            }
            SysRole.resource.close();
        },
        // 递归遍历所有节点并选中
        checkTreeNode: function (children, id) {
            if(children){
                for (var i = 0; i < children.length; i++) {
                    if (children[i].id == id) {
                        var node = SysResourceTree.tree('find', children[i].id).target;
                        if (SysResourceTree.tree('isLeaf', node)) {
                            SysResourceTree.tree('check', node); // 选中树叶子节点
                        }
                        break;
                    } else {
                        if (children[i].children != null) {
                            SysRole.resource.checkTreeNode(children[i].children, id); // 没有找到则接着遍历
                        }
                    }
                }
            }
        },
        close: function () {
            $("#dialog").dialog('close');
        }
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysRole.input.initComponent();
            SysRole.input.initForm();
        },
        initComponent: function () {
            SysRoleForm = $("#SysRoleForm");
        },
        initForm: function () {
            SysRoleForm.form({
                url: SysRole.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (data.code == 200) {
                        SysRole.input.close();
                        SysRole.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysRoleForm.submit();
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
                SysRole.list.add();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                SysRole.list.delete();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                SysRole.list.edit();
            }
        }, '-', {
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                SysRole.list.reload();
            }
        }],
        init: function (ct) {
            ctx = ct;
            SysRole.list.initComponent();
            SysRole.list.initList();
        },
        initComponent: function () {
            SysRoleList = $("#SysRoleList");
        },
        initList: function () {
            SysRoleList.datagrid({
                url: SysRole.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 30,
                fit:true,
                toolbar: '#SysRoleToolbar',//SysRole.list.toolbar,
                singleSelect: false,
                collapsible: false,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键id', hidden: true},
                    {field: 'name', title: '角色名', width: '13.571%', hidden: false},
                    {field: 'seq', title: '排序号', width: '13.571%', hidden: false},
                    {field: 'description', title: '简介', width: '13.571%', hidden: false},
                    {field: 'status', title: '状态', width: '13.571%', hidden: false},
                    {field: 'delFlag', title: '删除标记', width: '13.571%', hidden: false},
                    {field: 'updateTime', title: '更新时间', width: '13.571%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '13.571%', hidden: false},
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (index, row) {
                    SysRoleList.datagrid("unselectAll");
                    SysRoleList.datagrid("selectRow", index);
                },
                //替换分页请求参数
                onBeforeLoad: function (params) {
                    params.pageNo = params.page;
                    params.pageSize = params.rows;
                    delete params.rows;
                    delete params.page;
                },
                loadFilter: function (data) {
                    if (checkResp(data)) {
                        //返回结果进行封装
                        return {total: data.data.total, rows: data.data.list};
                    }
                }
            });
        },
        getSelectionsIds: function () {
            var sels = SysRoleList.datagrid("getSelections");
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
                href: SysRole.URL.inputUI(),
                title: '新增角色',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysRole.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysRole.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                }
            });
        },
        //改
        edit: function () {
            var sels = SysRoleList.datagrid("getSelections");
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
                href: SysRole.URL.inputUI(),
                title: '修改角色',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysRole.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysRole.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //方案一：使用Form的load去load数据
                    //SysRoleForm.form("load", SysRole.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SysRoleForm.form("load",sels[0]);
                    //方案三：使用Ajax请求数据
                    $.ajax({
                        type: "GET",
                        url: SysRole.URL.get(sels[0].id),
                        success: function (data) {
                            if (checkResp(data)) {
                                SysRoleForm.form("load", data.data);
                            }
                        }
                    });
                }
            });

        },
        //删
        delete: function () {
            var ids = SysRole.list.getSelectionsIds();
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
                            url: SysRole.URL.delete(),
                            data:{ids:ids},
                            success: function (data) {
                                if(checkResp(data)){
                                    SysRole.list.reload();
                                    //如果不清空，删除还可以编辑BUG
                                    SysRole.list.clearSelectionsAndChecked();
                                }
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysRoleList.datagrid("reload");
        },
        clearSelectionsAndChecked: function () {
            SysRoleList.datagrid("clearChecked");
            SysRoleList.datagrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysRole']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysRoleList.datagrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysRole']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysRole']").each(function (i) {
                $(this).val("");
            });
        },
        //授权
        authorize: function () {
            var sels = SysRoleList.datagrid("getSelections");
            if (sels.length < 1) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            if (sels.length > 1) {
                $.messager.alert("对话框", "只能选择一行");
                return;
            }

            $("<div></div>").dialog({
                id: 'dialog',
                href: SysRole.URL.resourceUI(),
                title: '修改角色',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysRole.resource.save();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysRole.resource.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    SysRole.resource.init(ctx, sels[0].id);
                }
            });
        }
    }
}
