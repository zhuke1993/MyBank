package com.bank.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.dao.UserDao;

public class DeleteUser extends DefaultActionSupport{
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String execute() throws Exception {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int r = new UserDao().deleteUser(id);
		if(r == 1){
			out.print("success");
		}else{
			out.print("faild");
		}
		return null;
	}
}	
