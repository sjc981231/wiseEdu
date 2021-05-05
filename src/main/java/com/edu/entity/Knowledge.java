package com.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Knowledge {
	@Override
	public String toString() {
		return "Knowledge [knowledgeId=" + knowledgeId + ", knowledgeName=" + knowledgeName + ", parentId=" + parentId
				+ ", status=" + status + ", courseId=" + courseId + "]";
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer knowledgeId;
	
	private String knowledgeName;
	private Integer parentId;
	 private Integer status;

	
	private Integer courseId;


	public Integer getKnowledgeId() {
		return knowledgeId;
	}


	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}


	public String getKnowledgeName() {
		return knowledgeName;
	}


	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}


	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getCourseId() {
		return courseId;
	}


	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	
}
