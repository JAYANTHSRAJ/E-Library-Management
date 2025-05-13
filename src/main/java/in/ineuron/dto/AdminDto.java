package in.ineuron.dto;

import java.io.Serializable;

public class AdminDto implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	Integer adminId;
	String adminName;
	String adminEmail;
	String adminPassword;
	@Override
	public String toString() {
		return "AdminDto [adminId=" + adminId + ", adminName=" + adminName + ", adminEmail=" + adminEmail
				+ ", adminPassword=" + adminPassword + "]";
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	
	
}
