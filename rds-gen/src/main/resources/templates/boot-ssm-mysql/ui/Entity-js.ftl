var ctx = "";//项目部署的工程名
var ${EntityInfo.entityName}List;
var ${EntityInfo.entityName}Form;

//其它组件

var ${EntityInfo.entityName} = {
    URL: {
        inputUI: function () {
            return ctx + "/${EntityInfo.tableName ? replace("_","/")}s/ui/input.html";
        },
        listUI: function () {
            return ctx + "/${EntityInfo.tableName ? replace("_","/")}s/ui/list.html";
        },
        list: function () {
            return ctx + "/${EntityInfo.tableName ? replace("_","/")}s/";
        },
        save: function () {
            return ctx + "/${EntityInfo.tableName ? replace("_","/")}s/save";
        },
        delete: function () {
            return ctx + "/${EntityInfo.tableName ? replace("_","/")}s/delete";
        },
        get: function (id) {
            return ctx + "/${EntityInfo.tableName ? replace("_","/")}s/" + id;
        }
    },
    input: {
        init: function (ct) {
            ctx = ct;
            ${EntityInfo.entityName}.input.initComponent();
            ${EntityInfo.entityName}.input.initForm();
        },
        initComponent: function () {
            ${EntityInfo.entityName}Form = $("#${EntityInfo.entityName}Form");
        },
        initForm: function () {
            ${EntityInfo.entityName}Form.form({
                url: ${EntityInfo.entityName}.URL.save(),
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                },
                success: function (data) {
                    var data = eval('(' + data + ')');
                    if (data.code == 200) {
                        ${EntityInfo.entityName}.input.close();
                        ${EntityInfo.entityName}.list.reload();
                    }
                }
            });
        },
        submitForm: function () {
            // submit the form
            ${EntityInfo.entityName}Form.submit();
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
                ${EntityInfo.entityName}.list.add();
            }
        },'-',{
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                ${EntityInfo.entityName}.list.delete();
            }
        },'-',{
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                ${EntityInfo.entityName}.list.edit();
            }
        },'-',{
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                ${EntityInfo.entityName}.list.reload();
            }
        }],
        init: function (ct) {
            ctx = ct;
            ${EntityInfo.entityName}.list.initComponent();
            ${EntityInfo.entityName}.list.initList();
        },
        initComponent: function () {
            ${EntityInfo.entityName}List = $("#${EntityInfo.entityName}List");
        },
        initList: function () {
            ${EntityInfo.entityName}List.datagrid({
                url: ${EntityInfo.entityName}.URL.list(),
                method: 'get',
                pagination: true,
                pageSize: 20,
                fit:true,
                toolbar: '#${EntityInfo.entityName}Toolbar',//${EntityInfo.entityName}.list.toolbar,
                singleSelect: false,
                collapsible: false,
                striped:true,
                columns: [[
                    {field: 'ck', checkbox: true},
                <#list EntityInfo.fieldInfoList as fieldInfo>
                    <#if fieldInfo.primaryKey>
                    {field: '${fieldInfo.name}', title: '${fieldInfo.remarks}', hidden:true},
                    <#else>
                        <#-- 如果小于5列就分配20%的宽度 -->
                        <#if fieldInfo.noDisplay>
                    {field: '${fieldInfo.name}', title: '${fieldInfo.remarks}', hidden:true},
                        <#else>
                            <#if fieldInfo_index==(EntityInfo.displayCount-1)>
                    {field: '${fieldInfo.name}', title: '${fieldInfo.remarks}', width: '${(99-99%(EntityInfo.displayCount-1))/(EntityInfo.displayCount-1)+99%(EntityInfo.displayCount-1)}%', hidden:false}
                            <#else >
                    {field: '${fieldInfo.name}', title: '${fieldInfo.remarks}', width: '${(99-99%(EntityInfo.displayCount-1))/(EntityInfo.displayCount-1)}%', hidden:false},
                            </#if>
                        </#if>
                    </#if>
                </#list>
                ]],
                //设置选中事件，清除之前的行选择
                onClickRow: function (index,row) {
                    $(this).datagrid("unselectAll");
                    $(this).datagrid("selectRow",index);
                },
                //替换分页请求参数
                onBeforeLoad: function (params) {
                    params.page = params.page
                    params.size = params.rows
                    delete params.rows
                },
                loadFilter: function(data){
                    if (data.data){
                        //返回结果进行封装
                        return {total: data.data.total, rows: data.data.result};
                    } else {
                        return data;
                    }
                }
            });
        },
        getSelectionsIds: function () {
            var sels = ${EntityInfo.entityName}List.datagrid("getSelections");
            var ids = [];
            for (var i in sels) {
                ids.push(sels[i].${EntityInfo.primaryKey.name});
            }
            ids = ids.join(",");
            return ids;
        },
        //增
        add: function () {
            $("<div></div>").dialog({
                id: 'dialog',
                href: ${EntityInfo.entityName}.URL.inputUI(),
                title: '新增${EntityInfo.remarks}',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons:[{
                    text: '保存', iconCls:'icon-save', handler: function(){
                        ${EntityInfo.entityName}.input.submitForm();
                    }
                },{
                    text: '取消', iconCls: 'icon-cancel', handler: function(){
                        ${EntityInfo.entityName}.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                }
            });
        },
        //改
        edit: function () {
            var sels = ${EntityInfo.entityName}List.datagrid("getSelections");
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
                href: ${EntityInfo.entityName}.URL.inputUI(),
                title: '修改${EntityInfo.remarks}',
                width: 600,
                height: 400,
                modal: true,
                cache: false,
                buttons:[{
                    text: '保存', iconCls:'icon-save', handler: function(){
                        ${EntityInfo.entityName}.input.submitForm();
                    }
                },{
                    text: '取消', iconCls: 'icon-cancel', handler: function(){
                        ${EntityInfo.entityName}.input.close();
                    }
                }],
                onClose: function () {
                    $("#dialog").dialog('destroy');
                },
                onLoad: function () {
                    //方案一：使用Form的load去load数据
                    //${EntityInfo.entityName}Form.form("load", ${EntityInfo.entityName}.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //${EntityInfo.entityName}Form.form("load",sels[0]);
                    //方案三：使用Ajax请求数据
                    $.ajax({
                        type: "GET",
                        url: ${EntityInfo.entityName}.URL.get(sels[0].${EntityInfo.primaryKey.name}),
                        success: function (data) {
                            if (data.code == 200) {
                                ${EntityInfo.entityName}Form.form("load", data.data);
                            }
                        }
                    });
                }
            });

        },
        //删
        delete: function () {
            var ids = ${EntityInfo.entityName}.list.getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert("对话框", "至少选择一行");
                return;
            }

            $.messager.confirm({
                title: '确认提示框',
                msg: '你确定要删除吗？',
                fn: function(r){
                    if (r){
                        $.ajax({
                            type: "POST",
                            url: ${EntityInfo.entityName}.URL.delete(),
                            data:{ids:ids},
                            success: function () {
                                ${EntityInfo.entityName}.list.reload();
                                //如果不清空，删除还可以编辑BUG
                                ${EntityInfo.entityName}.list.clearSelectionsAndChecked();
                            }
                        });
                    }
                }
            });
        },
        //刷新
        reload: function () {
            ${EntityInfo.entityName}List.datagrid("reload");
        },
        clearSelectionsAndChecked:function(){
            ${EntityInfo.entityName}List.datagrid("clearChecked");
            ${EntityInfo.entityName}List.datagrid("clearSelections");
        },
        //搜索
        search: function () {
            var searchName = [];
            var searchValue = [];
            $("input[lang='search${EntityInfo.entityName}']").each(function (i) {
                searchName[i] = $(this).attr("name");
                searchValue[i] = $(this).val();
            });

            var queryParamsArr = [];
            for (var i = 0; i < searchName.length; i++) {
                queryParamsArr.push(searchName[i] + ":'" + searchValue[i]+"'")
            }
            var queryParams = "{" + queryParamsArr.join(",") + "}";

            ${EntityInfo.entityName}List.datagrid({
                queryParams: eval('(' + queryParams + ')'),
                onLoadSuccess: function (data) {
                    //回显搜索内容
                    $("input[lang='search${EntityInfo.entityName}']").each(function (i) {
                        $(this).val(searchValue[i]);
                    });
                }
            });
        },
        //清空
        clear: function () {
            $("input[lang='search${EntityInfo.entityName}']").each(function (i) {
                $(this).val("");
            });
        }
    }
}
