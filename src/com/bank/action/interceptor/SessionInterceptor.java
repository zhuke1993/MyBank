package com.bank.action.interceptor;

import java.util.Map;

import com.bank.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * �Զ���������
 * ���ó�ʼ����Ϊ��¼����
 * @author ��˧
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
		// ���session����user����˵�����ѵ�¼�û�
		if (user != null) {
			return arg0.invoke();
		} else {
			return LOGIN_PAGE;
		}
	}
}
