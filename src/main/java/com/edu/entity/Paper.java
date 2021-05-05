package com.edu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paperId;
	
	private String title;
	
	private String limitTime;

	 private Integer status;

	
	private Integer courseId;


	public Integer getPaperId() {
		return paperId;
	}


	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLimitTime() {
		return limitTime;
	}


	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
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
