package cn.lhj.mvcproject.model;

import java.sql.Timestamp;
import java.util.Date;

public class Ponline {
	private String ssid;
	private String username;
	private String page;
	private String ip;
	private Timestamp time;

	public Ponline() {

	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "Ponline [ssid=" + ssid + ", username=" + username + ", page=" + page + ", ip=" + ip + ", time=" + time
				+ "]";
	}
}
