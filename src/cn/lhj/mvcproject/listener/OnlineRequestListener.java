package cn.lhj.mvcproject.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.lhj.mvcproject.model.Ponline;
import cn.lhj.mvcproject.service.FactoryService;
import cn.lhj.mvcproject.service.PonlineService;
/**
 * 任何一个请求过来都会监听
 * @author lihaijian
 *
 */
@WebListener
public class OnlineRequestListener implements ServletRequestListener{
	PonlineService ponlineService = FactoryService.getPonlineService();
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("OnlineRequestListener requestInitialized");
		HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
		HttpSession session = httpServletRequest.getSession();
		
		String ssid = session.getId();
		String ip = httpServletRequest.getRemoteAddr();
		String page = httpServletRequest.getRequestURI();
		String username = (String) session.getAttribute("user");
		
		//如果登录了应该有一个user的属性，否则就是游客，是游客就应该先登录才能做其他操作。
		if(username==null)
			username = "Traveller";
		
		Ponline ponline = ponlineService.getOnlineBySsid(ssid);
		//System.out.println(ponline);
		if(ponline==null) {
			Ponline ol = new Ponline();
			ol.setSsid(ssid);
			ol.setUsername(username);
			ol.setIp(ip);
			ol.setPage(page);
			ol.setTime(new Timestamp(new Date().getTime()));
			ponlineService.insertOnline(ol);
		}else {
			ponline.setUsername(username);
			ponline.setPage(page);
			ponline.setTime(new Timestamp(new Date().getTime()));
			ponlineService.updateOnline(ponline);
		}	
	}

}
