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
		$("#studentNotInList").datagrid({
			url : "findStudentList.do",
			fitColumns : true,
			idField : "id",
			rownumbers : true,
			columns : [[
				{field:"id",title:"选择",checkbox:true},
				{field:"nickName",title:"名字",sortable:true,width:20},
			]],
			loadFilter:function(data){
				return {"rows":data.data};
			}
		});
		
		$("#assignBtn").click(function(){
			var rows = $("#studentNotInList").datagrid("getChecked");
			if(rows.length <= 0){
				$.messager.alert("警告","必须选择一条记录！");
				return;
			}

			var ids = new Array();
			$.each(rows,function(index,obj){
				ids.push(obj.id);
			});
			ids = ids.join(",");
			//提交修改的菜单选项信息
			$.get(
				"save_newInStudent.do",
				{"ids":ids},
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
    	<a  class="easyui-linkbutton" id="assignBtn" data-options="iconCls:'icon-man'">添加</a>
    </div>
	<table id="studentNotInList"></table>
	
</body>
</html>