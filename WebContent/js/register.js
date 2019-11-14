$(document).ready(function () {
    $(".divForm").bind("click",function () {
        $(".warning").text("");
        var warning = verifyUsername();
        if(warning == 'unull'){
            $("#uwarning").text("用户名不能为空");
        }else if(warning == 'pnull'){
             $("#pwarning").text("密码不能为空");
        }else if(warning == "repnull"){
            $("#pwarning").text("密码不能为空");
        }else if(warning == "differ"){
            $("#rpwarning").text("两次输入密码不一致");
        }else if(warning == "illegal"){
             $("#uwarning").text("用户名包含非法字符");
        }
    });
    $("#submit").bind("click",function () {
        $(".warning").text("");
        var warning = verifyUsername();
        if (warning == ''){
            register();
        }else if(warning == 'unull'){
            $("#uwarning").text("用户名不能为空");
        }else if(warning == 'pnull'){
             $("#pwarning").text("密码不能为空");
        }else if(warning == "repnull"){
            $("#pwarning").text("密码不能为空");
        }else if(warning == "differ"){
            $("#rpwarning").text("两次输入密码不一致");
        }else {
             $("#uwarning").text("用户名包含非法字符");
        }
    });
});
function verifyUsername() {
    var username = $("#username").val();
    var password = $("#password").val();
    var repassword = $("#repassword").val();
    var illegal = "()!@#$%^&*|{}[]'\"?><.,:;";
    var warning = '';
    if(username == ''){
        warning = "unull";
    }else if ( password == ''){
        warning = "pnull";
    }else if(repassword == ''){
        warning ='repnull';
    }else if (password.length != repassword.length){
        warning = "differ";
    }
    if(warning == '') {
            for (var i = 0; i < illegal.length; i++) {
                if (username.indexOf(illegal[i])>=0) {
                    warning = "illegal";
                    break;
                }
            }
    }
    if(warning == ''){
        for (var item = 0;item<password.length;item++){
            for (var j=0;j<repassword.length;j++){
                if(password[item] != repassword[j]){
                    warning = "differ";
                    break;
                }else {
                    item++;
                }
            }
            if (warning != '')
                break;
        }
    }
    return warning;
}
function register() {
    var username = $("#username").val();
    var password = $("#password").val();
    console.log(username+" "+password);
    $.post("toRegister",{username:username,password:password},function (values,status) {
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