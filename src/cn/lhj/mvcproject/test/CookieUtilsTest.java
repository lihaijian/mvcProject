package cn.lhj.mvcproject.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cn.lhj.mvcproject.utils.CookieUtils;

class CookieUtilsTest {

	@Test
	void test() {
		System.out.println(CookieUtils.md5Encrypt("lhj"));
	}

}
