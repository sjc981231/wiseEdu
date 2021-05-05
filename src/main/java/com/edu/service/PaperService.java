package com.edu.service;

import java.util.List;

import com.edu.entity.Paper;

public interface PaperService {

	List<Paper> findByPage(int offset, int pageSize, Integer courseId);

	int count(Integer courseId);

	void insertPaper(Paper paper);

	void deleteByPaperId(int parseInt);

	Paper findByPaperId(int paperId);

	void updatePaper(Paper paper);

}
