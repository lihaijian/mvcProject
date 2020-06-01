package cn.lhj.mvcproject.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.lhj.mvcproject.dao.PonlineDao;
import cn.lhj.mvcproject.dao.PonlineImpl;
import cn.lhj.mvcproject.model.Ponline;

class PonlineImplTest {

	@Test
	void insertOnlineTest() {
		Ponline ponline = new Ponline();
		ponline.setSsid("efg");
		ponline.setUsername("lxj");
		ponline.setIp("10.9.4.175");
		ponline.setPage("login.jsp");
		ponline.setTime(new Timestamp(new Date().getTime()));
		PonlineDao ponlineDao = new PonlineImpl();
		System.out.println(ponlineDao.insertOnline(ponline));
	}
	
	@Test
	void updateOnlineTest() {
		Ponline ponline = new Ponline();
		ponline.setSsid("abc");
		ponline.setUsername("lhj111");
		ponline.setIp("10.9.4.174");
		ponline.setPage("login.jsp");
		ponline.setTime(new Timestamp(new Date().getTime()));
		PonlineDao ponlineDao = new PonlineImpl();
		System.out.println(ponlineDao.updateOnline(ponline));
	}
	
	@Test
	void getAllOnlineTest() {
		PonlineDao ponlineDao = new PonlineImpl();
		List<Ponline> list = ponlineDao.getAllOnline();
		System.out.println(list.size());
	}

	@Test
	void deleteExpiresOnline() {
		PonlineDao ponlineDao = new PonlineImpl();
		String ssid = "abc";
		System.out.println(ponlineDao.deleteExpiresOnline(ssid));
	}
	
	@Test
	void getOnlineBySsid() {
		PonlineDao ponlineDao = new PonlineImpl();
		String ssid = "efg";
		System.out.println(ponlineDao.deleteExpiresOnline(ssid));
	};

}
