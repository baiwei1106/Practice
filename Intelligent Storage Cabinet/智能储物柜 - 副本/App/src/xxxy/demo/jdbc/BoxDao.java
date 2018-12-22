package xxxy.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import xxxy.demo.model.Box;
import xxxy.demo.model.Users;

public class BoxDao extends BaseDao {
	
	
	/*public boolean selectboxbyid(String id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet res=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("select box_id from box where userid=?");
			ps.setString(1,id);
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
	}*/
	
	//查询用户存的那个箱子
	public Box selectboxbyid(String id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet res=null;
		Box box=new Box();
		String sql="select * from box where userid=?";
		conn=getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
            res=ps.executeQuery();
            
            while(res.next()){
            	box.setBox_id(res.getString(1));
            	box.setFlag(res.getString(2));
            	box.setUserid(res.getString(3));
            	box.setOperateflag(res.getString(4));
            	box.setUnkonwuserid(res.getString(5));
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			selectclose(conn,ps,res);
		}
		return box;
	}
	
	//改变box的flag值
	public boolean updateflag(String boxid,String flag){
		PreparedStatement ps=null;
		Connection conn=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement("update box set flag=?,operateflag=0 where box_id=?");
			ps.setString(1,flag);
			ps.setString(2,boxid);
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
	
	//改变box的userid值
		public boolean updateuserid(String boxid,String userid){
			PreparedStatement ps=null;
			Connection conn=null;
			try {
				conn=getConnection();
				ps=conn.prepareStatement("update box set userid=? where box_id=?");
				ps.setString(1,userid);
				ps.setString(2,boxid);
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
		
		
		//改变box的用户操作记录
				public boolean updateoperateflag(String boxid,String operateflag){
					PreparedStatement ps=null;
					Connection conn=null;
					try {
						conn=getConnection();
						ps=conn.prepareStatement("update box set operateflag=? where box_id=?");
						ps.setString(1,operateflag);
						ps.setString(2,boxid);
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
		
		//查询所有箱子的状态
		public List<Box> selectAll(){
			List<Box> list=new ArrayList<Box>();
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet res=null;
			try {
				conn=getConnection();
				String sql="select *from box";
				ps=conn.prepareStatement(sql);
				res=ps.executeQuery();
				while(res.next()){
					Box box=new Box();
					box.setBox_id(res.getString(1));
	            	box.setFlag(res.getString(2));
	            	box.setUserid(res.getString(3));
	            	box.setOperateflag(res.getString(4));
	            	box.setUnkonwuserid(res.getString(5));
					
					list.add(box);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				selectclose(conn,ps,res);
			}
			return list;
		}
		
		//查询某一个箱子的状态
		public Box selectbox(String box_id){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet res=null;
			Box box=new Box();
			String sql="select * from box where box_id=?";
			conn=getConnection();
			try {
				ps=conn.prepareStatement(sql);
				ps.setString(1, box_id);
	            res=ps.executeQuery();
	            
	            while(res.next()){
	            	box.setBox_id(res.getString(1));
	            	box.setFlag(res.getString(2));
	            	box.setUserid(res.getString(3));
	            	box.setOperateflag(res.getString(4));
	            	box.setUnkonwuserid(res.getString(5));
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				selectclose(conn,ps,res);
			}
			return box;
		}

}
