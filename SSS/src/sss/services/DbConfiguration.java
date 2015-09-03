package sss.services;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DbConfiguration implements Serializable {
	private String dbFullPath;
	private String userName;
	private String password;
	
	public DbConfiguration(String path, String userName, String password) {
		dbFullPath = path;
		this.userName = userName;
		this.password = password;
	}
	
	public String getPath() {
		return dbFullPath;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPath(String newPath) {
		dbFullPath = newPath;
	}
	
	public void setUserName(String newUserName) {
		userName = newUserName;
	}
	
	public void setPassword(String newPassword) {
		password = newPassword;
	}
}
