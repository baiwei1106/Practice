package xxxy.demo.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import android.content.Context;
import android.widget.Toast;
import xxxy.demo.model.Users;


public class UserDao extends BaseDao {
	//注册用户
	public boolean insertuser(String id,String username,String userpwd,String phone){
		PreparedStatement ps=null;
		Connection conn=null;
		try {
			conn=getConnection();
			String sql="insert into user values(?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, username);
			ps.setString(3, userpwd);
			ps.setString(4, phone);
			ps.setString(5, null);
			ps.setString(6, new java.sql.Timestamp(System.currentTimeMillis()).toString());
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
	
	public Users login(String phone,String userpwd, Context context){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet res=null;
		Users user=new Users();
		String sql="select * from user where phone=? and password=?";
		conn=getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, userpwd);
            res=ps.executeQuery();
            
            while(res.next()){
            	user.setId(res.getString(1));
            	user.setUsername(res.getString(2));
            	user.setUserpwd(res.getString(3));
            	user.setPhone(res.getString(4));
            	user.setBox_id(null);
            	user.setCrdate(new java.sql.Timestamp(System.currentTimeMillis()).toString());
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			selectclose(conn,ps,res);
		}
		return user;
	}
	
	//查重
	public boolean isselectbyphone(String phone){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet res=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("select * from user where phone=?");
			ps.setString(1,phone);
		    res=ps.executeQuery();
			if(res.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			selectclose(conn,ps,res);
		}
		return false;
	}
	
	//根据手机号查询用户姓名
	public Users getusername(String phone){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet res=null;
		Users user=new Users();
		String sql="select * from user where phone=?";
		conn=getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, phone);
            res=ps.executeQuery();
            
            while(res.next()){
            	user.setId(res.getString(1));
            	user.setUsername(res.getString(2));
            	user.setUserpwd(res.getString(3));
            	user.setPhone(res.getString(4));
            	user.setBox_id(res.getString(5));
            	user.setCrdate(new java.sql.Timestamp(System.currentTimeMillis()).toString());
            	
            	
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			selectclose(conn,ps,res);
		}
		return user;
	}
	//判断用户是否存包
	public Users iscun(String id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet res=null;
		Users user=new Users();
		try {
			conn=getConnection();
			ps=conn.prepareStatement("select * from user where id=?");
			ps.setString(1,id);
		    res=ps.executeQuery();
			if(res.next())
			user.setId(res.getString(1));
        	user.setUsername(res.getString(2));
        	user.setUserpwd(res.getString(3));
        	user.setPhone(res.getString(4));
        	user.setBox_id(res.getString(5));
        	user.setCrdate(new java.sql.Timestamp(System.currentTimeMillis()).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			selectclose(conn,ps,res);
		}
		return user;
	}
	
	
	//改变user的存柜
		public boolean updatebox(String id,String box_id){
			PreparedStatement ps=null;
			Connection conn=null;
			try {
				conn=getConnection();
				ps=conn.prepareStatement("update user set box_id=? where id=?");
				ps.setString(1,box_id);
				ps.setString(2,id);
				int res=ps.executeUpdate();
				if(res>0)
					return true;
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
