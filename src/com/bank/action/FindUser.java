package com.bank.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.dao.UserDao;
import com.bank.entity.User;
import com.google.gson.Gson;

public class FindUser  extends DefaultActionSupport{
	private int id2;
	private String name2;

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String execute() throws Exception {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		User user = new User();
		if(id2 != 0 && name2 == null){
			user = new UserDao().getUser(id2);
		}else if(id2 == 0 && name2 != null){
			user = new UserDao().getUser(name2);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(new Gson().toJson(user));
		System.out.println(sb.toString());
		out.write(sb.toString());
		out.close();
		return null;
	}
}
