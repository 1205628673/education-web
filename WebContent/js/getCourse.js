$(document).ready(function addCoursePart() {
	 var cookies = document.cookie.split(";");
	    for(var item = 0;item < cookies.length;item++){
	        var name = cookies[item].split("=")[0];
	        console.log(name+":"+cookies[item].split("=")[1]);
	        if(name == "token"){
	            getUserByToken(cookies[item].split("=")[1]);
	        }
	    }
    var path = window.location.pathname.split("/");
    var courseId = path[3];
    var partId = path[4];
    if(partId != 1) {
        $.get("../../getCourse/" + partId, function (values, status) {
        	if(status == "success" && values.data != null){
	            var obj = values.data;
	            var title = obj.partname;
	            var html = obj.html;
	            wirtenCourseHtml(title,html);
        	}else if(values.data == null){
        		wirtenCourseHtml(404, "找不到课程")
        	}else{
        		wirtenCourseHtml(status,status);
        	}
        });
    }else{
        $.get("../../getCourseByCid/"+courseId, function (values,status) {
        	if(status =="success" && values.data != null){
	            var obj = values.data;
	            var title = obj.partname;
	            var html = obj.html;
	            wirtenCourseHtml(title,html);
        	}else if(values.data == null){
        		wirtenCourseHtml(404, "找不到课程")
        	}else{
        		wirtenCourseHtml(status,status);
        	}
        });
    }
    $.get("../../getCourseKeyVal/"+courseId,function (values,status) {
    	if(status == "success" && values.data != null){
    		var mapObj = values.data;
    		wirtenPartList(mapObj);
    	}else if(values.data == null){
    		wirtenCourseHtml(404, "找不到课程")
    	}else{
    		wirtenCourseHtml(status,status);
    	}
    });
    $.get("../../getCourseName/"+courseId,function (values,status){
    	if(status == "success" && values.data != null){
    		var obj = values.data;
    		var coursename = obj.name;
    		wirtenCourseName(coursename);
    	}else{
    		wirtenCourseHtml(404, "找不到课程");
    	}
    });	
});
function wirtenCourseName(coursename) {
	if(coursename != ""){
		$("title").append(coursename);
		$(".coursename").text(coursename);
	}else{
		$(".coursename").text(404);
		$("title").append(404);
	}
}
function wirtenCourseHtml(title,html) {
    if(title != "" && html != "") {
        $(".text").text(html);
    }else {
        $("title").append("找不到该课程");
        $(".text").append("找不到该课程");
    }
}
function wirtenPartList(kvMap) {
    if(!kvMap.isEmpty){
    	for(var item =0;item<kvMap.length;item++){
	        var courseli = "<li><a href="+kvMap[item].pid+">"+kvMap[item].cpart+"</a></li>";
	        $(".chapterlist").append(courseli);
    	}
    }
}
function getUserByToken(token) {
    $.get("../../getUserByToken/"+token,function (values,status) {
        if(status == "success"){
            var username = values.data;
            if(username != null || username != '') {
                $("#logined").empty();
                var display = "<li class=\"nav-item\">" +
                    "<a class=\"nav-link\" href=\"userLogin\">欢迎!  " + username + "</a>" +
                    "</li>";
                $("#logined").append(display);
            }
        }
    });
}