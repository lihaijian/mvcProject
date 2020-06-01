package cn.lhj.mvcproject.model;



import java.util.Date;

public class User {
	private int id;
	private String username;
	private String passwd;
	private String phoneNo;
	private String address;
	private Date regDate;
	
	
	public User(String username, String passwd, String phoneNo, String address) {
		super();
		this.username = username;
		this.passwd = passwd;
		this.phoneNo = phoneNo;
		this.address = address;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passwd=" + passwd + ", phoneNo=" + phoneNo
				+ ", address=" + address + ", regDate=" + regDate + "]";
	}
	
}
