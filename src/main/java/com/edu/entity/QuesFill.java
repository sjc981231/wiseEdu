package com.edu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuesFill {
 


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer quesFillId;
	
	private String question;
	private String answer;
	
	private Integer quesId;
	
	public Integer getQuesFillId() {
		return quesFillId;
	}


	public void setQuesFillId(Integer quesFillId) {
		this.quesFillId = quesFillId;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public Integer getQuesId() {
		return quesId;
	}


	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
	}

}
