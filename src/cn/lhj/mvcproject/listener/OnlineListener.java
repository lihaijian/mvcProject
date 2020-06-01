package cn.lhj.mvcproject.listener;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



public class OnlineListener implements HttpSessionListener,HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("attributeAdded");
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		Map<String, String> online = (Map<String, String>) application.getAttribute("online");
		if(online==null) {
			online = new HashMap<String, String>();
		}
		//用户登录成功时会在session属性空间注入用户名，属性标识是user。
		online.put(session.getId(),session.getAttribute("user").toString());
		application.setAttribute("online", online);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext application = session.getServletContext();
		Map<String, String> online = (Map<String, String>) application.getAttribute("online");
		
		if(online==null) {
			online = new HashMap<String, String>();
		}
		String username = online.get(session.getId());
		if(username==null) {
			username = "Traveler";
		}
		online.put(session.getId(),username);
		application.setAttribute("online", online);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext application = session.getServletContext();
		Map<String, String> online = (Map<String, String>) application.getAttribute("online");
		
		if(online!=null) {
			online.remove(session.getId());
			application.setAttribute("online", online);
		}
		
	}

}
