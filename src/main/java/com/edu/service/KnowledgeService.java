package com.edu.service;

import java.util.List;

import com.edu.entity.Knowledge;

public interface KnowledgeService {

	List<Knowledge> findKnowledgeList(Integer courseId);

	Knowledge findByKnowledgeName(String knowledgeName);

	void insertKnowledge(Knowledge knowledge);

	List<Knowledge> findByParentId(int parseInt);

	void deleteByKnowledgeId(int parseInt);

	Knowledge findByKnowledgeId(int knowledgeId);

	List<Knowledge> findTreeByKnowledgeId(Integer knowledgeId);

	void updateKnowledge(Knowledge knowledge);

}
