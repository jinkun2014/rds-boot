//保存数据源的信息
var db;
var editTableIndex = undefined;
var editColumnIndex = undefined;

function setDb() {
    $("<div></div>").dialog({
        id: 'dialog',
        href: "set-db.html",
        title: '设置数据源',
        width: 600,
        height: 400,
        modal: true,
        cache: false,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                if (isFormValid()) {
                    //初始化db信息
                    db = getDataSource();

                    //关闭对话框
                    $("#dialog").dialog("close");

                    //刷新表数据
                    $("#table").datagrid({
                        url: '/gen/tables',
                        queryParams: db
                    });
                }
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $("#dialog").dialog("close");
            }
        }],
        onClose: function () {
            $("#dialog").dialog('destroy');
        }
    });
}

function gen() {
    //先保存修改的列
    save();

    var sels = $("#table").datagrid("getSelections");
    if (sels.length < 1) {
        $.messager.alert("对话框", "至少选择一行");
        return;
    }

    var entityInfo = JSON.stringify($('#table').datagrid('getSelected'));
    var fieldInfo = JSON.stringify($('#column').datagrid('getData').rows);
    var params = {
        dialect: db.dialect,
        url: db.url,
        username: db.username,
        password: db.password,
        entityInfo: entityInfo,
        fieldInfo: fieldInfo
    };

    $("<div></div>").dialog({
        id: 'dialog',
        href: "set-gen.html",
        title: '生成代码',
        width: 600,
        height: 400,
        modal: true,
        cache: false,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                saveForm(function (data) {
                    var data = eval('(' + data + ')');
                    if (data.code == 200) {
                        $("#dialog").dialog("close");
                        //下载
                        window.location.href = data.data;
                    }
                });
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $("#dialog").dialog("close");
            }
        }],
        onClose: function () {
            $("#dialog").dialog('destroy');
        },
        onLoad: function () {
            initData(params);
        }
    });
}

function save() {
    if(editTableIndex){
        $("#table").datagrid('endEdit', editTableIndex);
    }
    if(editColumnIndex){
        $("#column").datagrid('endEdit', editColumnIndex);
    }
}

function reload() {
    // $("#table").datagrid('reload');
    //console.info(JSON.stringify($('#column').datagrid('getData').rows));
    //console.info(JSON.stringify($('#table').datagrid('getSelected')));
}

//显示列表数据
$("#table").datagrid({
    method: 'POST',
    pagination: true,
    pageSize: 30,
    toolbar: '#Toolbar',
    checkOnSelect: true,
    selectOnCheck: true,
    singleSelect: true,
    collapsible: true,
    fit: true,
    columns: [[
        {field: 'ck', checkbox: true},
        {field: 'tableName', title: '表名', width: '33%'},
        {
            field: 'remarks', title: '注释', width: '33%',
            editor: {
                type: 'textbox',
                options: {},
                required: true
            }
        },
        {
            field: 'entityName', title: '实体名', width: '33%',
            editor: {
                type: 'textbox',
                options: {},
                required: true
            }
        }
    ]],
    // onClickCell: function (index, field, value) {
    //     //设置选中事件，清除之前的行选择
    //     $("#table").datagrid("unselectAll");
    //
    //     showColumns(index ,field.id, tableName);
    // },
    onDblClickCell: function (index, field, value) {

        editTableIndex = index;
        $(this).datagrid('beginEdit', index);
        var ed = $(this).datagrid('getEditor', {index: index, field: field});
        $(ed.target).focus();
    },
    onClickRow: function (index, row) {
        $(this).datagrid('endEdit', editTableIndex);
        showColumns(index, row.id, row.tableName);
    }
});

function showColumns(index, id, tableName) {
    //显示列表数据
    $("#column").datagrid({
        method: 'POST',
        url: "/gen/" + tableName + "/columns",
        queryParams: db,
        pagination: true,
        pageSize: 30,
        checkOnSelect: true,
        selectOnCheck: true,
        singleSelect: true,
        collapsible: true,
        fit: true,
        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'columnName', title: '列名', width: '15%'},
            {field: 'columnType', title: '列类型', width: '15%'},
            {
                field: 'remarks', title: '列注释', width: '15%',
                editor: {
                    type: 'textbox',
                    options: {},
                    required: true
                }
            },
            {
                field: 'defaultValue', title: '默认值', width: '15%',
                editor: {
                    type: 'textbox',
                    options: {},
                    required: true
                }
            },
            {
                field: 'name', title: '属性名', width: '15%',
                editor: {
                    type: 'textbox',
                    options: {},
                    required: true
                }
            },
            {
                field: 'type', title: '类型', width: '15%',
                editor: {
                    type: 'combobox',
                    options: {
                        valueField: 'label',
                        textField: 'value',
                        data: [{
                            label: 'String',
                            value: 'String'
                        }, {
                            label: 'Long',
                            value: 'Long'
                        }, {
                            label: 'Integer',
                            value: 'Integer'
                        }, {
                            label: 'Boolean',
                            value: 'Boolean'
                        }, {
                            label: 'boolean',
                            value: 'boolean'
                        }, {
                            label: 'int',
                            value: 'int'
                        }, {
                            label: 'long',
                            value: 'long'
                        }],
                        required: true
                    }
                }
            },
            {
                field: 'primaryKey', title: '主键', width: '5%',
                formatter: function (value, row, index) {
                    if (row.primaryKey) {
                        return '<input type="radio" name="primaryKey" checked="checked">';
                    }
                    return '<input type="radio"  name="primaryKey" >';
                }
            }
        ]],
        // onClickCell: function (index, field, value) {
        //     //设置选中事件，清除之前的行选择
        //     $("#table").datagrid("unselectAll");
        //
        //     showColumns(index ,field.id, tableName);
        // },
        onDblClickCell: function (index, field, value) {
            editColumnIndex = index;
            $(this).datagrid('beginEdit', index);
            var ed = $(this).datagrid('getEditor', {index: index, field: field});
            $(ed.target).focus();
        },
        onClickRow: function (index, row) {
            $(this).datagrid('endEdit', editColumnIndex);
        }
    });
    // $("<div></div>").dialog({
    //     id: 'dialog',
    //     href: "set-column.html",
    //     title: '数据列配置',
    //     width: 600,
    //     height: 400,
    //     modal: true,
    //     cache: false,
    //     buttons: [{
    //         text: '保存',
    //         iconCls: 'icon-save',
    //         handler: function () {
    //             var columns = getSelections();
    //             $("#" + id).text(columns);
    //
    //             $("#dialog").dialog("close");
    //         }
    //     }, {
    //         text: '取消',
    //         iconCls: 'icon-cancel',
    //         handler: function () {
    //             $("#dialog").dialog("close");
    //         }
    //     }],
    //     onClose: function () {
    //         $("#dialog").dialog('destroy');
    //     },
    //     onLoad: function () {
    //         initColumns(db, tableName);
    //     }
    // });

}

function searchByName() {
    var name = $("#tableName").val();
    db.name = name;
    $("#table").datagrid({
        queryParams: db
    });
}

function clearInput() {
    $("#tableName").val("");
}

$(function () {
    // $("#tableName").change(function () {
    //     searchByName();
    // });
    //实时搜索
    //$("#tableName").bind('input propertychange', function() {
    //    searchByName();
    //});
});