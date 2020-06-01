package cn.lhj.mvcproject.dao;

import java.util.List;

import cn.lhj.mvcproject.model.Ponline;

public interface PonlineDao {
	public List<Ponline> getAllOnline();//查
	public int insertOnline(Ponline ponline);//增
	public int updateOnline(Ponline ponline);//改
	public int deleteExpiresOnline(String ssid);//删
	public Ponline getOnlineBySsid(String ssid);//查
}
