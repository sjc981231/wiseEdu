package com.edu.service;

import java.util.List;

import com.edu.entity.Knowledge;
import com.edu.entity.QuesKnow;

public interface QuesKnowService {

	List<Knowledge> findKnowledgeByQuesId(int parseInt);

	void deleteByQuesId(int quesId);

	void save(QuesKnow rf);

}
