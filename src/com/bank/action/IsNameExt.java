package com.bank.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.dao.UserDao;
/**
 * ��֤�û����Ƿ��Ѿ�����
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
		//0--�����ڣ�1--����
		int rs = new UserDao().isNameExist(name);
		
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		writer.write(new Integer(rs).toString());
		writer.flush();
		writer.close();
		
		return null;
	}
}
