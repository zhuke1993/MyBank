package com.bank.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

public class CheckValidate extends DefaultActionSupport {
	private String validate;

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	@Override
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		PrintWriter writer = ServletActionContext.getResponse().getWriter();
		String va = (String) session.getAttribute("validate");
		if (va.endsWith(validate)) {
			// ��֤����ȷ
			writer.write(new Integer(1).toString());
			writer.flush();
			writer.close();
		} else {
			// ��֤�����
			writer.write(new Integer(0).toString());
			writer.flush();
			writer.close();
		}

		return null;
	}
}
