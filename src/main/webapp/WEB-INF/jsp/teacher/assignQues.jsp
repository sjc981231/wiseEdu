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
		$("#quesList").datagrid({
			url : "findQuestionList.do",
			fitColumns : true,
			idField : "quesId",
			rownumbers : true,
			columns : [[
				
				{field:"quesId",title:"选择",checkbox:true},
				{field:"quesIntro",title:"题目简介",sortable:true,width:20},
				{field:"level",title:"难度",width:15},
				{field:"score",title:"分数",width:20},
				{field:"type",title:"题目类型",formatter:function(value,rowData,index){
					if(value == 1){
						return "填空题";
					}else if(value == 2){
						return "选择题";
					}else if(value == 3){
						return "简答题";
					}else{
						return "";
					}
				}},
				{field:"status",title:"题目状态",formatter:function(value,rowData,index){
					if(value == 1){
						return "可用";
					}else if(value == 0){
						return "禁用";
					}else if(value == 2){
						return "已删除";
					}else{
						return "";
					}
				}},
				{field:"opt",title:"操作",width:30,formatter:function(value,rowData,index){
					var btn='<a href="javascript:void(0);" onclick="return detail(\'quesDetail.do\','+index+')"'
					+' class="easyui-linkbutton" data-options="iconCls:\'icon-add\',plain:true">详情</a>';
							console.log(btn);
					return btn;
				}}
			]],
			loadFilter:function(data){//"_parentId"
				return {"rows":data.data};
			},
			//当前表格中所有的数据加载完毕之后
			onLoadSuccess : function(){
				//根据角色的id获取到当前角色的所有权限信息
				$.get("findQuesByPaperId.do",
					{"id": $("#paper_id").val()},
					function(data){
						if(!data.success){
							return;
						}
						//判断用户是否已经有了角色，如果有，就将对应角色在表格中选中
						if(data && data.data.length>0){
							$.each(data.data,function(index,obj){
								$("#quesList").datagrid("checkRow",obj.quesId-1);
							});
						}
					},"json");
			}			
		});
		
		$("#assignBtn").click(function(){
			var rows = $("#quesList").datagrid("getChecked");
			if(rows.length <= 0){
				$.messager.alert("警告","必须选择一条记录！");
				return;
			}

			var ids = new Array();
			$.each(rows,function(index,obj){
				ids.push(obj.quesId);
			});
			ids = ids.join(",");
			//提交修改的菜单选项信息
			$.get(
				"save_paperQues.do",
				{"paperId":$("#paper_id").val(),"ids":ids},
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
	
		function detail(url,index){
		$('#quesList').datagrid('selectRow',index);
		var row= $("#quesList").datagrid("getSelected");
		var id = row.quesId;
		var type=row.type;
		var result="?id="+id+"&?type="+type;
		console.log(result);
		//调用父页面的弹出页面的方法
		parent.openAnotherWindow({
			width:700,
			height:500,
			title:"题目详情",
			url:url+"?id="+id+"&type="+type,
			isScrolling:"yes"
		});

	}
</script>
</head>
<body>
	<div style="text-align: center;margin-top:10px;">
		<input type="hidden" name="paperId" value="${paperId}" id="paper_id"/>
    	<a  class="easyui-linkbutton" id="assignBtn" data-options="iconCls:'icon-man'">分配</a>
    </div>
	<table id="quesList"></table>
	
</body>
</html>