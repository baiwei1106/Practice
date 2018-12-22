package xxxy.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemoryDao extends BaseDao {
	//²åÈëÐÅÏ¢
		public boolean insertmemory(String id,String userid,String boxid,String flag){
			PreparedStatement ps=null;
			Connection conn=null;
			try {
				conn=getConnection();
				String sql="insert into memory values(?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, userid);
				ps.setString(3, boxid);
				ps.setString(4, flag);
				ps.setString(5, new java.sql.Timestamp(System.currentTimeMillis()).toString());
				int res=ps.executeUpdate();
				if(res>0){
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				notselectclose(conn, ps);
			}
			return false;
		}

}
