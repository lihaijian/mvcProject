package cn.lhj.mvcproject.service;

import java.util.List;

import cn.lhj.mvcproject.model.Ponline;

public interface PonlineService {
	public List<Ponline> getAllOnline();//查
	public int insertOnline(Ponline ponline);//增
	public int updateOnline(Ponline ponline);//改
	public Ponline getOnlineBySsid(String ssid);//查
	public void deleteExpiresOnline(List<Ponline> oList);//删
}
