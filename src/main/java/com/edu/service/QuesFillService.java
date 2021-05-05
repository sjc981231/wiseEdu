package com.edu.service;

import com.edu.entity.QuesFill;

public interface QuesFillService {

	QuesFill findByQuesId(int quesId);

	void updateQuesFill(QuesFill quesFill);

}
