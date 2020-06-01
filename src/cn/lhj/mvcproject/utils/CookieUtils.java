package cn.lhj.mvcproject.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class CookieUtils {
	private final static String KEY="dfdfw";
	/**
	 * 创建cookie
	 * @param username
	 * @param req
	 * @param resp
	 */
	public static void createCookie(String username,HttpServletRequest req,HttpServletResponse resp,int sec) {
		Cookie usercookie = new Cookie("userKey", username);
		
		Cookie ssidcookie = new Cookie("ssid",md5Encrypt(username));
		usercookie.setMaxAge(sec);
		ssidcookie.setMaxAge(sec);
		resp.addCookie(usercookie);
		resp.addCookie(ssidcookie);
	}
	public static String md5Encrypt(String str) {
		//System.out.println(Charset.defaultCharset());
		str = str==null?"":str+KEY;
		
		char[] md5Digist = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bstr = str.getBytes();
			md.update(bstr);
			byte[] mbstr = md.digest();
			//System.out.println(mbstr);
			int k=0;
			int len = mbstr.length;
			char[] nextMbstr = new char[len*2];
			for(int i=0;i<len;i++) {
				byte b = mbstr[i];
				nextMbstr[k++] = md5Digist[b>>>4 & 0xf];
				nextMbstr[k++] = md5Digist[b & 0xf];
			}
			return new String(nextMbstr);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;


	}
}
