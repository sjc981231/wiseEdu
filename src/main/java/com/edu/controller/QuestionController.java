package com.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Knowledge;
import com.edu.entity.PaperQues;
import com.edu.entity.QuesFill;
import com.edu.entity.QuesKnow;
import com.edu.entity.Question;
import com.edu.entity.User;
import com.edu.service.PaperQuesService;
import com.edu.service.QuesFillService;
import com.edu.service.QuesService;

@Controller
@RequestMapping("/teacher")
public class QuestionController {
	
	@Resource
	QuesService quesService;
	
	@Resource
	QuesFillService quesFillService;
	
	@Resource
	PaperQuesService paperQuesService;
	
	@RequestMapping("/quesList")
	public String view_ques() {
		return "/teacher/questionList";
	}
	
	@RequestMapping("/quesFindByPage.do")
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
		List<Question> list = quesService.findByPage(offset, Integer.parseInt(pageSize),courseId);
		//获取用户表的总记录条数
		int total = quesService.count(courseId);
		
		
		resultMap.put("rows", list);
		resultMap.put("total", total);
		resultMap.put("msg", "获取用列表成功");
		
		return resultMap;
	}
	
	@RequestMapping("/addQues.do")
	public String addQues(HttpServletRequest request){

		return "teacher/addQuestion";
	}
	
	@RequestMapping("/saveQues.do")
	@ResponseBody
	public Map<String, Object> saveKnowledge(Question question,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap();
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		if(question != null){
			question.setCourseId(courseId);

			//设置创建时间
			//保存权限信息
			quesService.insertQues(question);
			resultMap.put("success", true);
			resultMap.put("msg", "问题添加成功");
		
		
		}else{
			resultMap.put("success", false);
			resultMap.put("msg", "获取问题信息失败！");
		}
		
		
			return resultMap;
	}
	
	@RequestMapping("/delQues.do")
	@ResponseBody
	public Map<String, Object> delQuesById(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap();
		String ids = request.getParameter("ids");
		String[] ids2 = ids.split(",");
		String msg = "";
		if(ids2 != null && ids2.length > 0){
			for(String id : ids2){

				quesService.deleteByQuesId(Integer.parseInt(id));
			
			}
			resultMap.put("msg","删除成功!");
			resultMap.put("success", true);

		}else{
			resultMap.put("msg","获取删除id失败!");
			resultMap.put("success", false);
		}
		return resultMap;
	}
	
	@RequestMapping("/updateQues.do")
	public String updateQues(HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && !"".equals(id)){
			//通过id查询当前菜单权限信息
			Question question = quesService.findByQuesId(Integer.parseInt(id));
			request.setAttribute("question",question);
			System.out.println(question);
		}
		return "teacher/editQuestion";
	}	
	
	@RequestMapping("/updateQuestionById.do")
	@ResponseBody
	public Map<String,Object> updateQuestionById(Question question,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap();

		if(question != null){

			quesService.updateQuestion(question);
			resultMap.put("success", true);
			resultMap.put("msg", "权限修改成功");
		}

		
		return resultMap;
	}
	
	
	
	@RequestMapping("quesDetail.do")
	public String quesDetail(HttpServletRequest request){
		String id = request.getParameter("id");
		String type=request.getParameter("type");
		System.out.println(type);
		request.setAttribute("quesId", id);
		switch(type) {
			case "1":
				QuesFill quesFill=quesFillService.findByQuesId(Integer.parseInt(id));
				request.setAttribute("quesFill", quesFill);
				return "teacher/editQuesFill";
//			case "2":
				
		    default:
		    	return "teacher/error";
		
		}

	}	
	
	@RequestMapping("updateQuesFillById.do")
	@ResponseBody
	public Map<String,Object> updateQuesFillById(QuesFill quesFill,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap();

		if(quesFill != null){

			quesFillService.updateQuesFill(quesFill);
			resultMap.put("success", true);
			resultMap.put("msg", "权限修改成功");
		}

		
		return resultMap;
	}
	
	
	
	
	@RequestMapping("/assignQues.do")
	public String toPaperQues(HttpServletRequest request){
		String paperId = request.getParameter("id");
		request.setAttribute("paperId",paperId);
		return "teacher/assignQues";
	}
	
	
	@RequestMapping("/findQuestionList.do")
	@ResponseBody
	public Map<String, Object> findQuestionList(HttpServletRequest request,HttpServletResponse response) {
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		Map<String, Object> resultMap = new HashMap();


		//从数据库中获取用户数据
		List<Question> list = quesService.findByCourseId(courseId);
		//获取用户表的总记录条数
		
		resultMap.put("data", list);
		resultMap.put("msg", "获取用列表成功");
		
		return resultMap;
	}
	
	
	@RequestMapping("/findQuesByPaperId.do")
	@ResponseBody
	public  Map<String,Object> findFunctionByRoleId(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> resultMap = new HashMap();
		String paperId = request.getParameter("id");
		if(paperId != null && !"".equals(paperId)){
			//根据角色id或该角色的现有的所有权限
			List<Question> list = paperQuesService.findQuesByPaperId(Integer.parseInt(paperId));
			if(list != null && list.size() > 0){
				resultMap.put("data", list);
				resultMap.put("success", true);
			}else {
				resultMap.put("success", false);
			}
		}
		return resultMap;
	}
	
	@RequestMapping("/save_paperQues.do")
	@ResponseBody
	public Map<String,Object> save_paperQues(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> resultMap = new HashMap();

		String paperId = request.getParameter("paperId");
		String id = request.getParameter("ids");
		String[] ids = id.split(",");
		if(paperId != null && !"".equals(paperId)){
			if(ids != null && ids.length > 0){
				//删除数据库表中的数据
				paperQuesService.deleteByPaperId(Integer.parseInt(paperId));
				//进行数据库插入操作
				for(String quesId : ids){
					PaperQues rf=new PaperQues();
					rf.setPaperId(Integer.parseInt(paperId));
					rf.setQuesId(Integer.parseInt(quesId));
					paperQuesService.save(rf);
				}
				resultMap.put("msg", "授权成功！");
				resultMap.put("success",true);

			}else{
				resultMap.put("msg", "没有获取知识点id！");
				resultMap.put("success",false);
			}
		}else{
			resultMap.put("msg", "没有获取题目id！");
			resultMap.put("success",false);
		}
		
		return resultMap;
	}
	

}
