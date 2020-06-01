package cn.lhj.mvcproject.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import cn.lhj.mvcproject.model.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into people(username,passwd,phoneno,address,regdate) values(?,?,?,?,(select sysdate from dual))";
		return super.update(sql, user.getUsername(),user.getPasswd(),user.getPhoneNo(),user.getAddress());
	}

	@Override
	public int deleteUserById(int id) {
		String sql = "delete from people where id=?";
		return super.update(sql, id);
	}

	@Override
	public int updateUserById(User user) {
		String sql = "update people set username=?,passwd=?,phoneno=?,address=?,regdate=(select sysdate from dual) where id=?";
		return super.update(sql, user.getUsername(),user.getPasswd(),user.getPhoneNo(),user.getAddress(),user.getId());
	}

	@Override
	public User get(int id) {
		String sql = "select id ,username,passwd,phoneno,address,regdate from people where id=?";
		return super.get(sql, id);
	}
	
	@Override
	public User get(Connection conn, int id) {
		// TODO Auto-generated method stub
		String sql = "select id ,username,passwd,phoneno,address,regdate from people where id=?";
		return super.get(conn,sql, id);
	}

	@Override
	public List<User> getListAll() {
		// TODO Auto-generated method stub
		String sql = "select * from people";
		return super.getList(sql);
	}

	@Override
	public int getCountByName(String username) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from people where username=?";
		BigDecimal obj = (BigDecimal) super.getValue(sql, username);
		
		return obj.intValue();
	}
	
	
	@Override
	public List<User> query(String username, String address, String phoneno) {
		// TODO Auto-generated method stub
		String sql="select id,username,passwd,address,phoneno,regdate from people where 1=1";
		if(username!=null&&!username.equals("")) {
			sql=sql+" and username like '%"+username+"%'";
		}
		if(address!=null&&!address.equals("")) {
			sql=sql+" and address like '%"+address+"%'";
		}
		if(phoneno!=null&&!phoneno.equals("")) {
			sql=sql+" and phoneno like '%"+phoneno+"%'";
		}
		System.out.println(sql);
		return super.getList(sql);
		
	}

	@Override
	public User getUserByUp(String username, String passwd) {
		String sql = "select id,username,passwd,address,phoneno,regdate from people where 1=1 and username=? and passwd=?";
		return super.get(sql, username,passwd);
		
	}

	

}
