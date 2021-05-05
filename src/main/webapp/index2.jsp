<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>首页</title>
<%@include file="WEB-INF/jsp/common/taglib.jsp"%> 
<meta charset="utf-8">
<title>权限管理系统</title>
    <link href="${basePath}/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/easyui/themes/insdep/icon.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/easyui/themes/color.css" rel="stylesheet" type="text/css">
    
    <link href="${basePath}/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" >

    <script type="text/javascript" src="${basePath}/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${basePath}/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
    <script type="text/javascript" src="${basePath}/ztree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="${basePath}/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${basePath}/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="${basePath}/js/util.js"></script>
  

</head>

<body>
<!-- <a href="debug.jsp">debug</a>-->
	<div id="master-layout">
		<!--顶部banner区开始-->
		<div
			data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
			<div class="theme-navigate">
				<div class="left">
					<h3>权限管理dasdasd系统</h3>
				</div>
				<div class="right">
					<a href="#" class="easyui-menubutton"
						data-options="menu:'#mm1',hasDownArrow:false">消息<span
						class="badge color-orange">15</span></a>

					<div id="mm1" class="theme-navigate-menu-panel"
						style="width:180px;">
						<div>
							站内消息<span class="badge color-success">5</span>
						</div>
						<div>
							公司公告<span class="badge color-important">10</span>
						</div>
						<div>服务消息</div>
						<div class="menu-sep"></div>
						<div>查看历史消息</div>
						<div class="menu-sep"></div>
						<div>清除消息提示</div>
					</div>
					<a href="#" class="easyui-menubutton theme-navigate-user-button"
						data-options="menu:'.theme-navigate-user-panel'">${sessionScope.user.userName}</a>

					<div class="theme-navigate-user-panel">
						<dl>
							<dd>
								<img src="static/easyui/themes/insdep/images/portrait86x86.png" width="86" height="86"> 
								<b class="badge-prompt">${sessionScope.user.userName }</b> 
								<span>${sessionScope.user.email}</span>

								<p>
									安全等级：<i class="text-success">高</i>
								</p>
							</dd>
							<dt>
								<a class="theme-navigate-user-modify">修改资料</a> 
								<a id="logout" class="theme-navigate-user-logout">注销</a>
							</dt>
						</dl>
					</div>
				</div>
			</div>

		</div>
		<!--顶部banner区结束-->
		<!--左侧系统菜单区开始-->
		<div data-options="region:'west',split:true,border:false" title="系统菜单" style="width:230px; padding:10px 20px;">
			<ul id="menu" class="ztree"></ul>
		</div>
		<!--左侧系统菜单区结束-->
		<!--右侧功能展示区开始-->
		<div id="tabs" data-options="region:'center',border:false,height:800" class="easyui-tabs">
			<div id="tab1" title="个人中心">
				<div class="theme-user-info-panel">
					<div class="left">
						<img src="static/easyui/themes/insdep/images/portrait86x86.png" width="86" height="86">
					</div>
					<div class="right">
						<ul>
							<li class="text-success">￥6,200.00<span>完成合同金额</span></li>
							<li class="text-info">33<span>月度客户数</span></li>
							<li class="text-warning">9820<span>月度任务额</span></li>
							<li>125<span>月度线索数</span></li>
						</ul>
					</div>
					<div class="center">
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
	</div>
</body>
	<script type="text/javascript">

		
		
		
	    $(function () {
	        /*布局部分*/
	        $('#master-layout').layout({
	            fit: true/*布局框架全屏*/
	        });
	
	    });
		//打开id="topWindow"的div窗口
		

	</script>
</html>
