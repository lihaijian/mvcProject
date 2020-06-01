package cn.lhj.mvcproject.filter;

import java.io.IOException;
import java.net.CookieStore;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.lhj.mvcproject.model.Ponline;
import cn.lhj.mvcproject.service.FactoryService;
import cn.lhj.mvcproject.service.PonlineService;
import cn.lhj.mvcproject.utils.CookieUtils;

@WebListener
public class AutoLoginFilter extends BaseFilter{
	PonlineService ponlineService = FactoryService.getPonlineService();
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		System.out.println("AutoLoginFilter doFilter");
		
		Cookie[] cookies = req.getCookies();
		if(cookies!=null&&cookies.length!=0) {
			String username = null;
			String ssid = null;
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("userKey")) {
					username = cookie.getValue();
				}
				if(cookie.getName().equals("ssid")) {
					ssid = cookie.getValue();
				}
			}

			if(username!=null&&ssid!=null&&CookieUtils.md5Encrypt(username).equals(ssid)) {
				HttpSession session = req.getSession();
				session.setAttribute("user", username);
				
				//自动登录时设置Traveller为username
				Ponline ponline = ponlineService.getOnlineBySsid(session.getId());
				if(ponline!=null) {
					ponline.setUsername(username);
					ponlineService.updateOnline(ponline);
				}				
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}else {
				chain.doFilter(req, resp);
			}
		}else {
			chain.doFilter(req, resp);
		}
		
			
		
	}
}
