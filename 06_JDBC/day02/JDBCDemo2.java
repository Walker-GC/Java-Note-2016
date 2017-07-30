package day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DML操作
 * @author adminitartor
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.201.217:1521:orcl",
				"fancq",
				"zaq12wsx"
			);
			
			//使用PreparedStatement
			String sql = "INSERT INTO userinfo "
					   + "(id,username,password,email,nickname,account) "
					   + "VALUES "
					   + "(seq_userinfo_id.NEXTVAL,?,?,?,?,?)";
			PreparedStatement ps 
				= conn.prepareStatement(sql);
			
			ps.setString(1, "liucangsong");
			ps.setString(2, "123456");
			ps.setString(3, "liu@qq.com");
			ps.setString(4, "苍苍");
			ps.setDouble(5, 5000.0);
			                                         
			int d = ps.executeUpdate();
			if(d>0){
				System.out.println("插入成功!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
