package cn.lhj.mvcproject.service;

import java.util.List;

import cn.lhj.mvcproject.dao.FactoryDao;
import cn.lhj.mvcproject.dao.PonlineDao;
import cn.lhj.mvcproject.model.Ponline;

public class PonlineServiceImpl implements PonlineService{
	PonlineDao ponlineDao = FactoryDao.getPonlineDao();
	
	@Override
	public List<Ponline> getAllOnline() {
		// TODO Auto-generated method stub
		return ponlineDao.getAllOnline();
	}

	@Override
	public int insertOnline(Ponline ponline) {
		return ponlineDao.insertOnline(ponline);
	}

	@Override
	public int updateOnline(Ponline ponline) {
		return ponlineDao.updateOnline(ponline);
	}



	@Override
	public void deleteExpiresOnline(List<Ponline> oList) {
		if(oList!=null&&oList.size()!=0) {
			for(Ponline ponline:oList) {
				ponlineDao.deleteExpiresOnline(ponline.getSsid());
			}
		}
		
	}

	@Override
	public Ponline getOnlineBySsid(String ssid) {
		// TODO Auto-generated method stub
		return ponlineDao.getOnlineBySsid(ssid);
	}

}
