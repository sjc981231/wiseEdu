<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知识点菜单权限管理页面</title>
  
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
<script>
	$(function(){
		$("#dataList").treegrid({
			url : "findKnowledgeList.do",
			fitColumns : true,
			idField : "knowledgeId",
			treeField: "knowledgeName",
			rownumbers : true,
			toolbar : "#toolbar",
			checkbox : true,
			singleSelect : false,
			cascadeCheck : false,
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
			}
		});
	});
	//添加权限菜单
	function add(url){
		//获取父id在选中的父id下面添加子菜单
		var rows = $("#dataList").treegrid("getCheckedNodes");
		if(rows.length != 1){
			$.messager.alert("警告","请选择一个知识点！");
			return;
		}
		var parentId = rows[0].knowledgeId; //获取父节点的id的值
		//调用父页面的弹出页面的方法
		parent.openTopWindow({
			width:700,
			height:500,
			title:"添加菜单权限",
			url:url + "?parentId="+ parentId,
			close:function(){
				$("#dataList").treegrid("reload");
			}
		});
	}
	function del(url){
		//获取父id在选中的父id下面添加子菜单
		var rows = $("#dataList").treegrid("getCheckedNodes");
		if(rows.length <= 0){
			$.messager.alert("警告","请至少选择一个节点！");
			return;
		}
		//跟节点原则上时不能删除的，删除父节点的原则是，先删除子节点再删除父节点
		var ids = new Array();
		$.each(rows,function(index,obj){
			ids.push(obj.knowledgeId);
		});
		ids = ids.join(",");
		//删除功能
		$.get(
			url,
			{"ids":ids},
			function(data){
				alert(data.msg);
				if(data.success){
					$("#dataList").treegrid("reload");
				}
			},
			"json"
		);
	}
	
	function edit(url){
		var rows = $("#dataList").treegrid("getCheckedNodes");
		if(rows.length != 1){
			$.messager.alert("警告","请选择一个节点！");
			return;
		}
		var id = rows[0].knowledgeId; //获取父节点的id的值
		//调用父页面的弹出页面的方法
	
		parent.openTopWindow({
			width:700,
			height:500,
			title:"修改菜单权限",
			url:url + "?id="+ id,
			close:function(){
				$("#dataList").treegrid("reload");
			}
		});
	}
</script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return add('toAddKnowledge.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> <a
			href="javascript:void(0);"
			onclick="return del('delKnowledge.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a> <a
			href="javascript:void(0);"
			onclick="return edit('updateKnowledge.do');"
			id="editBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<table id="dataList"></table>
</body>
</html>