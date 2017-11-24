<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
    </button>
    <h4 class="modal-title" id="exampleModalLabel">
        ${r"${empty vo?'新增"}${EntityInfo.remarks}':'编辑${EntityInfo.remarks}${r"'}"}
    </h4>
</div>
<form id="save-form" class="form-horizontal" method="post"
      action="${r"${pageContext.request.contextPath}"}/${EntityInfo.tableName ? replace("_","/")}/save.do">
    <div class="modal-body" style="min-height:200px;height:auto;">
        <input type="hidden" value="${r"${vo.id}"}" name="id" id="id"/>
    <#list EntityInfo.fieldInfoList as fieldInfo>
    <#if !fieldInfo.primaryKey>
        <div class="row form-group">
            <div class="col-md-3 control-label">
                <label>${fieldInfo.remarks}</label>
            </div>
            <div class="col-md-6 controls">
                <input type="text" id="${fieldInfo.name}" name="${fieldInfo.name}" class="form-control inputStart" value="${r"${vo."}${fieldInfo.name}${r"}"}"/>
            </div>
        </div>
    </#if>
    </#list>
    </div>

    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="submit" class="btn btn-primary">确认</button>
    </div>
</form>

<script type="text/javascript" src="${r"${pageContext.request.contextPath}"}/assets/js-lib/bootstrap-Validation/dist/js/bootstrapValidator.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('#save-form').bootstrapValidator({
        //        live: 'disabled',
        message: '值不合法',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        <#list EntityInfo.fieldInfoList as fieldInfo>
        <#if !fieldInfo.primaryKey>
            ${fieldInfo.name}: {
                /* group : '.col-sm-4', */
                validators: {
                    notEmpty: {
                        message: ' ${fieldInfo.remarks}不能为空'
                    }
                }
            },
        </#if>
        </#list>
        }
    });
</script>
