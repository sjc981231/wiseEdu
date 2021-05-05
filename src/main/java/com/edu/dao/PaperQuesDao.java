package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.entity.PaperQues;
import com.edu.entity.Question;

public interface PaperQuesDao extends JpaRepository<PaperQues, Integer> {

	@Query("select q from PaperQues c,Question q where c.paperId= :paperId and q.quesId=c.quesId")
	List<Question> findQuesByPaperId(int paperId);

	void deleteByPaperId(int paperId);

}
