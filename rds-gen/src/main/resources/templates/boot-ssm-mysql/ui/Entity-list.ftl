<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>${EntityInfo.remarks}</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/common.css}" href="../../../static/css/common.css"/>
    <link rel="stylesheet" type="text/css" th:href="@{/js/jquery-easyui-1.5/themes/gray/easyui.css}"  href="../../../static/js/jquery-easyui-1.5/themes/gray/easyui.css"/>
    <link rel="stylesheet" type="text/css" th:href="@{/js/jquery-easyui-1.5/themes/icon.css}" href="../../../static/js/jquery-easyui-1.5/themes/icon.css"/>
    <script type="text/javascript" th:src="@{/js/jquery-easyui-1.5/jquery.min.js}"  src="../../../static/js/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" th:src="@{/js/jquery-easyui-1.5/jquery.easyui.min.js}"  src="../../../static/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" th:src="@{/js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js}"  src="../../../static/js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <!-- 工具栏 -->
    <div id="${EntityInfo.entityName}Toolbar"  style="padding:5px;height:auto">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="javascript:${EntityInfo.entityName}.list.add()">增加</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="true" onclick="javascript:${EntityInfo.entityName}.list.delete()">删除</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true" onclick="javascript:${EntityInfo.entityName}.list.edit()">编辑</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" plain="true" onclick="javascript:${EntityInfo.entityName}.list.reload()">刷新</a>

        <#if EntityInfo.searching>
        <!-- 搜索开始 -->
        <span style="float: right;margin-right: 10px;padding: 1px">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" plain="true" onclick="javascript:${EntityInfo.entityName}.list.clear()">清除</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="true" onclick="javascript:${EntityInfo.entityName}.list.search()">搜索</a>
        </span>
        <span style="float: right;margin-right: 10px;padding-top: 3px">
            <#list EntityInfo.fieldInfoList as fieldInfo>
                <#if fieldInfo.search>
            ${fieldInfo.remarks}: <input lang="search${EntityInfo.entityName}" name="${fieldInfo.name}" style="line-height:19px;border:1px solid #ccc">
                </#if>
            </#list>
        </span>
        </#if>
    </div>
    <!-- 列表 -->
    <table id="${EntityInfo.entityName}List" data-options="border:false" style="width: 100%;" title="${EntityInfo.remarks}"></table>
    <script th:src="@{/js/${EntityInfo.packageInfo.module}/${EntityInfo.tableName ? replace("_","-")}/${EntityInfo.tableName ? replace("_","-")}.js}" src="../../../static/js/${EntityInfo.packageInfo.module}/${EntityInfo.tableName ? replace("_","-")}/${EntityInfo.tableName ? replace("_","-")}.js"></script>
    <script>
        ${EntityInfo.entityName}.list.init('');
    </script>
</body>
</html>
