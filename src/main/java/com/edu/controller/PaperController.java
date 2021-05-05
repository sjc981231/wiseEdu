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

import com.edu.service.PaperService;
import com.edu.entity.Paper;
import com.edu.entity.QuesFill;
import com.edu.entity.Question;
import com.edu.service.PaperQuesService;

@Controller
@RequestMapping("/teacher")
public class PaperController {
	
	@Resource
	PaperService paperService;
	
	@Resource
	PaperQuesService paperQuesService;
	
	
	
	@RequestMapping("/paperList")
	public String view_paper() {
		return "/teacher/paperList";
	}
	
	@RequestMapping("/paperFindByPage.do")
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
		List<Paper> list = paperService.findByPage(offset, Integer.parseInt(pageSize),courseId);
		//获取用户表的总记录条数
		int total = paperService.count(courseId);
		
		
		resultMap.put("rows", list);
		resultMap.put("total", total);
		resultMap.put("msg", "获取用列表成功");
		
		return resultMap;
	}
	
	@RequestMapping("/addPaper.do")
	public String addPaper(HttpServletRequest request){

		return "teacher/addPaper";
	}
	
	@RequestMapping("/savePaper.do")
	@ResponseBody
	public Map<String, Object> savePaper(Paper paper,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap();
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		if(paper != null){
			paper.setCourseId(courseId);

			//设置创建时间
			//保存权限信息
			paperService.insertPaper(paper);
			resultMap.put("success", true);
			resultMap.put("msg", "问题添加成功");
		
		
		}else{
			resultMap.put("success", false);
			resultMap.put("msg", "获取问题信息失败！");
		}
		
		
			return resultMap;
	}
	
	@RequestMapping("/delPaper.do")
	@ResponseBody
	public Map<String, Object> delPaperById(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap();
		String ids = request.getParameter("ids");
		String[] ids2 = ids.split(",");
		String msg = "";
		if(ids2 != null && ids2.length > 0){
			for(String id : ids2){

				paperService.deleteByPaperId(Integer.parseInt(id));
			
			}
			resultMap.put("msg","删除成功!");
			resultMap.put("success", true);

		}else{
			resultMap.put("msg","获取删除id失败!");
			resultMap.put("success", false);
		}
		return resultMap;
	}
	
	@RequestMapping("/updatePaper.do")
	public String updatePaper(HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && !"".equals(id)){
			//通过id查询当前菜单权限信息
			Paper paper = paperService.findByPaperId(Integer.parseInt(id));
			request.setAttribute("paper",paper);
			System.out.println(paper);
		}
		return "teacher/editPaper";
	}	
	
	@RequestMapping("/updatePaperById.do")
	@ResponseBody
	public Map<String,Object> updatePaperById(Paper paper,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap();

		if(paper != null){

			paperService.updatePaper(paper);
			resultMap.put("success", true);
			resultMap.put("msg", "权限修改成功");
		}

		
		return resultMap;
	}
	
	
	


}
