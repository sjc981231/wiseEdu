package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.dao.LessonDao;
import com.edu.entity.Lesson;
import com.edu.service.LessonService;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

	@Resource
	LessonDao lessonDao;

	@Override
	public List<Lesson> findByPage(int offset, int pageSize, Integer courseId) {
		// TODO Auto-generated method stub
		return lessonDao.findByPage(offset,pageSize,courseId);
	}

	@Override
	public int count(Integer courseId) {
		// TODO Auto-generated method stub
		return lessonDao.findByCourseId(courseId).size();
	}

	@Override
	public void insertLesson(Lesson lesson) {
		lessonDao.save(lesson);
		
	}

	@Override
	public void deleteByLessonId(int lessonId) {
		// TODO Auto-generated method stub
		lessonDao.myDeleteByLessonId(lessonId);
	}

	@Override
	public Lesson findByLessonId(int lessonId) {
		// TODO Auto-generated method stub
		return lessonDao.findByLessonId(lessonId);
	}

	@Override
	public void updateLesson(Lesson lesson) {
		// TODO Auto-generated method stub
		lessonDao.saveAndFlush(lesson);
		
	}
}
