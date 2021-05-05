package com.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.QuesKnow;
import com.edu.entity.Question;
import com.edu.entity.StudentCourse;
import com.edu.entity.User;
import com.edu.service.StudentCourseService;

@Controller
@RequestMapping("/teacher")
public class StudentManagerController {
	
	@Resource
	StudentCourseService studentCourseService;

	@RequestMapping("/studentList")
	public String studentList() {
		return "/teacher/studentList";
	}
	
	@RequestMapping("/studentFindByPage.do")
	@ResponseBody
	public Map<String, Object> studentFindByPage(HttpServletRequest request,HttpServletResponse response) {
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
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
		List<User> list = studentCourseService.findByPage(offset, Integer.parseInt(pageSize),courseId);
		//获取用户表的总记录条数
		int total = studentCourseService.count(courseId);
		System.out.println("result:"+list+total);
		
		resultMap.put("rows", list);
		resultMap.put("total", total);
		resultMap.put("msg", "获取用列表成功");
		
		return resultMap;
	}
	
	@RequestMapping("/delStudent.do")
	@ResponseBody
	public Map<String, Object> delStudentById(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap();
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		String ids = request.getParameter("ids");
		String[] ids2 = ids.split(",");
		String msg = "";
		if(ids2 != null && ids2.length > 0){
			for(String id : ids2){

				studentCourseService.deleteByCourseIdAndStudentId(courseId,Integer.parseInt(id));
			
			}
			resultMap.put("msg","删除成功!");
			resultMap.put("success", true);

		}else{
			resultMap.put("msg","获取删除id失败!");
			resultMap.put("success", false);
		}
		return resultMap;
	}
	
	@RequestMapping("/addStudent.do")
	public String addStudent(HttpServletRequest request){

		return "teacher/addStudent";
	}
	
	@RequestMapping("/findStudentList.do")
	@ResponseBody
	public Map<String, Object> findStudentList(HttpServletRequest request,HttpServletResponse response) {
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		Map<String, Object> resultMap = new HashMap();


		//从数据库中获取用户数据
		List<User> list = studentCourseService.findStudentNotIn(courseId);
		//获取用户表的总记录条数
		
		resultMap.put("data", list);
		resultMap.put("msg", "获取用列表成功");
		
		return resultMap;
	}
	
	@RequestMapping("/save_newInStudent.do")
	@ResponseBody
	public Map<String, Object> save_newInStudent(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap();
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		String ids = request.getParameter("ids");
		String[] ids2 = ids.split(",");
		String msg = "";
		if(ids2 != null && ids2.length > 0){
			for(String id : ids2){

				StudentCourse rf=new StudentCourse();
				rf.setStudentId(Integer.parseInt(id));
				rf.setCourseId(courseId);
				studentCourseService.addNewInStudent(rf);
			
			}
			resultMap.put("msg","添加成功!");
			resultMap.put("success", true);

		}else{
			resultMap.put("msg","获取添加id失败!");
			resultMap.put("success", false);
		}
		return resultMap;
	}
}
