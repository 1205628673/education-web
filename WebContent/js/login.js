$(document).ready(function () {
	var cookies = document.cookie.split(";");
    for(var item = 0;item < cookies.length;item++){
        var name = cookies[item].split("=")[0];
        console.log(name+":"+cookies[item].split("=")[1]);
        if(name == "token"){
            window.location.href="main";
        }
    }   
     $(".divForm").bind("click",function () {
        $(".warning").text("");
        var warning = verifyUsername();
        if(warning == 'unull'){
            $("#uwarning").text("用户名不能为空");
        }else if(warning == 'pnull'){
             $("#pwarning").text("密码不能为空");
        }else if(warning == "illegal"){
             $("#uwarning").text("用户名包含非法字符");
        }
    });
    $("#submit").bind("click",function () {
        $(".warning").text("");
        var warning = verifyUsername();
        if (warning == ''){
            login();
        }else if(warning == 'unull'){
            $("#uwarning").text("用户名不能为空");
        }else if(warning == 'pnull'){
             $("#pwarning").text("密码不能为空");
        }else {
             $("#uwarning").text("用户名包含非法字符");
        }
    });
});
function verifyUsername() {
    var username = $("#username").val();
    var password = $("#password").val();
    var illegal = "()!@#$%^&*|{}[]'\"?><.,:;";
    var warning = '';
    if(username == ''){
        warning = "unull";
    }else if ( password == ''){
        warning = "pnull";
    }
    if(warning == '') {
            for (var i = 0; i < illegal.length; i++) {
                if (username.indexOf(illegal[i])>=0) {
                    warning = "illegal";
                    break;
                }
            }
    }
    return warning;
}
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    $.post("toLogin",{username:username,password:password},function (values,status) {
        if(status == "success"){
            var code = values.code;
            var data = values.data;
            if(code == 404 || code == 500){
                $("#pwarning").text(data);
            }else {
                window.location.href="main";
            }
        }
    })
}