package cn.lhj.mvcproject.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import oracle.jdbc.driver.OracleDriver;

/**
 * JDBC工具类 
 * @author lihaijian
 *
 */
public class JdbcUtils {
	
	/**
	 * 初始化数据库连接池 ，项目中只有一个实例。
	 */
	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("oracle");	
	}
	/**
	 * 获取oracle数据库连接对象
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void rollbackTransaction(Connection conn) {
		if(conn!=null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
