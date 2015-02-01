package com.bank.service;

import java.util.ArrayList;

import com.bank.entity.User;

public interface UserService {
	
	public int deleteUser(User user);
	
	public int  modifyUser(User user);
	
	/**
	 * �õ������û�
	 * @return
	 */
	public ArrayList<User> allList();
	
	/**
	 * 
	 * @param name
	 * @param pwd
	 * @return 0--��½ʧ��  1--��½�ɹ�
	 */
	public int isLogin(String name, String pwd);
	
	/**
	 * ���û�ע��
	 * @param user
	 * @return  1--ע��ɹ���0--ע��ʧ��
	 */
	public int register(User user);
	
}
