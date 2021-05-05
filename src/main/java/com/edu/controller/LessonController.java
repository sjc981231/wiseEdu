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

import com.edu.entity.Lesson;
import com.edu.service.LessonService;

@Controller
@RequestMapping("/teacher")
public class LessonController {
	
	@Resource
	LessonService lessonService;

	@RequestMapping("/lessonList")
	public String view_lessonList() {
		return "/teacher/lessonList";
	}
	
	@RequestMapping("/lessonFindByPage.do")
	@ResponseBody
	public Map<String, Object> findByPage(HttpServletRequest request,HttpServletResponse response) {
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
		List<Lesson> list = lessonService.findByPage(offset, Integer.parseInt(pageSize),courseId);
		//获取用户表的总记录条数
		int total = lessonService.count(courseId);
		
		
		resultMap.put("rows", list);
		resultMap.put("total", total);
		resultMap.put("msg", "获取用列表成功");
		
		return resultMap;
	}
		
	@RequestMapping("/addLesson.do")
	public String addLesson(HttpServletRequest request){

		return "teacher/addLesson";
	}
	
	@RequestMapping("/saveLesson.do")
	@ResponseBody
	public Map<String, Object> saveLesson(Lesson lesson,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap();
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		if(lesson != null){
			lesson.setCourseId(courseId);

			//设置创建时间
			//保存权限信息
			lessonService.insertLesson(lesson);
			resultMap.put("success", true);
			resultMap.put("msg", "问题添加成功");
		
		
		}else{
			resultMap.put("success", false);
			resultMap.put("msg", "获取问题信息失败！");
		}
		
		
			return resultMap;
	}
	
	@RequestMapping("/delLesson.do")
	@ResponseBody
	public Map<String, Object> delLessonById(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap();
		String ids = request.getParameter("ids");
		String[] ids2 = ids.split(",");
		String msg = "";
		if(ids2 != null && ids2.length > 0){
			for(String id : ids2){

				lessonService.deleteByLessonId(Integer.parseInt(id));
			
			}
			resultMap.put("msg","删除成功!");
			resultMap.put("success", true);

		}else{
			resultMap.put("msg","获取删除id失败!");
			resultMap.put("success", false);
		}
		return resultMap;
	}
	
	@RequestMapping("/updateLesson.do")
	public String updateQues(HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && !"".equals(id)){
			//通过id查询当前菜单权限信息
			Lesson lesson = lessonService.findByLessonId(Integer.parseInt(id));
			request.setAttribute("lesson",lesson);
			System.out.println(lesson);
		}
		return "teacher/editLesson";
	}	
	
	@RequestMapping("/updateLessonById.do")
	@ResponseBody
	public Map<String,Object> updateLessonById(Lesson lesson,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap();

		if(lesson != null){

			lessonService.updateLesson(lesson);
			resultMap.put("success", true);
			resultMap.put("msg", "权限修改成功");
		}

		
		return resultMap;
	}
	
}
