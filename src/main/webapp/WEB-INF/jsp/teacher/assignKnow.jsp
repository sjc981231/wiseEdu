<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色授权页面</title>
  <link rel="stylesheet" type="text/css" href="${basePath}/themes/material/easyui.css">
  <link rel="stylesheet" type="text/css" href="${basePath}/themes/icon.css">

  <script type="text/javascript" src="${basePath}/default/jquery.min.js"></script>
  <script type="text/javascript" src="${basePath}/default/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="${basePath}/js/util.js"></script>
  <script src="${basePath}/locale/easyui-lang-zh_CN.js"></script> 
  
    
    <link href="${basePath}/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" >


    <script type="text/javascript" src="${basePath}/ztree/jquery.ztree.all.min.js"></script>

    <script type="text/javascript" src="${basePath}/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="${basePath}/js/util.js"></script>
<script type="text/javascript">
	$(function(){
		//加载所有权限
		$("#knowList").treegrid({
			url : "findKnowledgeList.do",
			fitColumns : true,
			idField : "knowledgeId",
			treeField: "knowledgeName",
			rownumbers : true,
			checkbox:true,
			cascadeCheck:false,
			singleSelect : false,
			columns : [[
				
				{field:"knowledgeName",title:"知识点名称",sortable:true,width:10},
				{field:"status",title:"知识点状态",formatter:function(value,rowData,index){
					if(value == 1){
						return "可用";
					}else if(value == 0){
						return "禁用";
					}else if(value == 2){
						return "已删除";
					}else{
						return "";
					}
				}}
			]],
			loadFilter:function(data){//"_parentId"
				if(data && data.data){
					$.each(data.data ,function(index,obj){
						if(obj.parentId){
							data.data[index]._parentId = obj.parentId; 
						}
					});
					return {"rows":data.data};
				}
				return null;
			},
			//当前表格中所有的数据加载完毕之后
			onLoadSuccess : function(){
				//根据角色的id获取到当前角色的所有权限信息
				$.get("findKnowledgeByQuesId.do",
					{"id":  $("#ques_id").val()},
					function(data){
						//判断用户是否已经有了角色，如果有，就将对应角色在表格中选中
						if(data && data.data.length>0){
							$.each(data.data,function(index,obj){
								$("#knowList").treegrid("checkNode",obj.knowledgeId);
							});
						}
					},"json");
			}
			
		});
		
		$("#assignBtn").click(function(){
			var rows = $("#knowList").treegrid("getCheckedNodes");
			if(rows.length <= 0){
				$.messager.alert("警告","必须选择一条记录！");
				return;
			}
			if(rows.length == 1 && rows[0].knowledgeId == 1){
				$.messager.alert("警告","请至少选择一个子菜单");
				return;
			}
			var ids = new Array();
			$.each(rows,function(index,obj){
				ids.push(obj.knowledgeId);
			});
			ids = ids.join(",");
			//提交修改的菜单选项信息
			$.get(
				"save_quesKnowledge.do",
				{"quesId":$("#ques_id").val(),"ids":ids},
				function(data){
					alert(data.msg);
					if(data.success){
						parent.closeTopWindow();
					}
				},
				"json"
			);
		});
	})
</script>
</head>
<body>
	<div style="text-align: center;margin-top:10px;">
		<input type="hidden" name="quesId" value="${quesId}" id="ques_id"/>
    	<a  class="easyui-linkbutton" id="assignBtn" data-options="iconCls:'icon-man'">分配</a>
    </div>
	<table id="knowList"></table>
	
</body>
</html>