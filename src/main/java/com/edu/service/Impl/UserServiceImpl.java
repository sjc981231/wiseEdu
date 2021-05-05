package com.edu.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.dao.UserDao;
import com.edu.entity.User;
import com.edu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public User login(String username, String password) {
		return userDao.findByUserNameAndPassword(username,password);
	}


}
