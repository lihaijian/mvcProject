package cn.lhj.mvcproject.service;

public class FactoryService {
	public static UserService getUserService() {
		return new UserServiceImp();
	}
	public static PonlineService getPonlineService() {
		return new PonlineServiceImpl();
	}
}
