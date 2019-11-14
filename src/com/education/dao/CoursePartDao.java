package com.education.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.education.po.CoursePart;

public interface CoursePartDao {
	public List<CoursePart> findAllCoursePart();
	public CoursePart findCoursePartByPid(@Param("pid")Integer pid);
	public CoursePart findCoursePartByCid(@Param("cid") Integer cid);
	public List<CoursePart> findCoursePartByPage(@Param("current")Integer current);
	public List<Map<Integer, String>> findCoursePartKeyVal(@Param("cid")Integer cid);
	public Integer insertCoursePart(CoursePart coursePart) throws Exception;
	public Integer deleteCoursePartByPid(@Param("pid")Integer pid)throws Exception;
	public Integer updateCoursePartByPid(CoursePart coursePart)throws Exception;
}
