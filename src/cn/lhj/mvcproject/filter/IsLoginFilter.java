package cn.lhj.mvcproject.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.lhj.mvcproject.filter.BaseFilter;


public class IsLoginFilter extends BaseFilter{
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("------------被IsLoginFilter拦截了");
		FilterConfig filterConfig = getFilterConfig();
		String[] names = filterConfig.getInitParameter("authority").split(",");
		String servletName = req.getServletPath().substring(1);
		//System.out.println("servletName="+servletName);
		
		HttpSession session = req.getSession(true);
		for(String name:names) {
			if(name.equals(servletName)) {
				//System.out.println("name="+name);
				
				//System.out.println("session user="+session.getAttribute("user"));
				if(session.getAttribute("user")==null) {
					//System.out.println("In redirect");
					//System.out.println(req.getContextPath()+"/login.jsp");
					resp.sendRedirect(req.getContextPath()+"/login.jsp");
					return;
				}else {
					break;
				}
			}
		}
		//System.out.println("out");
		chain.doFilter(req, resp);
	}
}
