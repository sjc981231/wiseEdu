package com.edu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaperQues {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pqId;
	
	private Integer paperId;
	private Integer quesId;
	public Integer getPqId() {
		return pqId;
	}
	public void setPqId(Integer pqId) {
		this.pqId = pqId;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getQuesId() {
		return quesId;
	}
	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
	}
	
	
	
}
