package com.edu.controller;



import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.User;
import com.edu.service.UserService;

import cn.hutool.crypto.SecureUtil;

@Controller
public class LoginController {

	@Resource
	private UserService userService;
	
	public static final String salt = "sjc981231";

	@RequestMapping("/login.do")
	@ResponseBody
	public Map<String, Object> login(String userName,String password,HttpServletRequest request) {
	    //设置返回信息
	    Map<String, Object> resultMap = new HashMap();
	    resultMap.put("errCode",0); //错误码
	    resultMap.put("errMsg",null);//错误信息
	    

	    
//	    //密码加密
//	    String password = SecureUtil.md5(params.get("password") + salt);
//	    System.out.println(password);
//	    params.put("password",password);

	    
	    System.out.println("用户名"+userName);
		User user = userService.login(userName, password);
		if(user != null){
			//把用户信息放到session中
			HttpSession session = request.getSession();
			
			session.setAttribute("user",user);
	        resultMap.put("roleId",user.getRoleId()); //返回用户的角色ID，方便跳转
			resultMap.put("success",true);
			resultMap.put("msg", "登录成功！");
		}else{
			resultMap.put("success",false);
			resultMap.put("msg", "用户名或密码错误！");
		}


	    return resultMap;

	}

	
}
