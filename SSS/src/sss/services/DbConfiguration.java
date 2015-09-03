/* DbConfiguration Class
 * 
 * A data structure used to represent database connection configuration. It is saved 
 * and read by DbConfigurator
 * 
 * Original Author: Josh Kent
 */

package sss.services;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DbConfiguration implements Serializable {
	
	// ==========================================================================
	// Variables
	// ==========================================================================
	
	
	
	private String dbFullPath;
	private String userName;
	private String password;
	
	
	
	// ==========================================================================
	// Constructor
	// ==========================================================================
	
	
	
	public DbConfiguration(String path, String userName, String password) {
		dbFullPath = path;
		this.userName = userName;
		this.password = password;
	}
	
	
	
	// ==========================================================================
	// Getter Methods
	// ==========================================================================
	
	
	
	public String getPath() {
		return dbFullPath;
	}
	
	
	
	public String getUserName() {
		return userName;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	
	
	
	// ==========================================================================
	// Setter Methods
	// ==========================================================================
	
	
	
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
