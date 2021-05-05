package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction; 
import org.springframework.stereotype.Service;

import com.edu.dao.CourseDao;
import com.edu.entity.Course;
import com.edu.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseDao courseDao;
	
	public List<Course> findCourse(Integer userId){
		return courseDao.findByUserId(userId);
	}

	@Override
	public List<Course> findByPage(int offset, int pageSize,Integer userId) {
		// TODO Auto-generated method stub
		return courseDao.findByPage(offset,pageSize,userId);
	}

	@Override
	public int count(Integer userId) {
		// TODO Auto-generated method stub
		return courseDao.findByUserId(userId).size();
	}

	@Override
	public Course findByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		return courseDao.findByCourseId(courseId);
	}
	


}
