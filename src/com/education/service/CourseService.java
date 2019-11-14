package com.education.service;

import java.util.List;

import com.education.po.Course;
import com.education.po.CoursePart;

public interface CourseService {
	public List<Course> getAllCourse();
	public List<Course> getSearchCourseByName(String name);
	public Course getCourseById(Integer cid);
	public List<Course> getCourseByPage(Integer current);
	public void insertCourse(Course course);
	public void deleteCourseByCid(Integer cid);
	public void updateCourseByCid(Course course);
}
