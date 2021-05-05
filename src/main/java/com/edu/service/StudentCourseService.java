package com.edu.service;

import java.util.List;

import com.edu.entity.StudentCourse;
import com.edu.entity.User;

public interface StudentCourseService {

	List<User> findByPage(int offset, int parseInt, Integer courseId);

	int count(Integer courseId);

	void deleteByCourseIdAndStudentId(Integer courseId, int studentId);

	List<User> findStudentNotIn(Integer courseId);


	void addNewInStudent(StudentCourse rf);

	List<StudentCourse> findByStudentId(Integer studentId);

}
