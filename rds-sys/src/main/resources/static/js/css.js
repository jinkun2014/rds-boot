/**
 * 此文件可以用于Html动态添加样式来实现引入公用的head和foot
 * 用法：
 * <script type="text/javascript" th:src="@{/js/css.js}" src="../static/js/css.js"></script>
 * @param url
 */
function includeLinkStyle(url) {
    var link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = url;
    document.getElementsByTagName('head')[0].appendChild(link);
}
//<link rel="stylesheet" type="text/css" th:href="@{/css/core.css}" href="../static/css/core.css"/>
//<link rel="stylesheet" type="text/css" th:href="@{/css/icon-ext.css}" href="../static/css/icon-ext.css"/>
//<link rel="stylesheet" type="text/css" th:href="@{/js/jquery-easyui-1.5/themes/gray/easyui.css}" href="../static/js/jquery-easyui-1.5/themes/gray/easyui.css"/>
//<link rel="stylesheet" type="text/css" th:href="@{/js/jquery-easyui-1.5/themes/icon.css}" href="../static/js/jquery-easyui-1.5/themes/icon.css"/>

includeLinkStyle("/css/core.css");
includeLinkStyle("/css/icon-ext.css");
includeLinkStyle("/js/jquery-easyui-1.5/themes/gray/easyui.css");
includeLinkStyle("/js/jquery-easyui-1.5/themes/icon.css");
