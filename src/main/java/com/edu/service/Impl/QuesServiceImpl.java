package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.dao.QuestionDao;
import com.edu.entity.Question;
import com.edu.service.QuesService;

@Service
@Transactional
public class QuesServiceImpl implements QuesService{
	@Resource
	QuestionDao questionDao;

	@Override
	public List<Question> findByPage(int offset, int pageSize, Integer courseId) {
		// TODO Auto-generated method stub
		return questionDao.findByPage(offset,pageSize,courseId);
	}

	@Override
	public int count(Integer courseId) {
		// TODO Auto-generated method stub
		return questionDao.findByCourseId(courseId).size();
	}

	@Override
	public void insertQues(Question question) {
		questionDao.save(question);// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByQuesId(int questionId) {
		questionDao.myDeleteByQuesId(questionId);// TODO Auto-generated method stub
		
	}

	@Override
	public Question findByQuesId(int questionId) {
		// TODO Auto-generated method stub
		return questionDao.findByQuesId(questionId);
	}

	@Override
	public void updateQuestion(Question question) {
		questionDao.saveAndFlush(question);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Question> findByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		return questionDao.findByCourseId(courseId);
	}



}
