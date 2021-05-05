package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ViewController {
	
	@RequestMapping("/login")
	public String LoginView() {
		return "login";
	}
	
	@RequestMapping("/{page}")
	public String view(@PathVariable(name = "page") String page) {
		return page;
	}
	
//	@RequestMapping("/teacher/{page}")
//	public String view_teacher(@PathVariable(name = "page") String page) {
//		return "teacher/"+page;
//	}
	
//	@RequestMapping("/student/{page}")
//	public String student(@PathVariable(name = "page") String page) {
//		return "student/"+page;
//	}
//	
}
