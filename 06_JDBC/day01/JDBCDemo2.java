package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 创建一个序列
 * seq_userinfo_id
 * 从1开始，步进为1
 * @author adminitartor
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.201.217:1521:orcl",
				"openlab",
				"open123"
			);
			System.out.println("已连接数据库!");
			Statement state = conn.createStatement();
			String sql = "CREATE SEQUENCE seq_userinfo_id "
					   + "START WITH 1 "
					   + "INCREMENT BY 1 ";
			System.out.println(sql);
			state.execute(sql);
			System.out.println("执行成功!");
			//没有其他数据库操作则关闭数据库连接
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
