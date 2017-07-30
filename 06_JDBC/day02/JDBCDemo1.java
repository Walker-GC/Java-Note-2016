package day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Statement只适合执行静态SQL语句，因为执行动态
 * SQL语句有两个缺点:
 * 1:由于含有动态信息，那么就需要先拼接SQL，这就
 *   可能出现SQL注入攻击的问题。
 * 2:大部分情况下，拼接SQL时，语义已经定好，拼接
 *   的内容无非就是一些数据，那么当大批量执行这样
 *   含有动态值的SQL时，数据库每当接受到Statement
 *   发送的SQL语句时，只要语句中的内容有区别，就会
 *   当做一条全新的SQL语句去执行。数据库执行SQL时
 *   会首先解析SQL语句并生成一个执行计划(开销大)，
 *   那么批量执行这样内容有些微变化的SQL时会为每一
 *   个SQL生成一个执行计划，对数据库是负担。  
 *   
 *   java.sql.PreparedStatement
 *   该接口是Statement的子接口。设计目的是为了
 *   执行动态SQL语句。这样的SQL称为预编译SQL，
 *   这种SQL语句会将动态信息以"?"代替，先进行占位。
 *   然后将该SQL发送给数据库生成执行计划。然后当
 *   需要执行该SQL时，只需要将?需要的实际数据再次
 *   传递给数据库即可。
 *   1:由于先将SQL语句发送给数据库，并生成了执行
 *     计划(语义已经确定)，就不存在拼接SQL导致
 *     改变SQL语义(SQL注入攻击)的问题了。
 *   2:由于执行计划已经生成，当大批量执行SQL时，
 *     每次只需要将?表示的实际值传入，那么数据库
 *     会重用执行计划，这就减少了服务器的压力。  
 *   
 *   
 *   
 * @author adminitartor
 *
 */
public class JDBCDemo1 {
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
			/*
			 * 预编译SQL语句，将动态部分已?代替
			 * 需要注意，?只能代替值，不能代替
			 * 会改变SQL语句语义的部分
			 */
			String sql = "SELECT id,username,password,email,nickname,account "
					   + "FROM userinfo "
					   + "WHERE username=? AND password=?";
			/*
			 * 创建PreparedStatement时就需要将预编译SQL
			 * 语句传入，实际上在创建PS时会将该SQL发送给
			 * 数据库以生成执行计划。
			 */
			PreparedStatement ps 
				= conn.prepareStatement(sql);
			
			/*
			 * 在执行SQL的工作前，还需要将?部分的
			 * 实际数据传递给数据库
			 */
			ps.setString(1,"fanchuanqi");
			ps.setString(2, "asd' OR '1'='1");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("登陆成功");
			}else{
				System.out.println("登陆失败");
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







