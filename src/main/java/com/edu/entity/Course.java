package com.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	
	@Column(length = 20)
	private String courseName;
	
	private Integer userId;
	

	

	//无参构造方法，这个必须要有，不然会报错
    public Course() {
        
    }




	public Integer getCourseId() {
		return courseId;
	}




	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}




	public String getCourseName() {
		return courseName;
	}




	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}




	public Integer getUserId() {
		return userId;
	}




	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
