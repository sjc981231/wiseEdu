package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.entity.Knowledge;
import com.edu.entity.QuesKnow;

public interface QuesKnowDao extends JpaRepository<QuesKnow, Integer>{

	@Query("select k from QuesKnow c,Knowledge k where c.quesId= :quesId and k.knowledgeId=c.knowledgeId")
	List<Knowledge> findKnowledgeByQuesId(int quesId);

	void deleteByQuesId(int quesId);

}
