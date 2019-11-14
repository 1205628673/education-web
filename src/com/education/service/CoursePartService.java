package com.education.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.education.po.CoursePart;

public interface CoursePartService {
	public List<CoursePart> getAllCoursePart();
	public CoursePart getCoursePartByPid(Integer pid);
	public CoursePart getCoursePartByCid(Integer cid);
	public List<CoursePart> getCoursePartByPage(Integer current);
	public List<Map<Integer, String>> getAllPartNameAndPid(Integer cid);
	public void insertCoursePart(CoursePart coursePart);
	public void deleteCoursePartByPid(Integer pid);
	public void updateCoursePartByPid(CoursePart coursePart);
}
