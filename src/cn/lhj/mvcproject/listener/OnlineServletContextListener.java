package cn.lhj.mvcproject.listener;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.swing.Timer;

import cn.lhj.mvcproject.model.Ponline;
import cn.lhj.mvcproject.service.FactoryService;
import cn.lhj.mvcproject.service.PonlineService;

@WebListener
public class OnlineServletContextListener implements ServletContextListener{
	public static final int MAX_MILLS = 10*60*1000;
	PonlineService ponlineService = FactoryService.getPonlineService();
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		List<Ponline> oList= new ArrayList<Ponline>();
		//服务器启动的时候执行。
		new Timer(5*1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Ponline> list = ponlineService.getAllOnline();
				if(list!=null&&list.size()!=0) {
					for(Ponline ol:list) {
						if(System.currentTimeMillis()-ol.getTime().getTime()>MAX_MILLS) {
							oList.add(ol);
						}
					}
				}
				
				if(oList.size()!=0) {
					ponlineService.deleteExpiresOnline(oList);
					oList.clear();
				}
				
			}
		}).start();
		
	}

}
