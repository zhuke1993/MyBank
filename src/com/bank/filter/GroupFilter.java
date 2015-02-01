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
 * 用户组的权限控制
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
		//普通用户
		if(groupId == 1){
			System.out.println("用户组类型为：普通用户");
			String url = req.getServletPath();
			//如果普通用户请求管理员目录，即admin目录，则报错
			if(url.contains("admin")){
				session.setAttribute("errorMsg", "你没有权限浏览该目录，请联系管理员");
				resp.sendRedirect("/errorAdmin.jsp");
				return;
			}
			
		}else if(groupId == 0){
			//管理员账户
			System.out.println("用户组类型为：管理员");
		}
		chain.doFilter(request, response);
		return ;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
