package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC:java 数据库连接
 * JDBC是一套标准的接口，规定了连接
 * 数据库的步骤与操作数据库的功能。
 * 不同的数据库提供商都提供了一套JDBC的
 * 实现类以操作该数据库，这一套实现类称为
 * 该数据库的驱动包。
 * JDBC中的主要接口:
 * DriverManager:负责加载驱动，建立与数据库的连接
 * Connection:表示与数据库的一个连接，负责创建
 *            Statement
 * Statement:负责向数据库执行SQL语句
 * ResultSet:表示数据库的一个查询结果集           
 * @author adminitartor
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		try {
			/*
			 *	1:加载驱动，不同的数据库字符串的内容不一样。 
			 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			/*
			 *  2:使用DriverManager通过加载的驱动与数据库
			 *    建立连接
			 *    DriverManager的静态方法getConnection用于
			 *    与数据库建立连接，需要传入三个参数
			 *    参数1:数据库的地址(不同数据库格式不一样)
			 *    参数2:数据库的用户名
			 *    参数3:数据库的密码
			 */ 
			Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.201.217:1521:orcl",
				"openlab",
				"open123"
			);
			System.out.println("已连接数据库!");
			
			/*
			 * 3:创建Statement以发送SQL语句
			 */
			Statement state = conn.createStatement();
			/*
			 * 针对不同的SQL语句，Statement也提供了
			 * 相应的执行方法
			 * 1:int executeUpdate(String sql)
			 *   专门用来执行DML语句的方法，返回值
			 *   为一个数字，表示执行DML操作后影响
			 *   了表中的记录数
			 * 
			 * 2:ResultSet executeQuery(String sql)
			 *   专门用来执行DQL语句的方法，返回值为
			 *   一个ResultSet实例，表示DQL执行后的
			 *   查询结果集
			 * 
			 * 3:boolean execute(String sql)
			 *   可以执行所有类型的SQL语句，由于DML,
			 *   DQL有专门的方法，所以通常使用execute
			 *   方法来执行DDL（数据定义语言）语句。返回值为boolean值，
			 *   当该值为true时说明执行SQL后有查询结果集         
			 */
			/*
			 * 创建一张表:userinfo
			 * 字段:
			 * id NUMBER(6)
			 * username VARCHAR2(50)
			 * password VARCHAR2(50)
			 * email VARCHAR2(100)
			 * nickname VARCHAR2(50)
			 * account NUMBER(10,2)
			 */
			String sql = "CREATE TABLE userinfo( "
					   + "	id NUMBER(6), "
					   + "  username VARCHAR2(50), "
					   + "  password VARCHAR2(50), "
					   + "  email VARCHAR2(100), "
					   + "  nickname VARCHAR2(50), "
					   + "  account NUMBER(10,2) "
					   + ")";
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








