package cn.lhj.mvcproject.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.lhj.mvcproject.dao.FactoryDao;
import cn.lhj.mvcproject.dao.UserDao;
import cn.lhj.mvcproject.dao.UserDaoImpl;
import cn.lhj.mvcproject.model.User;
import cn.lhj.mvcproject.utils.JdbcUtils;

public class UserServiceImp implements UserService {
	
	UserDao userdao = FactoryDao.getUserDao();
	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		return userdao.save(user);
	
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return userdao.deleteUserById(id);
	}

	@Override
	public int updateUserById(User user) {
		// TODO Auto-generated method stub
		return userdao.updateUserById(user);
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return userdao.get(id);
	}
	
	@Override
	public User getTransaction(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false);
			user = userdao.get(conn, id);
			conn.commit();
		}catch (Exception e) {
			JdbcUtils.rollbackTransaction(conn);
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return user;
		
	}
	
	@Override
	public List<User> getListAll() {
		// TODO Auto-generated method stub
		return userdao.getListAll();
	}

	@Override
	public int getCountByName(String username) {
		// TODO Auto-generated method stub
		return userdao.getCountByName(username);
	}

	@Override
	public List<User> query(String username, String address, String phoneno) {
		return userdao.query(username,address,phoneno);
		
	}

	@Override
	public User login(String username, String passwd) {
		// TODO Auto-generated method stub
		return userdao.getUserByUp(username,passwd);
	}

	

}
