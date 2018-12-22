package xxxy.demo.model;

import java.util.Date;

public class Users {
	private String id;
	private String username;
	private String userpwd;
	private String phone;
	private String box_id;
	private String crdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBox_id() {
		return box_id;
	}
	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}

	public String getCrdate() {
		return crdate;
	}
	public void setCrdate(String crdate) {
		this.crdate = crdate;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", userpwd=" + userpwd + ", phone=" + phone + ", box_id="
				+ box_id + ", crdate=" + crdate + "]";
	}
	
	

}
