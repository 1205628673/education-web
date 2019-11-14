$(document).ready(function () {
    $("#content .form-group").attr({hidden:"hidden"});
    $("#options *").each(function () {
       $(this).bind("click",function () {
            $(this).addClass("active").siblings().removeClass("active")
           clickevent($(this));
       });
    });
    $("#submit").bind("click",function () {
        clickevent($(this));
    })
});
function clickevent(obj) {
	$("#text").empty();	
	$("#pageul").empty();
    if(obj.attr("id") == "queryAllCourse"){
        $("#currentoption").attr({opt:"queryAllCourse"}).text("所有课程");
        $("#content .form-group").attr({hidden:"hidden"});
        var current = 1;
        $.get("getPage/"+current,function (data,status) {
	        var obj = data;
	        if(obj.MESSAGE != "false" && data != null){
	            var page = obj.data;
	            pageHtml(page,4);
	        }else if(status != "success"){
	            error(status);
	        } else {
	            nullHtml();
	        }
	        wirtenCourseHtml("getCourseList/"+current);
        });
    }else if(obj.attr("id") == "queryAllPart"){
        $("#currentoption").attr({opt:"queryAllPart"}).text("所有章节");
        $("#content .form-group").attr({hidden:"hidden"});
        var current = 1;
        $.get("getPartPage/"+current,function (data,status) {
            var obj = data;
            if (obj.MESSAGE != "false" && data != null) {
                var page = obj.data;
                pageHtml(page, 4);
            } else if (status != "success") {
                error(status);
            } else {
                nullHtml();
            }
            wirtenPartHtml("getPartByPage/" + current);
        });
    }else if(obj.attr("id") == "addCourse"){
        $("#currentoption").attr({opt:"addCourse"}).text("添加课程");
        $(".flush").val("");
        $("#content .form-group").attr({hidden:"hidden"});
        $("#cid").removeAttr("hidden");
        $("#name").removeAttr("hidden");
        $("#submit").removeAttr("hidden");
    }else if(obj.attr("id") == "addPart"){
        $("#currentoption").attr({opt:"addPart"}).text("添加章节");
        $(".flush").val("");
        $("#content .form-group").attr({hidden:"hidden"});
        $("#cid").removeAttr("hidden");
        $("#partname").removeAttr("hidden");
        $("#partid").removeAttr("hidden");
        $("#html").removeAttr("hidden");
        $("#submit").removeAttr("hidden");
    }else if(obj.attr("id") == "deletePart"){
        $("#currentoption").attr({opt:"deletePart"}).text("删除章节");
        $(".flush").val("");
        $("#content .form-group").attr({hidden:"hidden"});
        $("#partid").removeAttr("hidden");
        $("#submit").removeAttr("hidden");
    }else if(obj.attr("id") == "deleteCourse"){
        $("#currentoption").attr({opt:"deleteCourse"}).text("删除课程");
        $(".flush").val("");
        $("#content .form-group").attr({hidden:"hidden"});
        $("#cid").removeAttr("hidden");
        $("#submit").removeAttr("hidden");
    }else if(obj.attr("id") == "updatePart"){
        $("#currentoption").attr({opt:"updatePart"}).text("更新章节");
        $(".flush").val("");
        $("#content .form-group").attr({hidden:"hidden"});
        $("#partname").removeAttr("hidden");
        $("#partid").removeAttr("hidden");
        $("#html").removeAttr("hidden");
        $("#submit").removeAttr("hidden");
    }else if(obj.attr("id") == "updateCourse"){
        $("#currentoption").attr({opt:"updateCourse"}).text("更新课程");
        $(".flush").val("");
        $("#content .form-group").attr({hidden:"hidden"});
        $("#cid").removeAttr("hidden");
        $("#name").removeAttr("hidden");
        $("#submit").removeAttr("hidden");
    }else if(obj.attr("id") == "submit"){
        var opt = $("#currentoption").attr("opt");
    
        if(opt == "addCourse"){
            addCourse();
        }else if (opt == "addPart"){
            addPart();
        }else if (opt == "deletePart"){
            deleteCoursePart();
        }else if (opt == "deleteCourse"){
            deleteCourse();
        }else if (opt == "updatePart"){
            updateCoursePart();
        }else if(opt == "updateCourse"){
            updateCourse();
        }
        
    }else if(!isNaN(obj.attr("id"))){    	
    	
        $("#content .form-group").attr({hidden:"hidden"});
        var current = obj.attr("id");             
        if(current == undefined || '')
            current = 1;        	
        var judge = $("#options a").eq(0).hasClass("active");     
        if(judge)
            $.get("getPage/"+current,function (data,status) {
                var obj = data;
                if(obj.MESSAGE != "false" && data != null){
                    var page = obj.data;
                    pageHtml(page,4);
                }else if(status != "success"){
                    error(status);
                } else {
                    nullHtml();
                }
                wirtenCourseHtml("getCourseList/"+current);
            });
        else
            $.get("getPartPage/"+current,function (data,status) {
                var obj = data;
                if(obj.MESSAGE != "false" && data != null){
                    var page = obj.data;
                    pageHtml(page,4);
                }else if(status != "success"){
                    error(status);
                } else {
                    nullHtml();
                }
                wirtenPartHtml("getPartByPage/"+current);
            });
    }
}
function addCourse() {
	$("#text").empty();
    var cid = $("#courseid").val();
    var cname = $("#coursename").val();
    var arg={cid,cname};
    evalution(arg);
    $.post("insertCourse",{cid:cid,name:cname},function (data,status) {    	
        if(data.data == "success" && status == "success"){
            alert("添加成功");
        }else {
            alert(data.message);
        }
    });
    var cid = $("#courseid").val("");
    var cname = $("#coursename").val("");
}
function addPart() {
	$("#text").empty();
    var pid = $("#coursepartid").val();
    var cid = $("#courseid").val();
    var pname = $("#coursepartname").val();
    var html = $("#courseparthtml").val();
    console.log(html);
    alert(html);
    var arg = {pid,pname,cid,html};
    evalution(arg);
    $.post("insertCoursePart",{pid:pid,partname:pname,id:cid,html:html},function (data,status) {
        if(data.data == "success" && status == "success"){
            alert("添加成功");
        }else {
            alert(data.message);
        }
    });
    var pid = $("#coursepartid").val("");
    var pname = $("#coursepartname").val("");
    var cid = $("#courseid").val("");
    var html = $("#courseparthtml").val("");
}
function deleteCourse() {
	$("#text").empty();
    var cid = $("#courseid").val();
    var arg = {cid};
    evalution(arg);
    $.get("deleteCourse/"+cid,function (values,status) {
        if(values.data == "success"){
            alert("删除成功");
        }else{
            alert(values.data.message);
        }
    });
    var cid = $("#courseid").val("");
}
function deleteCoursePart() {
	$("#text").empty();
    var pid = $("#coursepartid").val();
    var arg = {pid};
    evalution(arg);
    $.get("deleteCoursePart/"+pid,function (values,status) {
        if(values.data == "success"){
            alert("删除成功");
        }else{
            alert(values.data.message);
        }
    });
    var pid = $("#coursepartid").val("");
}
function updateCourse() {
	$("#text").empty();
    var cid = $("#courseid").val();
    var cname = $("#coursename").val();
    var arg = {cid,cname};
    evalution(arg);
    $.post("updateCourse",{name:cname,cid:cid},function (data,status) {
        if(data.data == "success" && status == "success"){
            alert("更新成功");
        }else {
            alert(data.message);
        }
    });
    var cid = $("#courseid").val("");
    var cname = $("#coursename").val("");
}
function updateCoursePart() {
	$("#text").empty();
    var pid = $("#coursepartid").val();
    var pname = $("#coursepartname").val();
    var html = $("#courseparthtml").val();
    var arg = {pid,pname,cid,html};
    evalution(arg);
    $.post("updateCoursePart",{pid:pid,partname:pname,html:html},function (data,status) {
        if(data.data == "success" && status == "success"){
            alert("更新成功");
        }else {
            alert(data.message);
        }
    });
    var pid = $("#coursepartid").val("");
    var pname = $("#coursepartname").val("");
    var cid = $("#courseid").val("");
    var html = $("#courseparthtml").val("");
}
function wirtenCourseHtml(url) {
    $("#content>.form-group").attr({hidden:"hidden"});
    $("#text").empty();
    $.get(url,function (values,status) {
        var data = values.data;
        for(var item=0;item<data.length;item++) {
            var cid = data[item].cid;
            var cname = data[item].name;
            var html = "<p><h5>课程ID:"+cid+"&emsp;&emsp;&emsp;&emsp;课程名称:"+cname+"</h5></p>";
            $("#text").append(html);
        }
    });
}
function wirtenPartHtml(url) {
    $("#content>.form-group").attr({hidden:"hidden"});
    $("#text").empty();
    $.get(url,function (values,status) {
        var data = values.data;
        for (var item = 0; item < data.length; item++) {
            var pid = data[item].pid;
            var pname = data[item].partname;
            var cid = data[item].id
            var html = "<p><h5>" + "章节ID:" + pid + "&emsp;&emsp;&emsp;&emsp;" + "章节名称:" + pname +
                "&emsp;&emsp;&emsp;&emsp;课程ID:" + cid + "</h5></p>";
            $("#text").append(html);
        }
    });
}
function pageHtml(page,max) {
	var total = page.totalPage;
    var next = page.nextPage;
    var pre = page.prePage;
    var current = page.currentPage;
	var nextli = "<li class=\"page-item\"><a class=\"page-link\" id=\""+next+"\">下一页</a></li>";
    var preli = "<li class=\"page-item\"><a class=\"page-link\" id=\""+pre+"\">上一页</a></li>";   
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
            var pageli = "<li class=\"page-item\"><a class=\"page-link\" id=\""+item+"\">"+item+"</a></li>";
            $("#pageul").append(pageli);
        }
    }else{
    	var pageli = "<li class=\"page-item\"><a class=\"page-link\" id=\"1\">1</a></li>";
        $("#pageul").append(pageli);
    }
    $("#pageul").append(nextli);
    $("#pageul *").each(function () {
        $(this).bind("click",function () {
            clickevent($(this))
        })
    });
}
function evalution(arg){
	var flag=0;
	for(var item in arg){
		if(arg[item] == "")
			flag = 1;
		if(arg[item] == "null" || arg[item] == "NULL")
			flag = 2;
	}
	if(flag == 1)
		alert("输入不能为空");
	if(flag == 2)
		alert("输入包含非法参数");
}	