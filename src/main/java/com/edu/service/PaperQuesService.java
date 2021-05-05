package com.edu.service;

import java.util.List;

import com.edu.entity.PaperQues;
import com.edu.entity.Question;

public interface PaperQuesService {

	List<Question> findQuesByPaperId(int paperId);

	void deleteByPaperId(int paperId);

	void save(PaperQues rf);

}
