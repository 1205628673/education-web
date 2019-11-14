package com.education.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.po.Course;
import com.education.po.CoursePart;
import com.education.util.*;

import com.education.service.CoursePartService;
import com.education.service.CourseService;

@Controller
public class CourseController {
	/*
	 * 所有课程系列的拦截器
	 */
	@Autowired
	CourseService courseService;
	@Autowired
	CoursePartService coursePartService;
	@Autowired
	Page page;
	@RequestMapping("/main")//跳主页
	public String toMainPage() {
		System.out.println("跳到主页");
		return "index";
	}
	@RequestMapping("/course/{cid}")//跳课程内容
	public String AndtoCourse() {
		System.out.println("and跳到课程");
		return toCourse();
	}
	@RequestMapping("/course/{cid}/{pid}")//跳课程内容
	public String toCourse() {
		System.out.println("跳到课程");
		return "course";
	}
	@RequestMapping("/product")//跳到新闻
	public String toNews() {
		System.out.println("跳到新闻");
		return "news";
	}
	@RequestMapping("/knowme")//跳关于我们
	public String toAboutMe() {
		System.out.println("关于我们");
		return "aboutme";
	}
	@RequestMapping("/call")//跳联系我们
	public String toCallOur() {
		System.out.println("联系我们");
		return "callour";
	}
	@RequestMapping("/introduce")//跳团队介绍
	public String toGroup() {
		System.out.println("团队介绍");
		return "group";
	}

