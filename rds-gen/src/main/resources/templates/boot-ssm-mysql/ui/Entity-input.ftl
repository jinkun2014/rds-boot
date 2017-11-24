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
    <form id="${EntityInfo.entityName}Form" method="post">
        <table align="center" class="com_table">
    <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if !fieldInfo.primaryKey>
            <#if fieldInfo.type == "Date">
            <tr>
                <td></td>
                <td><label>${fieldInfo.remarks}:</label></td>
                <td><input class="easyui-datetimebox com_input" type="text" name="${fieldInfo.name}" data-options="required:${fieldInfo.noNull?string('true', 'false')},showSeconds:true"/></td>
                <td></td>
            </tr>
            <#else >
            <tr>
                <td></td>
                <td><label>${fieldInfo.remarks}:</label></td>
                <td><input class="easyui-textbox com_input" type="text" name="${fieldInfo.name}" data-options="required:${fieldInfo.noNull?string('true', 'false')}"/></td>
                <td></td>
            </tr>
            </#if>
        <#else >
            <input type="hidden" name="${fieldInfo.name}"/>
        </#if>
    </#list>
        </table>
    </form>
    <script th:src="@{/js/${EntityInfo.packageInfo.module}/${EntityInfo.tableName ? replace("_","-")}/${EntityInfo.tableName ? replace("_","-")}.js}" src="../../../static/js/${EntityInfo.packageInfo.module}/${EntityInfo.tableName ? replace("_","-")}/${EntityInfo.tableName ? replace("_","-")}.js"></script>
    <script>
        ${EntityInfo.entityName}.input.init('');
    </script>
</body>
</html>
