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
	<div id="master-layout" class="easyui-layout" data-options="fit:true">
		
		<!--左侧系统菜单区开始-->
		<div data-options="region:'west',split:true,border:false,collapsible:false" title="系统菜单" style="width:230px;">
			<div id="sm" class="easyui-sidemenu" data-options="data:Data, onSelect:test,border:false" style="width:100%;"></div>
		</div>
	 
		<!--左侧系统菜单区结束-->
		<!--右侧功能展示区开始-->
		<div id="tabs" data-options="region:'center',border:false,height:800" class="easyui-tabs">
			<div id="tab1" title="个人中心">
				<div id="theme-user-info-panel" class="easyui-layout" style="width:100%;height:300px;" >
				
					<div data-options="region:'west',title:'East'"  style="width:30%;">
						<img src="${basePath}/easyui/themes/insdep/images/portrait86x86.png" width="86" height="86">
					</div>
					<div data-options="region:'east'" style="width:30%;">
						<ul>
							<li class="text-success">￥6,200.00<span>完成合同金额</span></li>
							<li class="text-info">33<span>月度客户数</span></li>
							<li class="text-warning">9820<span>月度任务额</span></li>
							<li>125<span>月度线索数</span></li>
						</ul>
					</div>
					<div data-options="region:'center'">
						<h1>
							${sessionScope.user.userName }<span class="badge color-success">已认证</span>
						</h1>

						<h2>
							角色名
						</h2>
						<ul>
							<li><i class="iconfont">&#xe61c;</i> ${sessionScope.user.email}</li>
							<li><i class="iconfont">&#xe65d;</i> ${sessionScope.user.phone}</li>
						</ul>
					</div>
				</div>

				<div id="user-info-more"
					class="easyui-tabs theme-tab-blue-line theme-tab-body-unborder"
					data-options="tools:'#tab-tools',fit:true">
					<div title="待办事项" style="padding:10px"></div>
					<div title="任务列表" style="padding:10px"></div>
					<div title="站内信息" style="padding:10px"></div>
				</div>
			</div>
		</div>
		<!--右侧功能展示区结束-->
		<div id="topWindow" style="overflow: hidden;"></div>
		<div id="anotherWindow" style="overflow: hidden;"></div>
	</div>


</body>

<script>
    var Data = [{
        text: '课程管理系统',
        iconCls: 'icon-more',
        state: 'open',
        children: [
        	{
            	text: '个人中心',
            	iconCls: 'icon-more',
            },{
            	text: '课程管理',
            	iconCls: 'icon-more',
            	url:'lessonList'
            	
            },{
            	text: '知识点管理',
            	iconCls: 'icon-more',
            	url:'knowledgeList'
            },{
            	text: '题库管理',
            	iconCls: 'icon-more',
            	url:'quesList'
            },{
            	text: '考卷管理',
            	iconCls: 'icon-more',
            	url:'paperList'
            },{
            	text: '学生管理',
            	iconCls: 'icon-more',
            	url:'studentList'
            },{
            	text: '答卷管理',
            	iconCls: 'icon-more',
            	url:'answerList'
            }]
    }];
 
 
    function test(item){
        console.log(item.text);

		event.preventDefault();
		if (item.url) {
			openTab("#tabs", item.text, item.url);
		}
    }
    
    $(function () {



    });
    
	function openTopWindow(options) {
		//为参数设置默认值
		options.width = options.width ? options.width : 600;
		options.height = options.height ? options.height : 400;
		options.title = options.title ? options.title : "  ";
		options.url = options.url ? options.url : "";
		options.close = options.close ? options.close : function() {};
		options.isScrolling=options.isScrolling?options.isScrolling:"no";
		//初始化窗体
		$("#topWindow")
				.window(
						{
							width : options.width,
							height : options.height,
							title : options.title,
							modal : true,
							content : "<iframe width='100%' height='100%' scrolling='"+options.isScrolling+"' frameborder='0' src='"
									+ options.url + "'></iframe>",
							onClose : options.close
						});
	}
	
	function openAnotherWindow(options) {
		//为参数设置默认值
		options.width = options.width ? options.width : 600;
		options.height = options.height ? options.height : 400;
		options.title = options.title ? options.title : "  ";
		options.url = options.url ? options.url : "";
		options.close = options.close ? options.close : function() {};
		options.isScrolling=options.isScrolling?options.isScrolling:"no";
		//初始化窗体
		$("#anotherWindow")
				.window(
						{
							width : options.width,
							height : options.height,
							title : options.title,
							modal : true,
							content : "<iframe width='100%' height='100%' scrolling='"+options.isScrolling+"' frameborder='0' src='"
									+ options.url + "'></iframe>",
							onClose : options.close
						});
	}
	//关闭窗口
	function closeTopWindow() {
		$("#topWindow").window("close");
	}
	//打开标签页的函数
	function openTab(tabsId, title, url) {
		//判断是不是已经存在这个标签页 如果存在选中该标签页
		var exists = $(tabsId).tabs("exists", title);
		if (exists) {
			$(tabsId).tabs("select", title);
		} else {
			//如果不存在打开新的标签页
			$(tabsId)
					.tabs(
							"add",
							{
								"title" : title,
								"closable" : true,
								"content" : 
									"<iframe width='100%' height='100%' scrolling='yes' frameborder='0' src='"+ url + "'></iframe>"
							});
		}
	}
    
    


  </script>
</html>