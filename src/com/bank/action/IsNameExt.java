package com.bank.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.dao.UserDao;
/**
 * 验证用户名是否已经存在
 * @author ZHUKE
 *
 */
public class IsNameExt extends DefaultActionSupport{
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String execute() throws Exception {
		//0--不存在，1--存在
		int rs = new UserDao().isNameExist(name);
		
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		writer.write(new Integer(rs).toString());
		writer.flush();
		writer.close();
		
		return null;
	}
}
