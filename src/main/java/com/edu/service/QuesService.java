package com.edu.service;

import java.util.List;

import com.edu.entity.Question;

public interface QuesService {

	List<Question> findByPage(int offset, int parseInt, Integer courseId);

	int count(Integer courseId);

	void insertQues(Question question);

	void deleteByQuesId(int questionId);

	Question findByQuesId(int questionId);

	void updateQuestion(Question question);

	List<Question> findByCourseId(Integer courseId);


}