	@RequestMapping("/allcourse/{cid}")
	public String toCourseList() {
		return "courselist";
	}
	@RequestMapping("/getCourseList/{current}")//按页数返回Course
	@ResponseBody//AJAX访问该路径，返回ReturnMessage对象，对象包含所需数据
	public ReturnMessage<List<Course>> getCourseList(@PathVariable Integer current) {
		page.setPageSize(12);
		List<Course> courses = courseService.getCourseByPage((current-1)*page.getPageSize());
		ReturnMessage<List<Course>> message = new ReturnMessage<List<Course>>();
		message.setData(courses);
		return message;
	}
	@RequestMapping("/getCourseName/{cid}")
	@ResponseBody
	public ReturnMessage<Course> getCourseName(@PathVariable Integer cid) {
		Course courses = courseService.getCourseById(cid);
		ReturnMessage<Course> message = new ReturnMessage<Course>();
		message.setData(courses);
		return message;
	}
	@RequestMapping("/getPage/{current}")//返回Course的页
	@ResponseBody
	public ReturnMessage<Page> getCoursePage(@PathVariable Integer current){
		try {
		Page p = new Page();
		page.setCurrentPage(current);
		page.setPageSize(12);
		page.setTotalCourse(courseService.getAllCourse().size());
		p.setPrePage(page.getPrePage());
		p.setNextPage(page.getNextPage());
		p.setTotalPage(page.getTotalPage());
		p.setCurrentPage(current);
		ReturnMessage<Page> message = new ReturnMessage<Page>();
		message.setData(p);
		return message;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ReturnMessage<Page>();
			
		}
	}
	@RequestMapping("/getPartPage/{current}")//返回Part的页
	@ResponseBody
	public ReturnMessage<Page> getPartPage(@PathVariable Integer current){
		try {
			Page p = new Page();
			page.setCurrentPage(current);
			page.setPageSize(12);
			page.setTotalCourse(coursePartService.getAllCoursePart().size());
			p.setPrePage(page.getPrePage());
			p.setNextPage(page.getNextPage());
			p.setTotalPage(page.getTotalPage());
			p.setCurrentPage(current);
			ReturnMessage<Page> message = new ReturnMessage<Page>();
			message.setData(p);
			return message;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ReturnMessage<Page>();
				
			}
		}
	@RequestMapping("/getPartByPage/{current}")//按页返回part
	@ResponseBody
	public ReturnMessage<List<CoursePart>> getCoursePartByPage(@PathVariable Integer current){
		page.setPageSize(12);
		List<CoursePart> courseParts = coursePartService.getCoursePartByPage((current-1)*page.getPageSize());
		ReturnMessage<List<CoursePart>> message = new ReturnMessage<List<CoursePart>>();
		message.setData(courseParts);
		return message;
	}
	@RequestMapping("/getCourse/{pid}")
	@ResponseBody
	public ReturnMessage<CoursePart> getCoursePart(@PathVariable Integer pid){
		CoursePart coursePart = new CoursePart();
		coursePart = coursePartService.getCoursePartByPid(pid);
		ReturnMessage<CoursePart> message = new ReturnMessage<CoursePart>();
		message.setData(coursePart);
		return message;
	}
	@RequestMapping("/getCourseByCid/{cid}")
	@ResponseBody
	public ReturnMessage<CoursePart> getCoursePartByCid(@PathVariable Integer cid){
		CoursePart coursePart = coursePartService.getCoursePartByCid(cid);
		ReturnMessage<CoursePart> message = new ReturnMessage<CoursePart>();
		message.setData(coursePart);
		return message;
	}
	@RequestMapping("/getCourseKeyVal/{cid}")
	@ResponseBody
	public ReturnMessage<List<Map<Integer, String>>> getCousePartKeyVal(@PathVariable Integer cid){
		List<Map<Integer, String>> keyValList = coursePartService.getAllPartNameAndPid(cid);
		ReturnMessage<List<Map<Integer, String>>> message = new ReturnMessage<List<Map<Integer, String>>>();
		message.setData(keyValList);
		return message;
	}
	@RequestMapping("/deleteCoursePart/{pid}")
	@ResponseBody
	public ReturnMessage<String> deleteCourseByPid(@PathVariable Integer pid){
		ReturnMessage<String> message = new ReturnMessage<String>();
		message.setData("success");
		try {
			coursePartService.deleteCoursePartByPid(pid);
		}catch (Exception e) {
			// TODO: handle exception
			message.setData("error");
			message.setMESSAGE("不存在该章节");
		}
		return message;
	}
	@RequestMapping("/deleteCourse/{cid}")
	@ResponseBody
	public ReturnMessage<String> deleteCourseByCid(@PathVariable Integer cid){
		ReturnMessage<String> message = new ReturnMessage<String>();
		message.setData("success");
		try {
			courseService.deleteCourseByCid(cid);
		}catch (Exception e) {
			// TODO: handle exception
			message.setData("error");
			message.setMESSAGE("不存在该课程");
		}
		return message;
	}
	@RequestMapping("/insertCoursePart")
	@ResponseBody
	public ReturnMessage<String> insertCoursePart(CoursePart coursePart){
		ReturnMessage<String> message = new ReturnMessage<String>();
		message.setData("success");
		try {
			coursePartService.insertCoursePart(coursePart);
		}catch (Exception e) {
			// TODO: handle exception
			message.setData("error");
			message.setMESSAGE("插入主键重复或不存在该课程");
			
		}
		return message;
	}
	@RequestMapping("/insertCourse")
	@ResponseBody
	public ReturnMessage<String> insertCourse(Course course){
		ReturnMessage<String> message = new ReturnMessage<String>();
		message.setData("success");
		try {
			courseService.insertCourse(course);
		}catch (Exception e) {
			// TODO: handle exception
			message.setData("error");
			message.setMESSAGE("插入主键重复");
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("/updateCoursePart")
	public ReturnMessage<String> updateCoursePartByPid(CoursePart coursePart){
		ReturnMessage<String> message = new ReturnMessage<String>();
		message.setData("success");
		try {
			coursePartService.updateCoursePartByPid(coursePart);
		}catch (Exception e) {
			// TODO: handle exception
			message.setData("error");
			message.setMESSAGE("不存在该章节或外键约束");
			System.out.println(e);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping("/updateCourse")
	public ReturnMessage<String> updateCourseByCid(Course course){
		ReturnMessage<String> message = new ReturnMessage<String>();
		message.setData("success");
		try {
			courseService.updateCourseByCid(course);
		}catch (Exception e) {
			// TODO: handle exception
			message.setData("error");
			message.setMESSAGE("不存在该课程");
		}
		return message;
	}
}
