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
  <style>
		.category {
	width: 100%;
	margin-top: 10px;
	margin-bottom:20px;
}
	
.category .title {
	margin-bottom: 10px;
    border-bottom: 1px solid #cac5c5;
    height: 30px;
	text-indent:1em;
	font-size:18px;
	color:#666;
}

.category .items {
	margin-left:10px;
}

.category .items .item {
   	width: 95%;
    height: 200px;
    background: #ccc;
    margin: 20px;
    margin-right:20px;
    cursor:pointer;
}

.item-header {
	font-size: 16px;
    line-height: 52px;
    padding-top: 30px;
}

.item-name {
	font-size: 16px;
}

  </style>
<script>
	$(function(){

	})
	
	function enterCourse(courseId) {
		location.href="courseLearning?courseId="+courseId;
}
	
	

</script>


</head>
<body>

<h1>你好，${sessionScope.user.nickName}学生</h1>

	<table id="courseList"></table>

	<div class='category'>
		<div class='title'>课程学习</div>
		<ul class='items'>
			<c:forEach items="${studentCourses}" var="item">
				<li class='item' onclick="enterCourse('${item.courseId}');">
					<div class='item-pic' style="float: left;margin-right:30px;">
						<img src="${basePath}/pic/pic-default.jpg" width="300" height="200">
					</div>
					
					<div class='item-banner' style="margin-right:20px;">
						<div class='item-header'>课程标题：${item.courseName}</div>
						<div class='item-name' >授课老师：${item.teacherName}</div>
					</div>
	
				</li>
			</c:forEach>
		</ul>
	</div>


</body>
</html>