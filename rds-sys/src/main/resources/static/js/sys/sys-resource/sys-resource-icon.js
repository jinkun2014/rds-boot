/**
 * Created by Administrator on 2017/5/26.
 */
function clickIcon(url) {
    SysResource.input.setIcon(url);
}

$(function () {
    $.ajax({
        type: "GET",
        url: "/sys/icons",
        data: {page: 1, size: 100000},
        success: function (data) {
            if (data.code == 200) {
                $("#iconDiv").empty();
                for (var i = 0; i < data.data.result.length; i++) {
                    $('#iconDiv').append('<img onclick="clickIcon(\'' + data.data.result[i].url + '\')"  style="margin: 5px;" src="' + data.data.result[i].url + '"/>');
                }
            }
        }
    });
});