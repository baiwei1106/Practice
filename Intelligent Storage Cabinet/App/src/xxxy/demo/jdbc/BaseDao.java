package xxxy.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import android.app.Activity;


public class BaseDao extends Activity {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://192.144.153.235:3306/lockers?useUnicode=true&characterEncoding=utf-8";
	private static String root="root";
	private static String pwd="bw661102";
	public  static   Connection getConnection(){
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url, root, pwd);
			return con;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void selectclose(Connection conn,PreparedStatement pst,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	}
		public void notselectclose(Connection conn,PreparedStatement pst){
			if(pst!=null){
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
	

}
