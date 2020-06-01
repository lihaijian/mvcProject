package cn.lhj.mvcproject.dao;


import java.sql.Connection;
import java.util.List;

import cn.lhj.mvcproject.model.User;

/**
 * 接口定义规则，只定义方法，不实现，UserDao，定义与Users数据表有关系的操作方法；
 * @author lihaijian
 *
 */
public interface UserDao {
	
	/**
	 * 实现插入一条用户信息。
	 * @param user
	 * @return
	 */
	public int save(User user);
	
	
	
	public int deleteUserById(int id);
	
	
	
	public int updateUserById(User user);
	
	
	public User get(int id);
	
	public User get(Connection conn,int id);
	
	
	public List<User> getListAll();
	
	public int getCountByName(String username);


	/**
	 * 实现模糊查询的方法
	 * @param username
	 * @param address
	 * @param phoneno
	 * @return
	 */
	public List<User> query(String username, String address, String phoneno);


	/**
	 * 用用户名和密码查询用户的方法。
	 * @param username
	 * @param passwd
	 */
	public User getUserByUp(String username, String passwd);
}
