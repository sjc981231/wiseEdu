package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Course;
import com.edu.entity.Paper;
import com.edu.entity.StudentCourse;
import com.edu.service.CourseService;
import com.edu.service.StudentCourseService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Resource
	StudentCourseService studentCourseService;
	
	@Resource
	CourseService courseService;
	
	@RequestMapping("/index")
	public String student_index(HttpServletRequest request) {
//		User user=(User) request.getSession().getAttribute("user");
//		Integer userId=user.getId();
		Integer userId=2;		//测试用
		if(userId != null && !"".equals(userId)){
			//通过id查询当前菜单权限信息
			List<StudentCourse> studentCourses = studentCourseService.findByStudentId(userId);
			request.setAttribute("studentCourses",studentCourses);
			System.out.println(studentCourses);
		}
		
		return "/student/studentIndex";
	}
	
	@RequestMapping("/courseLearning")
	public String student_courseLearning(HttpServletRequest request) {

		String courseId = request.getParameter("courseId");
		HttpSession session = request.getSession();
		Course course = courseService.findByCourseId(Integer.valueOf(courseId));
		session.setAttribute("course",course);
		request.setAttribute("courseId",courseId);

		
		return "/student/courseLearning";
	}

}
