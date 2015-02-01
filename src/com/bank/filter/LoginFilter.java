package com.bank.filter;

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

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;

/**
 * ���ڼ���û��Ƿ��½�Ĺ����������δ��¼�����ض���ָ�ĵ�¼ҳ�� ���ò��� checkSessionKey ������� Session �б���Ĺؼ���
 * 
 * redirectURL ����û�δ��¼�����ض���ָ����ҳ�棬URL������ ContextPath
 * 
 * notCheckURLList ��������URL�б��Էֺŷֿ������� URL �в����� ContextPath
 */
public class LoginFilter implements Filter {

	protected FilterConfig filterConfig = null;
	private String redirectURL = null;
	private List<String> notCheckURLList = new ArrayList<>();
	private String sessionKey = null;

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("���ڹ��˵�½����");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		HttpSession session = request.getSession();
		// �������ҳ�����ų��б��У�������ִ�к���Ķ���
		if (checkRequestURIIntNotFilterList(request)) {
			filterChain.doFilter(request, response);
			return;
		} else {
			// ����ҳ�治���ų��б��У����ж�session���Ƿ���loginUser��ֵ
			// δ��½
			if (((User) session.getAttribute("loginUser")) == null) {
				response.sendRedirect("/zhushuai/login.jsp");
				filterChain.doFilter(request, response);
				return;
			} else {
				filterChain.doFilter(request, response);
				return;
			}
		}

	}

	public void destroy() {
		notCheckURLList.clear();
	}

	/**
	 * ��֤����ҳ���Ƿ��ڷ���֤ҳ������
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
		String uri = request.getServletPath();
		if(uri.contains(".css") || uri.contains(".js") || uri.contains(".jpg") || uri.contains(".png")){
			//���������Դ�ļ��������й���
			return true;
		}
		return notCheckURLList.contains(uri);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// �����ݽ�����notCheckURLListҳ��õ�
		this.filterConfig = filterConfig;
		redirectURL = filterConfig.getInitParameter("redirectURL");
		sessionKey = filterConfig.getInitParameter("checkSessionKey");

		String notCheckURLListStr = filterConfig
				.getInitParameter("notCheckURLList");

		if (notCheckURLListStr != null) {
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
			notCheckURLList.clear();
			while (st.hasMoreTokens()) {
				notCheckURLList.add(st.nextToken());
			}
		}
	}
}