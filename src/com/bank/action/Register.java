package com.bank.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.dao.UserDao;
import com.bank.entity.User;

public class Register extends DefaultActionSupport {
	private String name1;
	private String pwd1;
	private String phone1;
	private String email1;
	private String sex1;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getSex1() {
		return sex1;
	}

	public void setSex1(String sex1) {
		this.sex1 = sex1;
	}

	public String execute() throws Exception {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		User user = new User(name1, pwd1, phone1, email1, sex1);
		user.setGroupId(1);
		System.out.println(user);
		int r = new UserDao().register(user);
		if (r == 1) {
			out.print("success");
		} else {
			out.print("faild");
		}
		return null;
	}
}
