<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>${r"${title}"}</title>
    <style>
        html, body {
            height: 100%;
        }
    </style>
</head>
<body>
<!-- 引入头页 -->
<jsp:include page="../../common/head.jsp"></jsp:include>
<div class="container" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-6">
            <ol class="breadcrumb">
                <li>当前位置：</li>
                <li>基础数据</li>
                <li class="active">${EntityInfo.remarks}管理</li>
            </ol>
        </div>
        <div class="col-md-6" style="padding-top:5px;">
            <div class="pull-right">
                <iframe id="iframe" src="about:blank" style="display:none"></iframe>
                <a class="btn btn-success btn-sm" href="${r"${pageContext.request.contextPath}"}/${EntityInfo.tableName ? replace("_","/")}/edit.do"
                   data-toggle="modal" data-target="#modal">
                    <span class="glyphicon glyphicon-plus prs"></span>&nbsp;新增
                </a>
            </div>
        </div>
    </div>

    <!--
    <div class="well well-sm" style="text-align:left;">
        <div id="search-form" class="form-inline">
            <div class="form-group">
                <input type="text" id="studentName" name="studentName" value="" class="form-control" placeholder="学生名称">
            </div>
            <a class="btn btn-primary" onclick="creatPageList(1)">搜索</a>
        </div>
    </div>
    -->
</div>

<div class="container" style="height:auto;min-height:80%;margin-bottom:10px;">
    <!--数据begin-->
    <div id="contextDiv">
    </div>
    <!--数据end-->
</div>

<!--模态窗-->
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>

<!--尾部-->
<jsp:include page="../../common/footer.jsp"></jsp:include>

<!--查询请求参数-->
<script type="text/javascript">
    var Query = "${r"${pageContext.request.contextPath}"}/${EntityInfo.tableName ? replace("_","/")}/context.do";
    var DownQuery = "${r"${pageContext.request.contextPath}"}/${EntityInfo.tableName ? replace("_","/")}/export.do";
</script>
<!--js-->
<script src="${r"${pageContext.request.contextPath}"}/js/${EntityInfo.tableName ? replace("_","/")}/list.js"></script>
</body>
</html>