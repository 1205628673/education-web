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
    if(path=="")
    	path = 1;
    var obj;
    var re=$.get("../getCourseList/"+path,function (data,status) {
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
    re.done(function() {
		obj.data
	});
    $.get("../getPage/"+path,function (data,status) {
        var obj = data;
        if(obj.MESSAGE != "false" && data != null){
            var page = obj.data;        
            pageHtml(page,4);
        }else if(status != "success"){
            error(status);
        } else {
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
    var courseImg = "../images/"+courseName+".jpeg";
    var courseHref = "../course/"+courseId+"/1";
    var rand = Math.floor(Math.random()*2)+1;
    var maxRand =Math.floor(Math.random()*20000)+1;
    var color = rand>1? "danger":"success";
    var badge = rand>1? "Hot":"New";
    var courseHtml = "<div class=\"col-md-3\">"
                    +"<div class=\"photo\">"
                    +"<a href="+courseHref+">"
                    +"<img src=../images/"+courseName+".jpeg>"
                    +"<div class=\"detail\">"
                    +"<p>"+courseName+"</p>"
                    +"<i class=\"fa fa-eye\">"+maxRand+"</i>"
                    +"<span class=\"pull-right badge badge-pill badge-"+color+"\">"+badge+"</span>"
                    +"</div>"
                    +"</a>"
                    +"</div>"
                    +"</div>";
    $("#addpoint").append(courseHtml);
}
function pageHtml(page,max) {
	var total = page.totalPage;
    var next = page.nextPage;
    var pre = page.prePage;
    var current = page.currentPage;
	var nextli = "<li class=\"page-item\"><a class=\"page-link\" href=\""+next+"\">下一页</a></li>"
    var preli = "<li class=\"page-item\"><a class=\"page-link\" href=\""+pre+"\">上一页</a></li>"
    $("#pageul").append(preli);
    if(total > 0){
    	var start = 1;
    	var end = 1;
    	if(total <= max){
    		start = 1;
    		end = total;
    	}else{
    	
    		if(current - max > 0){
    			start = current-max;
    		}else{
    			start = 1;
    		}
    		if(current + max < total){
    			end = current+max;
    		}else{
    			end = total;
    		}
    	}
    	for(var item=start;item<=end;item++) {
            var pageli = "<li class=\"page-item\"><a class=\"page-link\" href=\""+item+"\">"+item+"</a></li>"
            var pagecurrent = "<li class=\"page-item active\"><a class=\"page-link\" href=\""+item+"\">"+item+"</a></li>"
            if(item == current){
                 $("#pageul").append(pagecurrent);
            }else {
                $("#pageul").append(pageli);
            }
        }
    }else{
    	var pageli = "<li class=\"page-item\"><a class=\"page-link\" href=\""+1+"\">"+1+"</a></li>"
        $("#pageul").append(pageli);
    }
    $("#pageul").append(nextli);
}
function getUserByToken(token) {
    $.get("../getUserByToken/"+token,function (values,status) {
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