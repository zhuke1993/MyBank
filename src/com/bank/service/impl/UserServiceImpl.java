package com.bank.service.impl;

import java.util.ArrayList;

import com.bank.dao.UserDao;
import com.bank.entity.User;
import com.bank.service.UserService;

public class UserServiceImpl implements UserService{

	
	public int deleteUser(User user) {
		return 0;
	}

	public int isLogin(String name, String pwd) {
		
		return new UserDao().isLogin(name, pwd);
	}

	public int register(User user) {
		return new UserDao().register(user);
	}

	public ArrayList<User> allList() {
		
		return new UserDao().allList();
	}

	public int modifyUser(User user) {
		return new UserDao().modifyUser(user);
	}

}
