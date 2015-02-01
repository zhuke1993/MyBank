package com.bank.action.interceptor;

import java.util.Map;

import com.bank.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 自定义拦截器
 * 设置初始界面为登录界面
 * @author 朱帅
 *
 */
//@SuppressWarnings("serial")
public class SessionInterceptor extends AbstractInterceptor {
	private static final Object LOGIN_KEY = "loginUser";

	public static final String LOGIN_PAGE = "loginPage";
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map session = arg0.getInvocationContext().getSession();
		User user = (User) session.get(LOGIN_KEY);
		// 如果session中有user对象说明是已登录用户
		if (user != null) {
			return arg0.invoke();
		} else {
			return LOGIN_PAGE;
		}
	}
}
