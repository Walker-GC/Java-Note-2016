package com.tarena.netctoss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.tarena.netctoss.entity.Admin;

@Repository("adminDAO")  
//持久层
public class AdminDAOJdbcImpl implements 
AdminDAO{
	@Resource(name="ds")
	private DataSource ds;
	
	public Admin findByAdminCode(
			String adminCode) {
		Admin admin  = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql =
					"select * from admin_info "
					+ "where admin_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminCode);
			rs = ps.executeQuery();
			if(rs.next()){
				admin = new Admin();
				admin.setAdminId(
						rs.getInt("admin_id"));
				admin.setAdminCode(
						rs.getString("admin_code"));
				admin.setPassword(
						rs.getString("password"));
				admin.setName(
						rs.getString("name"));
				admin.setTelephone(
						rs.getString("telephone"));
				admin.setEmail(
						rs.getString("email"));
				admin.setEnrolldate(
						rs.getTimestamp("enrolldate"));
			}
		} catch (SQLException e) {
			//记日志 
			e.printStackTrace();
			/*
			 * 看异常是否能够恢复，如果能够
			 * 恢复，则立即恢复，如果不能够
			 * 恢复(比如，发生了系统异常：数据
			 * 库服务暂停，网络中断等),则提示
			 * 用户稍后重试。
			 */
			throw new RuntimeException(e);
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return admin;
	}
	
	
}



