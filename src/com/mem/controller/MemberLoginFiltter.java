package com.mem.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class MemberLogin
 */
@WebFilter("/MemberLoginFiltter")
public class MemberLoginFiltter implements Filter {

	private FilterConfig config;
    public MemberLoginFiltter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		config = null;
	}

	
		public void doFilter(ServletRequest request, ServletResponse response, 
		FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object account = session.getAttribute("memVO");
		System.out.println("1111111111111111");
		System.out.println("account="+account);
		if (account == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front_end/mem/MemLogin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
