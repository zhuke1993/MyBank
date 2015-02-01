package com.bank.service;

import java.util.ArrayList;

import com.bank.entity.User;

public interface UserService {
	
	public int deleteUser(User user);
	
	public int  modifyUser(User user);
	
	/**
	 * 得到所有用户
	 * @return
	 */
	public ArrayList<User> allList();
	
	/**
	 * 
	 * @param name
	 * @param pwd
	 * @return 0--登陆失败  1--登陆成功
	 */
	public int isLogin(String name, String pwd);
	
	/**
	 * 新用户注册
	 * @param user
	 * @return  1--注册成功，0--注册失败
	 */
	public int register(User user);
	
}
