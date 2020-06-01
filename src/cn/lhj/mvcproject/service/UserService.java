package cn.lhj.mvcproject.service;

import java.sql.Connection;
import java.util.List;

import cn.lhj.mvcproject.model.User;

public interface UserService {
	/**
	 * 实现插入一条用户信息。
	 * @param user
	 * @return
	 */
	public int save(User user);
	
	
	
	public int deleteUserById(int id);
	
	
	
	public int updateUserById(User user);
	
	
	public User get(int id);
	
	public User getTransaction(int id);
	
	
	public List<User> getListAll();
	
	public int getCountByName(String username);


	/**
	 * 用户模糊查询的方法
	 * @param username
	 * @param address
	 * @param phoneno
	 * @return 
	 */
	public List<User> query(String username, String address, String phoneno);


	/**
	 * 这个是判断用户的用户名和密码对不对的方法。
	 * @param username
	 * @param passwd
	 * @return
	 */
	public User login(String username, String passwd);
}
