<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="n" uri="/newy" %>


<c:if test="${r"${not empty page.result}"}">
    <table class="table table-striped table-hover" id="wordTable">
        <thead>
        <tr>
            <th class="index">
                <input type="checkbox" class="idCheckAll"/>
                序号
            </th>
        <#list EntityInfo.fieldInfoList as fieldInfo>
            <#if !fieldInfo.primaryKey>
                <#-- 如果小于5列就分配20%的宽度 -->
                <#if fieldInfo_index==(EntityInfo.displayCount-1)>
            <th width="${(70-70%(EntityInfo.displayCount-1))/(EntityInfo.displayCount-1)+70%(EntityInfo.displayCount-1)}%">${fieldInfo.remarks}</th>
                <#else >
            <th width="${(70-70%(EntityInfo.displayCount-1))/(EntityInfo.displayCount-1)}%">${fieldInfo.remarks}</th>
                </#if>
            </#if>
        </#list>
            <th>
                操作
            </th>
        </tr>
        </thead>


        <c:forEach items="${r"${page.result}"}" var="${EntityInfo.entityName ? uncap_first }" varStatus="sts">
            <tr>
                <td class="index">
                    <input type="checkbox" class="idCheck"/>
                        ${r"${page.start + sts.index + 1}"}
                    <input type="hidden" value="${r"${"}${EntityInfo.entityName ? uncap_first }${r".id}"}" class="hdSelectId"/>
                </td>
            <#list EntityInfo.fieldInfoList as fieldInfo>
                <#if !fieldInfo.primaryKey>
                <td>
                ${r"${"}${EntityInfo.entityName ? uncap_first }${r"."}${fieldInfo.name}${r"}"}
                </td>
                </#if>
            </#list>
                <td>
                    <a class="btn btn-primary"
                       href="${r"${pageContext.request.contextPath}"}/${EntityInfo.tableName ? replace("_","/")}/edit.do?id=${r"${"}${EntityInfo.entityName ? uncap_first }${r".id}"}"
                       data-toggle="modal" data-target="#modal">
                        编辑
                    </a>
                    <button class="btn btn-warning" onclick="deleteBt(${r"${"}${EntityInfo.entityName ? uncap_first }${r".id}"})">
                        <span class="glyphicon glyphicon-trash"></span>删除
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="col-md-12" style="padding:0px;margin-top:10px;">
        <input type="checkbox" class="idCheckAll">&nbsp;&nbsp;全选
        &nbsp;&nbsp;
        <button class="btn btn-default" id="deleteAllBt">
            批量删除
        </button>
    </div>
</c:if>
<c:if test="${r"${empty page.result}"}">
    <ul class="media-list conversation-list">
        <li class="empty">暂无相关信息</li>
    </ul>
</c:if>

<jsp:include page="../../common/pager.jsp"></jsp:include>
<script>
    //全选
    $(".idCheckAll").click(function () {

        if ($(this).is(":checked")) {
            $(".idCheckAll").prop("checked", true);
            $('.idCheck').prop("checked", true);
        }
        else {
            $(".idCheckAll").prop("checked", false);
            $('.idCheck').prop("checked", false);
        }
    });
    //批量删除按钮
    $("#deleteAllBt").click(function () {
        var ids = [];
        for (var i = 0; i < $(".idCheck").length; i++) {
            if ($(".idCheck").eq(i).is(":checked")) {
                var id = $(".idCheck").eq(i).parent().find(".hdSelectId").val();
                ids.push(id);
            }
        }
        if (ids.length > 0) {
            var idStr = ids.join(",");
            layer.confirm("确定批量删除" + ids.length + "条数据吗？", {icon: 3}, function () {
                $.ajax({
                    type: "POST",
                    url: projectPath + "/${EntityInfo.tableName ? replace("_","/")}/deleteAll.do",
                    data: {ids: idStr},
                    dataType: 'json',
                    success: function (data) {
                        if (data.flag == "1") {
                            layer.msg("删除成功", {icon: 1, time: 1000, shade: 0.01});
                            creatPageList(1);
                        } else {
                            layer.msg("删除失败", {icon: 2, time: 1000, shade: 0.01});
                        }
                    }

                });
            });
        } else {
            layer.msg("未选择任何信息！", {icon: 7, time: 1000, shade: 0.01});
        }
    });
</script>

