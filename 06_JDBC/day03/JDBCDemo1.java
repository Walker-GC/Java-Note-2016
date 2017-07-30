package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;

import day02.DBUtil;

/**
 * 批量执行
 * 
 * 当有大批量数据需要插入到数据库某张表时，
 * 影响插入性能的主要因素:
 * 1:事务，一条DML执行一次事务效率是很差的
 *   可以考虑多条记录使用同一个事务
 * 2:PreparedStatement的使用可以减少执行计划的
 *   生成
 * 3:减少网络调用，客户端一次发送一条SQL语句与
 *   一次发送多条SQL语句到数据库服务端的效率也是
 *   不一样的。
 * 批量执行就是减少了网络调用。大批量SQL执行时，
 * 应当考虑使用批量执行操作。      
 * 
 * @author adminitartor
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			/*
			 * 在一个事务中插入1000条可以减少数据库
			 * 对表的实际写操作次数，提高效率
			 */
			conn.setAutoCommit(false);
			String sql 
			= "INSERT INTO userinfo "
			+ "(id,username,password,email,nickname,account) "
			+ "VALUES "
			+ "(?,?,'123456',?,?,5000)";
			/*
			 * 使用PS可以使这1000条SQL使用同一个执行计划
			 * 从而提高SQL执行效率
			 */
			PreparedStatement ps
				= conn.prepareStatement(sql);
			long start = System.currentTimeMillis();
			for(int i=20000;i<30000;i++){
				ps.setInt(1, i);
				ps.setString(2, "test"+i);
				ps.setString(3, "test"+i+"@qq.com");
				ps.setString(4, "nick"+i);
				/*
				 * 执行executeUpdate方法，会将?对应的
				 * 一组数据发送给数据库服务端
				 * 那么调用10000次该方法就会发送10000次
				 * 提高了网络调用次数会降低网络传输效率。
				 */
//				ps.executeUpdate();
				//添加到本地缓存中(添加到批中)
				ps.addBatch();
			}
			//执行批操作(将缓存的内容一次性发给数据库执行)
			int[] d = ps.executeBatch();
			conn.commit();
			long end = System.currentTimeMillis();
			System.out.println("插入完毕，耗时:"+(end-start)+"ms");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		
		
	}
}







