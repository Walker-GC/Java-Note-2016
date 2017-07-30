package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import day02.DBUtil;

/**
 * 自动返回主键
 * 允许我们在插入一条数据的同时将该条数据中指定的
 * 字段返回。通常返回的都是系统生成的值，例如主键
 * 值。
 * @author adminitartor
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql 
				= "INSERT INTO dept "
				+ "(deptno,dname,loc) "
				+ "VALUES "
				+ "(seq_dept_id.NEXTVAL,?,?)";
			/*
			 * 使用Connection带两个参数的方法
			 * 生成PS,其中第二个参数是一个字符串
			 * 数组，数组中每一个字符串内容应当是
			 * 需要知道插入后该条记录希望返回值的
			 * 字段名
			 */
			PreparedStatement ps
				= conn.prepareStatement(
					sql, new String[]{"deptno"}
				);
			
			ps.setString(1, "IT");
			ps.setString(2, "BEIJING");
			int i = ps.executeUpdate();
			if(i>0){
				System.out.println("插入成功!");
				/*
				 * 获取刚插入的这个部门记录中deptno字段的值
				 */
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				int deptno = rs.getInt(1);
				System.out.println("插入的部门id为:"+deptno);
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








