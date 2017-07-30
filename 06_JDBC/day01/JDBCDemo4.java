package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 执行DQL语句
 * @author adminitartor
 *
 */
public class JDBCDemo4 {
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
				String sql = "SELECT id,username,password,email,nickname,account "
						   + "FROM userinfo ";
				ResultSet rs = state.executeQuery(sql);
				/*
				 * java.sql.ResultSet
				 * 表示一个数据库的查询结果集
				 * ResultSet提供了遍历结果集的相关方法
				 * boolean next()
				 * 判断结果集是否还有下一条记录，若有则返回true
				 * 并使当前ResultSet的指针指向并表示该条记录。
				 * 若没有则返回false
				 * 
				 * 提供了若干的getXXX(String colName)方法
				 * 根据字段名获取该字段对应的值，不同字段类型
				 * 使用不同方法。
				 */
				while(rs.next()){
					//获取id
					int id = rs.getInt("id");
					//获取username
					String username = rs.getString("username");
					String password = rs.getString("password");
					String email = rs.getString("email");
					String nickName = rs.getString("nickname");
					double account = rs.getDouble("account");
					System.out.println(id+","+username+","+password+","+email+","+nickName+","+account);
				}
				
				
				//没有其他数据库操作则关闭数据库连接
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
