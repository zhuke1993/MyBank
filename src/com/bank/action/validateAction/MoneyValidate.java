package com.bank.action.validateAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bank.dao.UserDao;
import com.bank.entity.User;
import com.bank.service.UserService;
import com.bank.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class MoneyValidate extends ActionSupport{
	private float money;
	
	private String pwd;
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public float getMoney() {
		System.out.println(money);
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

/*	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();// ��action�еõ�request��response�ķ�������ServletActionContext�еõ�
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserService userService = new UserServiceImpl();
		int rs = userService.isLogin(user.getName(), user.getPwd());
		System.out.println(user.getName());
		System.out.println(user.getPwd());
		if (rs == 1) {
			// ��½�ɹ�
			User loginUser = new UserDao().getUser(user.getName());
			session.setAttribute("loginUser", loginUser);
			if(loginUser.getGroupId() == 1){
				//��ͨ�û�
				return "normal";
			}else{
				//����Ա
				return "admin";
			}
		}
		session.setAttribute("loginMsg", "�û������������");
		return INPUT;
	}*/
}
