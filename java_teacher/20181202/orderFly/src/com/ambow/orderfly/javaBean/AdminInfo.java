package com.ambow.orderfly.javaBean;

public class AdminInfo {
	private String loginName;
	private String loginPwd;

	public AdminInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminInfo(String loginName, String loginPwd) {
		super();
		this.loginName = loginName;
		this.loginPwd = loginPwd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

}
