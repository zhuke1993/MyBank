package com.bank.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.entity.User;
import com.bank.service.UserService;
import com.bank.service.impl.UserServiceImpl;

public class RegisterAction extends DefaultActionSupport {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("����ע��action");
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserService service = new UserServiceImpl();
		user.setGroupId(1);
		int rs = service.register(user);
		if (rs == 1) {
			// ע��ɹ�
			session.setAttribute("regMsg", "ע��ɹ������½��");
			System.out.println("ע��ɹ���");
			return SUCCESS;
		} else {
			// ע��ʧ��
			session.setAttribute("regMsg", "ע��ʧ��");
			System.out.println("ע��ʧ�ܣ�");
			return INPUT;
		}
	}
}
