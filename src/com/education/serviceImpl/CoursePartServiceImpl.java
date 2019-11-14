package com.education.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.education.dao.CoursePartDao;
import com.education.po.CoursePart;
import com.education.service.CoursePartService;
import com.education.service.CourseService;
import com.sun.jdi.IntegerType;
@Transactional
@Service
public class CoursePartServiceImpl implements CoursePartService {
	@Autowired
	CoursePartDao coursePartDao;
	@Override
	public CoursePart getCoursePartByPid(Integer pid) {
		// TODO Auto-generated method stub		
		CoursePart coursePart = coursePartDao.findCoursePartByPid(pid);		
			// TODO: handle exception
		return coursePart;		
	}

	@Override
	public List<Map<Integer, String>> getAllPartNameAndPid(Integer cid) {
		// TODO Auto-generated method stub
		List<Map<Integer, String>> KeyValList = coursePartDao.findCoursePartKeyVal(cid);
		return KeyValList;
	}

	@Override
	public void insertCoursePart(CoursePart coursePart) {
		// TODO Auto-generated method stub
		try {
			Integer flag = coursePartDao.insertCoursePart(coursePart);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("插入章节错误",e);
		}
	}

	@Override
	public void deleteCoursePartByPid(Integer pid) {
		// TODO Auto-generated method stub
		try {
			Integer flag = coursePartDao.deleteCoursePartByPid(pid);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("删除章节错误",e);
		}
	}

	@Override
	public void updateCoursePartByPid(CoursePart coursePart) {
		// TODO Auto-generated method stub
		try {
			Integer flag = coursePartDao.updateCoursePartByPid(coursePart);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("更新章节错误",e);
		}
	}

	@Override
	public CoursePart getCoursePartByCid(Integer cid) {
		// TODO Auto-generated method stub
		CoursePart coursePart = coursePartDao.findCoursePartByCid(cid);
		return coursePart;
	}

	@Override
	public List<CoursePart> getCoursePartByPage(Integer current) {
		// TODO Auto-generated method stub
		List<CoursePart> courseParts = coursePartDao.findCoursePartByPage(current);
		return courseParts;
	}

	@Override
	public List<CoursePart> getAllCoursePart() {
		// TODO Auto-generated method stub
		List<CoursePart> courseParts = coursePartDao.findAllCoursePart();
		return courseParts;
	}

}
