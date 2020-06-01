package cn.lhj.mvcproject.test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.lhj.mvcproject.dao.UserDao;
import cn.lhj.mvcproject.dao.UserDaoImpl;
import cn.lhj.mvcproject.model.User;
import cn.lhj.mvcproject.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Timestamp;


class UserDaoImplTest {
	UserDao userDao = new UserDaoImpl();
	@Test
	void testSave() {
		User user = new User("bb", "12221111", "3434347456", "guanyaojiaoyulu37");
		System.out.println(userDao.save(user));
	}

	@Test
	void testDeleteUserById() {
		System.out.println(userDao.deleteUserById(56));
	}

	@Test
	void testUpdateUserById() {
		User user = new User("bb", "12221111", "3434347456", "guanyaojiaoyulu37");
		user.setId(5);
		System.out.println(userDao.updateUserById(user));
	}

	@Test
	void testGetInt() {
		User user = userDao.get(6);
		System.out.println(user);
	}

	@Test
	void testGetConnectionInt() {
		Connection conn = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false);
			user = userDao.get(conn, 7);
			conn.commit();
		}catch (Exception e) {
			JdbcUtils.rollbackTransaction(conn);
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		System.out.println(user);
	}

	@Test
	void testGetListAll() {
		List<User> list = userDao.getListAll();
		Iterator<User> iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}

	@Test
	void testGetCountByName() {
		int rows = userDao.getCountByName("lhj");
		System.out.println(rows);
	}

}
