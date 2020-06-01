package cn.lhj.mvcproject.dao;

import java.util.List;

import cn.lhj.mvcproject.model.Ponline;

public class PonlineImpl extends BaseDao<Ponline> implements PonlineDao{

	@Override
	public List<Ponline> getAllOnline() {
		String sql = "select * from ponline";
		List<Ponline> list = super.getList(sql);
		return list;
	}

	@Override
	public int insertOnline(Ponline ponline) {
		String sql = "insert into ponline(ssid,username,page,ip,time) values(?,?,?,?,?)";
		return super.update(sql, ponline.getSsid(),ponline.getUsername(),ponline.getPage(),ponline.getIp(),ponline.getTime());
		
		
	}

	@Override
	public int updateOnline(Ponline ponline) {
		String sql = "update ponline set username=?,page=?,ip=?,time=? where ssid=?";
		return super.update(sql, ponline.getUsername(),ponline.getPage(),ponline.getIp(),ponline.getTime(),ponline.getSsid());
		
		
	}

	@Override
	public int deleteExpiresOnline(String ssid) {
		String sql = "delete from ponline where ssid=?";
		return super.update(sql, ssid);
		
		
	}

	@Override
	public Ponline getOnlineBySsid(String ssid) {
		String sql = "select * from ponline where ssid=?";
		return super.get(sql, ssid);
	}

}
