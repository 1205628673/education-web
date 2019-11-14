package com.education.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.education.po.CoursePart;
import com.education.po.*;
public interface CourseDao {
	public List<Course> findAllCourse();
	public Course findCourseById(Integer cid);
	public List<Course> findCourseByName(String name);
	public List<Course> findCourseByPage(Integer current);
	public Integer insertCourse(Course course)throws Exception;
	public Integer deleteCourseByCid(Integer cid)throws Exception;
	public Integer updateCourseByCid(Course course)throws Exception;
}
