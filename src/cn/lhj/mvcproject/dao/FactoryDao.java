package cn.lhj.mvcproject.dao;

public class FactoryDao {
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}
	public static PonlineDao getPonlineDao() {
		return new PonlineImpl();
	}
}
