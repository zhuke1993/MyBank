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
		System.out.println("½øÈë×¢²áaction");
		HttpSession session = ServletActionContext.getRequest().getSession();
		UserService service = new UserServiceImpl();
		user.setGroupId(1);
		int rs = service.register(user);
		if (rs == 1) {
			// ×¢²á³É¹¦
			session.setAttribute("regMsg", "×¢²á³É¹¦£¬ÇëµÇÂ½£¡");
			System.out.println("×¢²á³É¹¦£¡");
			return SUCCESS;
		} else {
			// ×¢²áÊ§°Ü
			session.setAttribute("regMsg", "×¢²áÊ§°Ü");
			System.out.println("×¢²áÊ§°Ü£¡");
			return INPUT;
		}
	}
}
