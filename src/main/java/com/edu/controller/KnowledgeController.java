package com.edu.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.edu.entity.Knowledge;
import com.edu.entity.QuesKnow;
import com.edu.service.KnowledgeService;
import com.edu.service.QuesKnowService;






@Controller
@RequestMapping("/teacher")
public class KnowledgeController {
	@Resource
	KnowledgeService knowledgeService;
	
	@Resource
	QuesKnowService quesKnowService;
	
	@RequestMapping("/knowledgeList")
	public String view_knowledge() {
		return "teacher/knowledgeList";
	}
	
	@RequestMapping("/findKnowledgeList.do")
	@ResponseBody
	public Map<String, Object>  findFunctionList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap();
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		//查询所有的权限列表
		List<Knowledge> list = knowledgeService.findKnowledgeList(courseId);
		if(list != null && list.size() > 0){
			resultMap.put("data",list);
		}
		
		resultMap.put("msg", "获取用列表成功");	
		return resultMap;
	}
	
	@RequestMapping("/toAddKnowledge.do")
	public String addPerm(HttpServletRequest request){
		String parentId = request.getParameter("parentId");
		request.setAttribute("parentId", parentId);
		return "teacher/addKnowledge";
	}
	@RequestMapping("/saveKnowledge.do")
	@ResponseBody
	public Map<String, Object> saveKnowledge(Knowledge knowledge,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap();
//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		resultMap.put("msg","该知识点已经存在，请重新输入！");
		System.out.println(knowledge);
		if(knowledge != null){
			knowledge.setCourseId(courseId);
			String knowledgeName = knowledge.getKnowledgeName();
			System.out.println(knowledgeName);
			//根据菜单名称查询，判断该名称是否存在
			if(knowledgeName != null && !"".equals(knowledgeName)){
				Knowledge know = knowledgeService.findByKnowledgeName(knowledgeName);
				if(know != null){
					resultMap.put("success",false);
					resultMap.put("msg","该知识点已经存在，请重新输入！");
				}else{
					//设置创建时间
					//保存权限信息
					knowledgeService.insertKnowledge(knowledge);
					resultMap.put("success", true);
					resultMap.put("msg", "权限添加成功");
				}				
			}
		}else{
			resultMap.put("success", false);
			resultMap.put("msg", "获取知识点信息失败！");
		}
		
		
			return resultMap;

	}
	@RequestMapping("/delKnowledge.do")
	@ResponseBody
	public Map<String, Object> delQuesById(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap();
		String ids = request.getParameter("ids");
		String[] ids2 = ids.split(",");
		String msg = "";
		if(ids2 != null && ids2.length > 0){
			for(String id : ids2){
				//根据id查询看角色权限表中是否存在关联

				//判断当前id是否存在子菜单，如果存在不能删除
				List<Knowledge> knows = knowledgeService.findByParentId(Integer.parseInt(id));
				if(knows != null && knows.size() > 0){
					msg = "存在子知识点菜单，请先删除子知识点再删除该知识点！";
					resultMap.put("msg",msg);
					resultMap.put("success", false);
					return resultMap;
				}else{
					knowledgeService.deleteByKnowledgeId(Integer.parseInt(id));
				}
			
			}
			resultMap.put("msg","删除成功!");
			resultMap.put("success", true);

		}else{
			resultMap.put("msg","获取删除id失败!");
			resultMap.put("success", false);
		}
		
		
		return resultMap;
	}
	@RequestMapping("/updateKnowledge.do")
	public String updateKnowledge(HttpServletRequest request){
		String id = request.getParameter("id");
		if(id != null && !"".equals(id)){
			//通过id查询当前菜单权限信息
			Knowledge knowledge = knowledgeService.findByKnowledgeId(Integer.parseInt(id));
			request.setAttribute("knowledge",knowledge);
			System.out.println(knowledge);
		}
		return "teacher/editKnowledge";
	}	
	@RequestMapping("/findTree.do")
	@ResponseBody
	public Map<String, Object> findTree(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = new HashMap();

//		Course course=(Course) request.getSession().getAttribute("course");
//		Integer courseId=course.getCourseId();
		Integer courseId=1; //test
		List<Knowledge> list = knowledgeService.findKnowledgeList(courseId);
		System.out.println("list为:"+list);
		List<Map<String,Object>> treeNodes = new ArrayList<Map<String,Object>>();


		if(list != null && list.size() > 0){
			for(Knowledge know : list){
				if(know.getParentId()==null) {
					Map<String,Object> treeNode = new HashMap<String,Object>();
					treeNode.put("id", know.getKnowledgeId());
					treeNode.put("text", know.getKnowledgeName());
					treeNode.put("children", findChildNodes(know.getKnowledgeId()));

					treeNodes.add(treeNode);
				}

				
			}
		}
		if(treeNodes != null && treeNodes.size() > 0){
			resultMap.put("data",treeNodes);
			resultMap.put("success",true);
			resultMap.put("msg", "获取权限列表成功！");
			
		}else{
			resultMap.put("success",false);
			resultMap.put("msg", "获取权限列表失败！");
		}
		return resultMap;
	}
	@RequestMapping("/updateKnowledgeById.do")
	@ResponseBody
	public Map<String,Object> updateKnowledge(Knowledge knowledge,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap();

		if(knowledge != null){

			knowledgeService.updateKnowledge(knowledge);
			resultMap.put("success", true);
			resultMap.put("msg", "权限修改成功");
		}

		
		return resultMap;
	}
	
	public List<Map<String,Object>> findChildNodes(int parentId){
		List<Map<String,Object>> childNodes = new ArrayList<Map<String,Object>>();
		List<Knowledge> list = knowledgeService.findByParentId(parentId);
		System.out.println("子list为:"+list);
		if(list != null && list.size() > 0){
			for(Knowledge know : list){

				Map<String,Object> treeNode = new HashMap<String,Object>();
				treeNode.put("id", know.getKnowledgeId());
				treeNode.put("text", know.getKnowledgeName());
				treeNode.put("children", findChildNodes(know.getKnowledgeId()));

				childNodes.add(treeNode);


				
			}
		}
		
		return childNodes;
	}
	
	
	@RequestMapping("/assignKnow.do")
	public String toQuesKnow(HttpServletRequest request){
		String quesId = request.getParameter("id");
		request.setAttribute("quesId",quesId);
		return "teacher/assignKnow";
	}
	
	@RequestMapping("/findKnowledgeByQuesId.do")
	@ResponseBody
	public  Map<String,Object> findFunctionByRoleId(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> resultMap = new HashMap();
		String quesId = request.getParameter("id");
		if(quesId != null && !"".equals(quesId)){
			//根据角色id或该角色的现有的所有权限
			List<Knowledge> list = quesKnowService.findKnowledgeByQuesId(Integer.parseInt(quesId));
			if(list != null && list.size() > 0){
				resultMap.put("data", list);
			}
		}
		return resultMap;
	}
	
	@RequestMapping("/save_quesKnowledge.do")
	@ResponseBody
	public Map<String,Object> save_roleFunction(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> resultMap = new HashMap();

		String quesId = request.getParameter("quesId");
		String id = request.getParameter("ids");
		String[] ids = id.split(",");
		if(quesId != null && !"".equals(quesId)){
			if(ids != null && ids.length > 0){
				//删除数据库表中的数据
				quesKnowService.deleteByQuesId(Integer.parseInt(quesId));
				//进行数据库插入操作
				for(String knowId : ids){
					QuesKnow rf=new QuesKnow();
					rf.setKnowledgeId(Integer.parseInt(knowId));
					rf.setQuesId(Integer.parseInt(quesId));
					quesKnowService.save(rf);
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
