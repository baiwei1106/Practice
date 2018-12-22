package xxxy.demo.model;

public class Memory {
	private String id;
	private String user_id;
	private String box_id;
	private String flag;
	private String date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBox_id() {
		return box_id;
	}
	public void setBox_id(String box_id) {
		this.box_id = box_id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Memory [id=" + id + ", user_id=" + user_id + ", box_id=" + box_id + ", flag=" + flag + ", date=" + date
				+ "]";
	}
	
	

}
