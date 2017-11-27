function checkResp(data) {
    if (data && data.resultCode == "SUCCESS") {
        return true;
    }
    return false;
}

/**
 * 错误提示框
 * @param result {"code":200,"msg":"发送位置错误"}
 */
function errorAlert(msg) {
    $.messager.alert({
        title: '警告',
        msg: msg,
        fn: function () {

        }
    });
}