package com.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.entity.QuesFill;

public interface QuesFillDao extends JpaRepository<QuesFill, Integer>{

	QuesFill findByQuesId(int quesId);

}
