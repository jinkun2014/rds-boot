function login() {
    var loginName = $("#loginName").textbox("getValue");
    var password = $("#password").textbox("getValue");
    $.ajax({
        type: "POST",
        url: "/login",
        data: {loginName: loginName, password: password},
        dataType: 'json',
        success: function (data) {
            if (checkResp(data)) {
                window.location.href = data.data;
            }
        }
    });
}