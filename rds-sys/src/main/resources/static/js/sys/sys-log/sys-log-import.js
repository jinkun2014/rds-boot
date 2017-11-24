function saveXls(){
    $("#SysLogXlsForm").form({
        url: SysLog.URL.importXls(),
        onSubmit: function () {
            // do some check
            // return false to prevent submit;
        },
        success: function (data) {
            var data = eval('(' + data + ')');
            if (data.code == 200) {
                $("#dialog").dialog('close');
                SysLog.list.reload();

            }
        }
    }).submit();
}
function cancelXls(){
    $("#dialog").dialog('close');
}