package cn.lhj.mvcproject.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import cn.lhj.mvcproject.utils.JdbcUtils;

class JdbcUtilsTest {

	@Test
	void testGetConnection() {
		
		 Connection conn = JdbcUtils.getConnection(); System.out.println(conn);
		 
	}

}
