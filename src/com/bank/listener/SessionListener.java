package com.bank.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bank.entity.User;

public class SessionListener implements HttpSessionAttributeListener,
		HttpSessionListener {
	public static ArrayList<User> onlineUser = new ArrayList<>();

	
	public static ArrayList<User> getOnlineUser() {
		return onlineUser;
	}

	public static void setOnlineUser(ArrayList<User> onlineUser) {
		SessionListener.onlineUser = onlineUser;
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		User u = (User) se.getSession().getAttribute("loginUser");
		if (u != null && u.getGroupId() == 1) {
			onlineUser.add(u);
			System.out.println(new Date().toGMTString() + "用户" + u.getName()
					+ "登陆");
		}

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		User u = (User) se.getSession().getAttribute("loginUser");
		if (u != null) {
			onlineUser.remove(u);
			System.out.println(new Date().toGMTString() + "用户" + u.getName()
					+ "退出登陆");
		}
	}

}
