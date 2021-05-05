<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%> 
<!DOCTYPE html>
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
  	body {
    background-color: #fefefe;
    font-family: Verdana,Tahoma,Arial;
    margin: 0;
}

.container {
    min-height: 600px;
}


.header {
    height: 56px;
    overflow: hidden;
    border-bottom: 1px solid #d6dfea;
    background: #eff4fa;
}

.content {
    width: 960px;
    margin: 32px auto;
    padding: 0 24px;
}



.content_wrapper {
    min-height: 372px;
    padding-top: 40px;
    margin: 0 20px 60px 25px;
}


.login_container {
    float: right;
    border: 1px solid #b0a1c4;
    width: 344px;
    height: 400px;
    background-color: #fff;
    position: relative;
    z-index: 11;
    border-radius: 6px;
    overflow: hidden;
}

.login_pictures {
    position: relative;
    margin-right: 400px;
}


.login_pictures_picture {
    background-image:url('${basePath}/images/aaaa.jpg');
    background-position:-50px 70px;

    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    height: 570px;
    background-repeat: no-repeat;

    z-index: -1;
}


.login_pictures_txt {
    line-height: 45px;
    margin-top: 15px;
    margin-bottom: 15px;
    font-size: 30px;
    font-weight: normal;
    color: #6f95c8;
}


.footer {
    position: fixed;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 2;
    clear: both;
    line-height: 36px;
    text-align: center;
    color: #6f95c9;
    background-color: #eff4fa;
    border-top: 1px solid #d6dfea;
}

.login_title {
    text-align: center;
    margin: 50px;

    line-height: 26px;
    font-size: 30px;
    font-weight: normal;
    color: #6f95c8;
}

.login_div {
    text-align: center;
    margin:0 auto;
}

.login_btn1 {
    background-color: #54A455;
    border: 2px solid #4BAF50;
    border-radius: 2px;
    color: #fff;
    border: none;
    padding: 5px 30px;
    width: 230px;
    height: 40px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 18px;
    margin: 4px 10px 4px 5px;
    cursor: pointer;
}

.login_btn1:hover {
    background-color: #4CAE51;
}

.login_margin {
    margin-top: 10px;
    margin-bottom: 10px;
}

  	
  </style>
  
  
</head>
<body>
<div class="container">
    <div class="header">
        <div class="header_title" style="margin-left: 20px;color: #6f95c9;font-size: 25px;font-weight: bold;line-height: 56px; ">智慧教育平台</div>
    </div>
    <div class="content">
        <div class="content_wrapper">
            <div id="login" class="login_container" style="visibility: visible;">
                <div class="login_title">
                    LOGIN
                </div>
                <div class="login_div">
                    <form method="post" id="loginForm">
                        <div class="login_margin">
                                <p>账号: <input name="userName" id="userName" class='easyui-textbox' type="text" data-options="required:true"></p>
                        </div>
                        <div class="login_margin">
								<p>密码: <input name="password" id="password" class='easyui-textbox' type="password" data-options="required:true"></p>
                        </div>
                        
                        
                        <div class="login_margin">
                            <input type="button" class="login_btn1" value="登录" type="submit" id="loginbtn" onclick="login()"/>
                            <p id="alert" style="color: red;">&nbsp; </p>
                        </div>
                    </form>
                </div>
            </div>
            <div class="login_pictures">
                <div class="login_pictures_picture">
                <div class="login_pictures_txt">
                	欢迎使用智慧教育平台
                </div>
            </div>

        </div>
    </div>

    <div class="footer">
        Website by @SJC
    </div>
</div>
</div>

  

  


  
    

<script>

$(function(){

	//敲入键盘的enter建进行提交登陆
	$(document).keydown(function(e){
		if(e.keyCode == 13){
			//触发登陆按钮的事件
			$("#loginbtn").trigger("click");
		}
	});
	
});

function login() {

	var userName = $("#userName").val();
	var password = $("#password").val();
	if(isEmpty(userName)){
		$("#userName").focus();
		$("#alert").html("请输入账号");
		return;
	}
	
	if(isEmpty(password)){
		$("#password").focus();
		$("#alert").html("请输入密码");
		return;
	}
    $.ajax({
        url: '${basePath}/login.do',
        data: {"userName":userName,"password":password},
        type: 'post',
        dataType: 'json',
        success: function (data) {
        	if(data.success){
	            //登录成功后根据用户角色跳转到不同的页面
	            if(data.roleId == 1){
	                location.href="teacher/index"
	            }else{
	                location.href="student/index"
	            }
        	}
        	else{
        		$("#alert").html('用户名或密码错误');
        	}
        }
    });


}

</script>

</body>
  
  
  
