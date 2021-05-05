package com.edu.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.edu.entity.User;
import com.edu.service.CourseService;




@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Resource
	private CourseService courseService;

	@RequestMapping("/index")
	public String teacher_index() {
		return "/teacher/index";
	}
	
	@RequestMapping("/courseMenu")
	public String view_teacher() {
		return "/teacher/courseMenu";
	}
	
	@RequestMapping("/courseFindByPage.do")
	@ResponseBody
	public Map<String, Object> findByPage(HttpServletRequest request,HttpServletResponse response) {
//		User user=(User) request.getSession().getAttribute("user");
//		Integer userId=user.getId();
		Integer userId=1;		//测试用
		Map<String, Object> resultMap = new HashMap();
		//分页查询 获取当前页和每页条数
		String pageNum = request.getParameter("page"); //当前页
		String pageSize = request.getParameter("rows"); //每页多少条数据
		
		if(pageNum == null || "".equals(pageNum)){
			pageNum = "1";
		}
		if(pageSize == null || "".equals(pageSize)){
			pageSize = "10";
		}
		//计算开始的记录数据
		int offset = (Integer.parseInt(pageNum) - 1) * Integer.parseInt(pageSize);
		//从数据库中获取用户数据
		List<Course> list = courseService.findByPage(offset, Integer.parseInt(pageSize),userId);
		//获取用户表的总记录条数
		int total = courseService.count(userId);
		
		
		resultMap.put("rows", list);
		resultMap.put("total", total);
		resultMap.put("msg", "获取用列表成功");
		
		return resultMap;
	}
	
	@RequestMapping("/enterCourseMenu.do")
	@ResponseBody
	public Map<String, Object> enterCourseMenu(String courseId,HttpServletRequest request) {
	    //设置返回信息
	    Map<String, Object> resultMap = new HashMap();
	    resultMap.put("errCode",0); //错误码
	    resultMap.put("errMsg",null);//错误信息
	    
			HttpSession session = request.getSession();
				Course course = courseService.findByCourseId(Integer.valueOf(courseId));
			session.setAttribute("course",course);
			
			resultMap.put("success",true);
			resultMap.put("msg", "成功进入菜单！");


	    return resultMap;

	}
	
}
