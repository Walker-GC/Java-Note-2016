package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 输入每页显示的条数以及显示第几页
 * 然后输出emp表中对应的记录
 * 字段查询:empno,ename,sal,job,deptno
 * 要求是按照工资降序排列后的分页
 * @author adminitartor
 *
 */
public class JDBCDemo5 {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入每页显示的条数:");
			int pageSize = Integer.parseInt(scanner.nextLine());
			System.out.println("请输入要显示的页数:");
			int page = Integer.parseInt(scanner.nextLine());
			int start = (page-1)*pageSize+1;
			int end = pageSize*page;
			
			
			
			
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.201.217:1521:orcl",
				"openlab",
				"open123"
			);
			System.out.println("已连接数据库!");
			Statement state = conn.createStatement();
			/*
			 * SELECT *
			 * FROM(SELECT ROWNUM rn,t.*
			 *      FROM(SELECT empno,ename,sal,job,deptno
			 *           FROM emp
			 *           ORDER BY sal DESC) t
			 *      WHERE ROWNUM<=x)
			 * WHERE rn>=x
			 */
			
			String sql = "SELECT * "
					   + "FROM(SELECT ROWNUM rn,t.* "
					   + "     FROM(SELECT empno,ename,sal,job,deptno "
					   + "          FROM emp "
					   + "          ORDER BY sal DESC) t "
					   + "     WHERE ROWNUM<="+end+") "
					   + "WHERE rn>="+start;
			ResultSet rs = state.executeQuery(sql);
			
			while(rs.next()){
				//获取empno
				int empno = rs.getInt("empno");
				//获取ename
				String ename = rs.getString("ename");
				int sal = rs.getInt("sal");
				String job = rs.getString("job");
				int deptno = rs.getInt("deptno");
				System.out.println(empno+","+ename+","+sal+","+job+","+deptno);
			}
			
			
			//没有其他数据库操作则关闭数据库连接
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
