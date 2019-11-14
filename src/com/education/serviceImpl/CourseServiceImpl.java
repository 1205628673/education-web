package com.education.serviceImpl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.dao.CourseDao;
import com.education.po.Course;
import com.education.po.CoursePart;
import com.education.service.CourseService;
@Transactional
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseDao courseDao;
	@Override
	public List<Course> getAllCourse() {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<Course>();
		courses = courseDao.findAllCourse();
		return courses;
	}

	@Override
	public List<Course> getSearchCourseByName(String name) {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<Course>();
		courses = courseDao.findCourseByName(name);
		return courses;
	}

	@Override
	public Course getCourseById(Integer cid) {
		// TODO Auto-generated method stub;
		Course course = courseDao.findCourseById(cid);
		return course;
	}

	@Override
	public List<Course> getCourseByPage(Integer current) {
		// TODO Auto-generated method stub
		List<Course> courses = courseDao.findCourseByPage(current);//limit子句从0开始,每翻一页就乘上pageSize;
		return courses;
	}

	@Override
	public void insertCourse(Course course) {
		// TODO Auto-generated method stub
		try {
			Integer flag = courseDao.insertCourse(course);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("插入课程错误",e);
		}
	}

	@Override
	public void deleteCourseByCid(Integer cid) {
		// TODO Auto-generated method stub
		try {
		Integer flag = courseDao.deleteCourseByCid(cid);
		}catch (Exception e) {
			throw new RuntimeException("删除课程错误",e);
			// TODO: handle exception
		}
		
	}

	@Override
	public void updateCourseByCid(Course course) {
		// TODO Auto-generated method stub
		try {
		Integer flag = courseDao.updateCourseByCid(course);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("更新课程错误",e);
		}
	}
}
