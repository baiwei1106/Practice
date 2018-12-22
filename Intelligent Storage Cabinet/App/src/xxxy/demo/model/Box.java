package xxxy.demo.model;

public class Box {
	private String box_id;
	private String flag;
	private String userid;
	private String operateflag;
	private String unkonwuserid;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOperateflag() {
		return operateflag;
	}
	public void setOperateflag(String operateflag) {
		this.operateflag = operateflag;
	}
	public String getUnkonwuserid() {
		return unkonwuserid;
	}
	public void setUnkonwuserid(String unkonwuserid) {
		this.unkonwuserid = unkonwuserid;
	}
	@Override
	public String toString() {
		return "Box [box_id=" + box_id + ", flag=" + flag + ", userid=" + userid + ", operateflag=" + operateflag
				+ ", unkonwuserid=" + unkonwuserid + "]";
	}
	
	
	

}
