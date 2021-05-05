package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.dao.StudentCourseDao;
import com.edu.dao.UserDao;
import com.edu.entity.StudentCourse;
import com.edu.entity.User;
import com.edu.service.StudentCourseService;

@Service
@Transactional
public class StudentCourseServiceImpl implements StudentCourseService {

	@Resource
	StudentCourseDao studentCourseDao;
	
	@Resource
	UserDao userDao;

	@Override
	public List<User> findByPage(int offset, int parseInt, Integer courseId) {
		// TODO Auto-generated method stub
		return userDao.findByPage(offset,parseInt,courseId);
	}

	@Override
	public int count(Integer courseId) {
		// TODO Auto-generated method stub
		return studentCourseDao.findByCourseId(courseId).size();
	}

	@Override
	public void deleteByCourseIdAndStudentId(Integer courseId, int studentId) {
		// TODO Auto-generated method stub
		studentCourseDao.deleteByCourseIdAndStudentId(courseId,studentId);
		
	}

	@Override
	public List<User> findStudentNotIn(Integer courseId) {
		// TODO Auto-generated method stub
		return studentCourseDao.findByCourseIdNot(courseId);
	}

	@Override
	public void addNewInStudent(StudentCourse rf) {
		// TODO Auto-generated method stub
		studentCourseDao.saveAndFlush(rf);
	}

	@Override
	public List<StudentCourse> findByStudentId(Integer studentId) {
		// TODO Auto-generated method stub
		return studentCourseDao.findByStudentId(studentId);
	}


	
	
	
}
