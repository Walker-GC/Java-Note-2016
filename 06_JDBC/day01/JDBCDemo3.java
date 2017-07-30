package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 执行DML语句
 * @author adminitartor
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("欢迎注册!");
			System.out.println("请输入用户名:");
			String username = scanner.nextLine();
			System.out.println("请输入密码:");
			String password = scanner.nextLine();
			System.out.println("请输入邮箱地址:");
			String email = scanner.nextLine();
			System.out.println("请输入昵称:");
			String nickName = scanner.nextLine();
			//下面可以做一些正确性的验证工作
			
			
			
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.201.217:1521:orcl",
				"openlab",
				"open123"
			);
			System.out.println("已连接数据库!");
			Statement state = conn.createStatement();
			String sql = "INSERT INTO userinfo "
					   + "(id,username,password,email,nickname,account) "
					   + "VALUES "
					   + "(seq_userinfo_id.NEXTVAL,'"+username+"','"+password+"','"+email+"','"+nickName+"',5000)";
			System.out.println(sql);
			int i = state.executeUpdate(sql);
			if(i>0){
				System.out.println("执行成功!");
			}
			//没有其他数据库操作则关闭数据库连接
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}





