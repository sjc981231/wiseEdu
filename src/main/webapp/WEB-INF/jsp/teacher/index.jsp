<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <!-- import CSS -->

  <link rel="stylesheet" type="text/css" href="${basePath}/themes/material/easyui.css">
  <link rel="stylesheet" type="text/css" href="${basePath}/themes/icon.css">
  
  <link rel="stylesheet" href="${basePath}/layui/css/layui.css"  media="all">
  
  <script type="text/javascript" src="${basePath}/default/jquery.min.js"></script>
  <script type="text/javascript" src="${basePath}/default/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="${basePath}/js/util.js"></script>
  <script src="${basePath}/locale/easyui-lang-zh_CN.js"></script> 

  <script src="${basePath}/layui/layui.all.js" charset="utf-8"></script>

<script>
	$(function(){
		$("#courseList").datagrid({
			url : "courseFindByPage.do",
			pagination : true,
			toolbar : "#toolbar",
			fitColumns : true,
			idField : "courseId",
			rownumbers : true,
			//singleSelect:true,
			columns : [[
				{field:"courseId",title:"选择",checkbox:true},
				{field:"courseName",title:"课程名",sortable:true,width:20},
				
			]],
			loadFilter:function(data){
				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
				return {"total":data.total,"rows":data.rows};
			}
		});
	})
	
	
	function edit(url){
		//选中修改的记录
		var rows = $("#courseList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录修改！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录修改！");
			return;
		}else{
			var courseId = rows[0].courseId;
			//调用父页面的弹出页面的方法
		    $.ajax({
		        url: 'enterCourseMenu.do',
		        data: {"courseId":courseId},
		        type: 'post',
		        dataType: 'json',
		        success: function (data) {
			           location.href="courseMenu"

		        }
		    });
		}
	}

</script>


</head>
<body>

<h1>你好，${sessionScope.user.nickName}老师</h1>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return add('addCourse.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> <a
			href="javascript:void(0);"
			onclick="return del('delCourse.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a> <a
			href="javascript:void(0);"
			onclick="return edit('updateCourse.do');"
			id="editBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a> 
	</div>
	<table id="courseList"></table>


</body>
</html>