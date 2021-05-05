<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问题添加页面</title>
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
		$("#saveBtn").click(function(){
			//校验输入的信息是否合法
			var result = $("#form").form("validate");
			//通过ajax进行数据的提交
			if(result){
				$.post(
					"saveQues.do",
					$("#form").serialize(),
					function(data){
						alert(data.msg);
						if(data.success){
							//关掉当前弹出窗口，刷新父页面
							parent.closeTopWindow();
						}
						return;
					},
					"json"
				);
			}
		});
	})

</script>
</head>
<body>
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">问题简介：</td>
					<td class="text-content">
						<input type="text" name="quesIntro" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[1,20]'">
					</td>
				</tr>
				
				<tr>
					<td class="text-title">分数：</td>
					<td class="text-content">
						<input type="text" name="score" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[1,20]'">
					</td>
				</tr>
				
				<tr>
					<td class="text-title">难度：</td>
					<td class="text-content">
						<input type="text" name="level" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[1,20]'">
					</td>
				</tr>
				
				<tr>
					<td class="text-title">种类：</td>
					<td class="text-content">
						<select name="type" class="easyui-combobox theme-textbox-radius">
							<option value="1">填空题</option>
							<option value="2">选择题</option>
							<option value="3">简答题</option>
						</select>
					</td>
				</tr>


				<tr>
					<td class="text-title">知识点状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1">正常</option>
							<option value="0">禁用</option>
							<option value="2">已删除</option>
						</select>
					</td>
				</tr>
			
				<tr>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
					</td>
				</tr>
				</table>
		</form>
	</section>
</body>
</html>