package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.edu.dao.KnowledgeDao;
import com.edu.entity.Knowledge;
import com.edu.service.KnowledgeService;
@Service
@Transactional
public class KnowledgeServiceImpl implements KnowledgeService{

	@Resource
	KnowledgeDao knowledgeDao;
	
	@Override
	public List<Knowledge> findKnowledgeList(Integer courseId) {
		// TODO Auto-generated method stub
		return knowledgeDao.findKnowledgeList(courseId);
	}

	@Override
	public Knowledge findByKnowledgeName(String knowledgeName) {
		// TODO Auto-generated method stub
		return knowledgeDao.findByKnowledgeName(knowledgeName);
	}

	@Override
	public void insertKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		knowledgeDao.save(knowledge);
	}

	@Override
	public List<Knowledge> findByParentId(int parentId) {
		// TODO Auto-generated method stub
		return knowledgeDao.findByParentId(parentId);
	}

	@Override
	public void deleteByKnowledgeId(int knowledgeId) {
		knowledgeDao.myDeleteByKnowledgeId(knowledgeId);
		
	}

	@Override
	public Knowledge findByKnowledgeId(int knowledgeId) {
		// TODO Auto-generated method stub
		return knowledgeDao.findByKnowledgeId(knowledgeId);
	}

	@Override
	public List<Knowledge> findTreeByKnowledgeId(Integer knowledgeId) {
		// TODO Auto-generated method stub
		return knowledgeDao.findTreeByKnowledgeId(knowledgeId);
	}

	@Override
	public void updateKnowledge(Knowledge knowledge) {
		knowledgeDao.saveAndFlush(knowledge);
		
	}

}
