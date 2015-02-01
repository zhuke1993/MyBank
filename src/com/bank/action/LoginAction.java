package com.bank.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bank.dao.UserDao;
import com.bank.entity.User;
import com.bank.service.UserService;
import com.bank.service.impl.UserServiceImpl;

public class LoginAction extends DefaultActionSupport {
	private User user;
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();// 在action中得到request和response的方法，在ServletActionContext中得到
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserService userService = new UserServiceImpl();
		int rs = userService.isLogin(user.getName(), user.getPwd());
		System.out.println(user.getName());
		System.out.println(user.getPwd());
		if (rs == 1) {
			// 登陆成功
			User loginUser = new UserDao().getUser(user.getName());
			session.setAttribute("loginUser", loginUser);
			if(loginUser.getGroupId() == 1){
				//普通用户
				return "normal";
			}else{
				//管理员
				return "admin";
			}
		}
		session.setAttribute("loginMsg", "用户名或密码错误！");
		return INPUT;
	}
}
