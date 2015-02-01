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
 * 用于检测用户是否登陆的过滤器，如果未登录，则重定向到指的登录页面 配置参数 checkSessionKey 需检查的在 Session 中保存的关键字
 * 
 * redirectURL 如果用户未登录，则重定向到指定的页面，URL不包括 ContextPath
 * 
 * notCheckURLList 不做检查的URL列表，以分号分开，并且 URL 中不包括 ContextPath
 */
public class LoginFilter implements Filter {

	protected FilterConfig filterConfig = null;
	private String redirectURL = null;
	private List<String> notCheckURLList = new ArrayList<>();
	private String sessionKey = null;

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("正在过滤登陆请求");
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		HttpSession session = request.getSession();
		// 如果请求页面在排除列表中，则正常执行后面的动作
		if (checkRequestURIIntNotFilterList(request)) {
			filterChain.doFilter(request, response);
			return;
		} else {
			// 请求页面不再排除列表中，则判断session中是否有loginUser的值
			// 未登陆
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
	 * 验证请求页面是否在非验证页面里面
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
		String uri = request.getServletPath();
		if(uri.contains(".css") || uri.contains(".js") || uri.contains(".jpg") || uri.contains(".png")){
			//请求的是资源文件，不进行过滤
			return true;
		}
		return notCheckURLList.contains(uri);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// 将传递进来的notCheckURLList页面得到
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