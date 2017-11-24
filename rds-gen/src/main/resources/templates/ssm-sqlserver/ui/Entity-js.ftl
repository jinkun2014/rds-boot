/**
 * ${EntityInfo.remarks}管理js
 */

creatPageList(1);
function creatPageList(currentPage){
    //var studentName=$("#studentName").val();

    $.ajax({
        type: "POST",
        url: Query,
        data: {pageNo:currentPage} ,
        cache: false,
        async: true,
        beforeSend: function () {
            $('#contextDiv').html("<div align='center'><img style='margin-left: auto;margin-right: auto;' src='"+loadingImgPath+"' /></div>");
        },
        success: function (data) {
            $('#contextDiv').html(data);
        }
    });
}

//导出
$("#downloadPage").click(function(){
    $("#iframe").attr('src',DownQuery);
});

function deleteBt(id){
    layer.confirm("确定删除这条数据吗？", {icon:3}, function () {
        $.ajax({
            type: "POST",
            url:projectPath+"/${EntityInfo.tableName ? replace("_","/")}/delete.do",
            data: {id:id},
            dataType:'json',
            success: function (data) {
                if(data.flag=="1"){
                    // layer.msg("删除成功", {icon:1,time:1000,shade: 0.01});
                    layer.msg("删除成功", {icon:1,time:1000,shade: 0.01});
                    creatPageList(1);
                }else{
                    // layer.msg("删除失败", {icon:2,time:1000,shade: 0.01});
                    layer.msg("删除失败", {icon:2,time:1000,shade: 0.01});
                }
            }
        });
    });
}