<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台</title>
  
  
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
  

</head>
<body>
<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
    <div data-options="region:'east',title:'East',split:true" style="width:300px;">
    	<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',title:'East',collapsed:false,collapsible:false" style="width:180px"></div>
			<div data-options="region:'center'"></div>
		</div>
    </div>
    	
    <div data-options="region:'west',title:'West',split:true" style="width:100px;"></div>
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>
</div>



</body>

<script>
    var Data = [{
        text: '课程管理系统',
        iconCls: 'icon-more',
        state: 'open',
        children: [
        	{
            	text: '首页',
            	iconCls: 'icon-more',
            	url:'courseMenu'
            },
        	{
            	text: '课程管理',
            	iconCls: 'icon-more'
            	
            },{
            	text: '知识点管理',
            	iconCls: 'icon-more'
            },{
            	text: '题库管理',
            	iconCls: 'icon-more'
            },{
            	text: '考卷管理',
            	iconCls: 'icon-more'
            },{
            	text: '学生管理',
            	iconCls: 'icon-more'
            },{
            	text: '答卷管理',
            	iconCls: 'icon-more',
            	url:'login'
            }, {
            text: 'Option1',
            click:'out'
        },{
            text: 'Option2'
        },{
            text: 'Option3',
            children: [{
                text: 'Option31'
            },{
                text: 'Option32'
            }]
        }]
    }];
 
 
    function test(item){
        alert(item.url);
        location.href=item.url;
        console.log(item.text);
    }
    
    $(function () {

    });
    
    


  </script>
</html>