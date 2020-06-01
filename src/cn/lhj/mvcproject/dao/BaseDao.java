package cn.lhj.mvcproject.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.LifecycleListener;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;



import cn.lhj.mvcproject.utils.JdbcUtils;

/**
 * 基本类，给具体类继承的，一般不会new BaseDao<T>
 * 数据库的增删改查
 * @author lihaijian
 *
 * @param <T>
 */
public class BaseDao<T> {
	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;
	
	public BaseDao(){
		Type superType = this.getClass().getGenericSuperclass();
		System.out.println(this.getClass());
		System.out.println(superType);
		if(superType instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) superType;
			Type[] tarray = pt.getActualTypeArguments();
			System.out.println(tarray[0]);
			if (tarray[0] instanceof Class) {
				clazz = (Class<T>) tarray[0];
			}
		}
	}
	/**
	 * 查询数据表，返回结果集的第一条数据，封装成一个类的对象进行返回。不支持事务。
	 * 使用dbutils工具类。
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql,Object... args) {
		Connection conn = null;
		T entity = null;
		try {
			conn = JdbcUtils.getConnection();
			entity = queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return entity;
	}
	
	/**
	 * 查询数据表，返回结果集的第一条数据，封装成一个类的对象进行返回。支持事务。
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(Connection conn,String sql,Object... args) {
		T entity = null;
		try {
			entity = queryRunner.query(conn, sql, new BeanHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	/**
	 * 查询所有记录的通用方法。
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getList(String sql,Object... args){
		Connection conn = null;
		List<T> list = new ArrayList<>();
		try {
			conn = JdbcUtils.getConnection();
			list = queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return list;
	}
	
	/**
	 * 增改删通用方法。
	 * @param sql
	 * @param args
	 * @return
	 */
	public int update(String sql, Object... args) {
		Connection conn = null;
		int rows = 0;
		try {
			conn = JdbcUtils.getConnection();
			rows = queryRunner.update(conn, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return rows;
	}
	
	/**
	 * 返回select语句只有一个值的方法。
	 * @param sql
	 * @param args
	 * @return
	 */
	public Object getValue(String sql,Object... args) {
		Connection conn = null;
		Object obj = null;
		try {
			conn = JdbcUtils.getConnection();
			obj = queryRunner.query(conn, sql,new ScalarHandler<T>(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return obj;
	}
}
