package com.bank.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.dao.UserDao;
import com.bank.entity.User;

public class ModifyUser extends DefaultActionSupport {
	private int id;
	private String name;
	private String pwd;
	private String phone;
	private String email;
	private String sex;
	private float balance;
	private int groupId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String execute() throws Exception {

		User user = new User(id, name, pwd, phone, email, sex, balance, groupId);
		System.out.println(user);
		int r = new UserDao().modifyUser(user);
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if (r == 1) {
			out.write("success");
		} else {
			out.write("faild");
		}
		return null;
	}
}
