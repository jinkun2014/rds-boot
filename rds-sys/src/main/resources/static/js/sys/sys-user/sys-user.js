var ctx = "";//项目部署的工程名
var SysUserList;
var SysUserForm;

//其它组件
var SysUserOrgTree;// 组织构架树
var SysUserOrg;//组织架构树
var SysUserRole;//角色构架树

var SysUser = {
    URL: {
        inputUI: function () {
            return ctx + "/sys/user/ui/input.html";
        },
        listUI: function () {
            return ctx + "/sys/user/ui/list.html";
        },
        list: function () {
            return ctx + "/sys/user/list";
        },
        save: function () {
            return ctx + "/sys/user/save";
        },
        delete: function () {
            return ctx + "/sys/user/delete";
        },
        get: function (id) {
            return ctx + "/sys/user/" + id;
        },
        orgTree: function () {
            return ctx + "/sys/org/tree";
        },
        roleListAll: function () {
            return ctx + "/sys/roles/all";
        },
    },
    input: {
        init: function (ct) {
            ctx = ct;
            SysUser.input.initComponent();
            SysUser.input.initForm();
        },
        initComponent: function () {
            SysUserForm = $("#SysUserForm");
            SysUserOrg = $("#SysUserOrg");
            SysUserRole = $("#SysUserRole");
        },
        initForm: function () {
            SysUserForm.form({
                url: SysUser.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (checkResp(data)) {
                        SysUser.input.close();
                        SysUser.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            SysUserForm.submit();
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
                SysUser.list.add();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                SysUser.list.delete();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                SysUser.list.edit();
            }
        }, '-', {
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                SysUser.list.reload();
            }
        }],
        init: function (ct) {
            ctx = ct;
            SysUser.list.initComponent();
            SysUser.list.initOrgTree();
            SysUser.list.initList();
        },
        initComponent: function () {
            SysUserList = $("#SysUserList");
            SysUserOrgTree = $('#SysUserOrgTree');
        },
        initOrgTree: function () {
            SysUserOrgTree.tree({
                method: 'get',
                url: SysUser.URL.orgTree(),
                loadFilter: function (data, parent) {
                    if (checkResp(data)) {
                        return data.data;
                    }
                },
                onSelect: function (node) {
                    if (SysUserOrgTree.tree('isLeaf', node.target)) {
                        SysUserList.datagrid({
                            queryParams: {
                                orgId: node.id
                            }
                        });
                    }
                }
            });
        },
        initList: function () {
            SysUserList.datagrid({
                url: SysUser.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 30,
                fit:true,
                toolbar: '#SysUserToolbar',//SysUser.list.toolbar,
                singleSelect: false,
                collapsible: false,
                columns: [[
                    {field: 'ck', checkbox: true},
                    {field: 'id', title: '主键id', hidden: true},
                    {field: 'loginName', title: '登录名', width: '10%', hidden: false},
                    {field: 'name', title: '用户名', width: '10%', hidden: false},
                    {
                        field: 'sex',
                        title: '性别',
                        width: '10%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == 0 ? '男' : '女';
                        }
                    },
                    {field: 'age', title: '年龄', width: '10%', hidden: false},
                    {field: 'phone', title: '手机号', width: '14%', hidden: false},
                    {
                        field: 'userType',
                        title: '用户类别',
                        width: '10%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == 0 ? '用户' : '管理员';
                        }
                    },
                    {
                        field: 'status',
                        title: '用户状态',
                        width: '10%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == 0 ? '启用' : '停用';
                        }
                    },
                    {field: 'updateTime', title: '更新时间', width: '12%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '12%', hidden: false},
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (index, row) {
                    SysUserList.datagrid("unselectAll");
                    SysUserList.datagrid("selectRow", index);
                },
                //替换分页请求参数
                onBeforeLoad: function (params) {
                    params.pageNo = params.page;
                    params.pageSize = params.rows;
                    delete params.rows
                },
                loadFilter: function (data) {
                    if (checkResp(data)) {
                        //返回结果进行封装
                        return {total: data.data.total, rows: data.data.list};
                    } else {
                        return data;
                    }
                }
            });
        },
        getSelectionsIds: function () {
            var sels = SysUserList.datagrid("getSelections");
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
                href: SysUser.URL.inputUI(),
                title: '新增用户',
                width: 600,
                height: 500,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysUser.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysUser.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //组织信息 虽然数据库设计是多个组织，但目前前端只实现单选
                    SysUserOrg.combotree({
                        url: SysUser.URL.orgTree(),
                        method: 'get',
                        panelHeight: 'auto',
                        loadFilter: function (data, parent) {
                            if (checkResp(data)) {
                                return data.data;
                            }
                        },
                        //选择树节点触发事件
                        onSelect: function (node) {
                            //返回树对象
                            var tree = $(this).tree;
                            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
                            var isLeaf = tree('isLeaf', node.target);
                            if (!isLeaf) {
                                //清除选中
                                SysUserOrg.treegrid("unselect");
                            }
                        }
                    });

                    //角色信息
                    SysUserRole.combobox({
                        url: SysUser.URL.roleListAll(),
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
            var sels = SysUserList.datagrid("getSelections");
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
                href: SysUser.URL.inputUI(),
                title: '修改用户',
                width: 600,
                height: 500,
                modal: true,
                cache: false,
                buttons: [{
                    text: '保存', iconCls: 'icon-save', handler: function () {
                        SysUser.input.submitForm();
                    }
                }, {
                    text: '取消', iconCls: 'icon-cancel', handler: function () {
                        SysUser.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //方案一：使用Form的load去load数据
                    //SysUserForm.form("load", SysUser.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SysUserForm.form("load",sels[0]);
                    //方案三：使用Ajax请求数据
                    $.ajax({
                        type: "GET",
                        url: SysUser.URL.get(sels[0].id),
                        success: function (data) {
                            if (checkResp(data)) {
                                SysUserForm.form("load", data.data);

                                //组织信息
                                SysUserOrg.combotree({
                                    url: SysUser.URL.orgTree(),
                                    method: 'get',
                                    panelHeight: 'auto',
                                    loadFilter: function (data, parent) {
                                        if (checkResp(data)) {
                                            return data.data;
                                        }
                                    },
                                    onLoadSuccess: function () {
                                        SysUserOrg.combotree('setValue', data.data.orgIds);
                                    },
                                    //选择树节点触发事件
                                    onSelect: function (node) {
                                        //返回树对象
                                        var tree = $(this).tree;
                                        //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
                                        var isLeaf = tree('isLeaf', node.target);
                                        if (!isLeaf) {
                                            //清除选中
                                            SysUserOrg.treegrid("unselect");
                                        }
                                    }
                                });

                                //角色信息
                                SysUserRole.combobox({
                                    url: SysUser.URL.roleListAll(),
                                    method: 'get',
                                    panelHeight: 'auto',
                                    loadFilter: function (data, parent) {
                                        if (checkResp(data)) {
                                            return data.data;
                                        }
                                    },
                                    onLoadSuccess: function () {
                                        SysUserRole.combobox('setValues', data.data.roleId);
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
            var ids = SysUser.list.getSelectionsIds();
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
                            url: SysUser.URL.delete(),
                            data:{ids:ids},
                            success: function () {
                                if(checkResp(data)){
                                    SysUser.list.reload();
                                    //如果不清空，删除还可以编辑BUG
                                    SysUser.list.clearSelectionsAndChecked();
                                }
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            SysUserList.datagrid("reload");
        },
        clearSelectionsAndChecked: function () {
            SysUserList.datagrid("clearChecked");
            SysUserList.datagrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='searchSysUser']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i] + "'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            SysUserList.datagrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='searchSysUser']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='searchSysUser']").each(function (i) {
                $(this).val("");
            });
        }
    }
}
