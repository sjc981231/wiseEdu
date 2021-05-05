package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.dao.QuesKnowDao;
import com.edu.entity.Knowledge;
import com.edu.entity.QuesKnow;
import com.edu.service.QuesKnowService;

@Service
@Transactional
public class QuesKnowServiceImpl implements QuesKnowService {
	@Resource
	QuesKnowDao quesKnowDao;

	@Override
	public List<Knowledge> findKnowledgeByQuesId(int quesId) {
		// TODO Auto-generated method stub
		return quesKnowDao.findKnowledgeByQuesId(quesId);
	}

	@Override
	public void deleteByQuesId(int quesId) {
		// TODO Auto-generated method stubr
		quesKnowDao.deleteByQuesId(quesId);
		
	}

	@Override
	public void save(QuesKnow rf) {
		// TODO Auto-generated method stub
		quesKnowDao.save(rf);
	}
	
	

}
