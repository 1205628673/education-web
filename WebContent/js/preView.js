$(document).ready(function addCourseOnList() {
	var cookies = document.cookie.split(";");
    for(var item = 0;item < cookies.length;item++){
        var name = cookies[item].split("=")[0];
        console.log(name+":"+cookies[item].split("=")[1]);
        if(name == "token"){
            getUserByToken(cookies[item].split("=")[1]);
        }
    }
    
    var path = window.location.pathname.split("/")[3];
    if(path==undefined)
    	path = 1;
    var obj;
   $.get("getCourseList/"+path,function (data,status) {
		        obj = data;
		        courselist = obj.data;
		        if(obj.message != "false" && data != null) {
		            if(!getSize(courselist)){
		                nullHtml();
		            }
		            else if(status != "success"){
		                error(status);
		            }
		            else {
		                for(var item=0;item<courselist.length;item++){
		                    courseHtml(courselist[item].cid,courselist[item].name);
		                }
		            }
		        }else{
		            nullHtml();
		        }
   });
});
function getSize(json) {
    var len=0;
    for (var item in json){
        len++;
    }
    return len;
}function error(status) {
    var warning = "<h4 class=\"mx-auto\" style=/'color: darkgrey/'>状态:"+status+"</h4>";
    $(".listcontent.row").append(warning);
}
function nullHtml() {
    var emtpy = "<h4 class=\"mx-auto\" style=/'color: darkgrey/'>没有课程</h4>";
    $(".listcontent.row").append(emtpy);
    $("title").text("404 not found");
}
function courseHtml(courseId,courseName) {
    var courseImg = "images/"+courseName+".jpeg";
    var courseHref = "course/"+courseId+"/1";
    var rand = Math.floor(Math.random()*2)+1;
    var maxRand =Math.floor(Math.random()*20000)+1;
    var color = rand>1? "danger":"success";
    var badge = rand>1? "Hot":"New";
    var courseHtml = "<div class=\"col-md-3\">"
                    +"<div class=\"photo\">"
                    +"<a href="+courseHref+">"
                    +"<img src="+courseImg+">"
                    +"<div class=\"detail\">"
                    +"<p>"+courseName+"</p>"
                    +"<i class=\"fa fa-eye\">"+maxRand+"</i>"
                    +"<span class=\"pull-right badge badge-pill badge-"+color+"\">"+badge+"</span>"
                    +"</div>"
                    +"</a>"
                    +"</div>"
                    +"</div>";
    $("#preViewPoint").append(courseHtml);
}
function getUserByToken(token) {
    $.get("getUserByToken/"+token,function (values,status) {
        if(status == "success"){
            var username = values.data;
            if(username != null || username != '') {
                $("#logined").empty();
                var display = "<li class=\"nav-item\">" +
                    "<a class=\"nav-link\" href=\"userLogin\">欢迎  !" + username + "</a>" +
                    "</li>";
                $("#logined").append(display);
            }
        }
    });
}