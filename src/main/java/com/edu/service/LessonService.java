package com.edu.service;

import java.util.List;

import com.edu.entity.Lesson;

public interface LessonService {

	List<Lesson> findByPage(int offset, int pageSize, Integer courseId);

	int count(Integer courseId);

	void insertLesson(Lesson lesson);

	void deleteByLessonId(int lessonId);

	Lesson findByLessonId(int lessonId);

	void updateLesson(Lesson lesson);

}
