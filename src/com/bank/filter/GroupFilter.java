package com.bank.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.entity.User;

/**
 * �û����Ȩ�޿���
 * 
 * @author ZHUKE
 * 
 */
public class GroupFilter implements Filter {

	public GroupFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		int groupId=1;
		if ((User) session.getAttribute("loginUser") != null) {
			groupId = ((User) session.getAttribute("loginUser"))
					.getGroupId();
			
		}
		//��ͨ�û�
		if(groupId == 1){
			System.out.println("�û�������Ϊ����ͨ�û�");
			String url = req.getServletPath();
			//�����ͨ�û��������ԱĿ¼����adminĿ¼���򱨴�
			if(url.contains("admin")){
				session.setAttribute("errorMsg", "��û��Ȩ�������Ŀ¼������ϵ����Ա");
				resp.sendRedirect("/errorAdmin.jsp");
				return;
			}
			
		}else if(groupId == 0){
			//����Ա�˻�
			System.out.println("�û�������Ϊ������Ա");
		}
		chain.doFilter(request, response);
		return ;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
