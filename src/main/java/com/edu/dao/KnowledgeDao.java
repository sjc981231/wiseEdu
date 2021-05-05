package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.entity.Knowledge;

public interface KnowledgeDao extends JpaRepository<Knowledge, Integer>{
	
	
	@Query("select c from Knowledge c where c.courseId= :courseId")
	List<Knowledge> findKnowledgeList(@Param(value="courseId")Integer courseId);

	Knowledge findByKnowledgeName(String knowledgeName);

	List<Knowledge> findByParentId(int parentId);

	@Modifying
    @Query("update Knowledge c set c.status = 0 where c.knowledgeId = :knowledgeId")
	void myDeleteByKnowledgeId(@Param(value="knowledgeId")int knowledgeId);

	Knowledge findByKnowledgeId(int knowledgeId);

	@Query("select c from Knowledge c where c.knowledgeId != :knowledgeId")
	List<Knowledge> findTreeByKnowledgeId(@Param(value="knowledgeId")Integer knowledgeId);

	
}
