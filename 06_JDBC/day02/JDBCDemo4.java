package day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 结果集的元数据
 * @author adminitartor
 *
 */
public class JDBCDemo4 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			
			Statement state = conn.createStatement();
			String sql = "SELECT * FROM emp";
			
			ResultSet rs = state.executeQuery(sql);
			/*
			 * 获取结果结元数据		
			 */
			ResultSetMetaData rsmd = rs.getMetaData();
			/*
			 * 查看结果集的字段数量
			 */
			int count = rsmd.getColumnCount();
			
			for(int i=1;i<=count;i++){
				/*
				 * 查看指定字段的字段名
				 */
				String colName = rsmd.getColumnName(i);
				System.out.println(colName);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
	}
}
