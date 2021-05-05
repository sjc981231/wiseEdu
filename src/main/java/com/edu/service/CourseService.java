package com.edu.service;

import java.util.List;

import com.edu.entity.Course;

public interface CourseService {
	
	List<Course> findCourse(Integer userId);

	List<Course> findByPage(int offset, int pageSize, Integer userId);

	int count(Integer userId);

	Course findByCourseId(Integer courseId);
	
}
