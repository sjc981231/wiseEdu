package com.edu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuesKnow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer qkId;
	
	private Integer quesId;
	private Integer knowledgeId;//1填空题2选择题3简答题4判断题
	public Integer getQkId() {
		return qkId;
	}
	public void setQkId(Integer qkId) {
		this.qkId = qkId;
	}
	public Integer getQuesId() {
		return quesId;
	}
	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
	}
	public Integer getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
}
