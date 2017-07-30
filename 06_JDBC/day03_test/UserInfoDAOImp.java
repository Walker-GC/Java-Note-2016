package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import day01.UserInfo;
import day02.DBUtil;

/**
 * UserInfoDAO的实现类
 * @author adminitartor
 *
 */
public class UserInfoDAOImp implements UserInfoDAO{

	public boolean save(UserInfo userinfo) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql 
				= "INSERT INTO userinfo "
				+ "(id,username,password,email,nickname,account) "
				+ "VALUES "
				+ "(seq_userinfo_id.NEXTVAL,?,?,?,?,?)";
			ps = conn.prepareStatement(sql,new String[]{"id"});
			ps.setString(1, userinfo.getUsername());
			ps.setString(2, userinfo.getPassword());
			ps.setString(3, userinfo.getEmail());
			ps.setString(4, userinfo.getNickname());
			ps.setDouble(5, userinfo.getAccount());
			int i = ps.executeUpdate();
			if(i>0){
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				int id = rs.getInt(1);
				userinfo.setId(id);
				//插入成功，返回true告知业务层
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		return false;
	}

	public UserInfo findByName(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT id,username,password,email,nickname,account "
					   + "FROM userinfo "
					   + "WHERE UPPER(username)=UPPER(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String user = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String nickname = rs.getString("nickname");
				double account = rs.getDouble("account");
				UserInfo userinfo = new UserInfo(id,user,password,email,nickname,account);
				return userinfo;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		return null;
	}

	public List<UserInfo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserInfo> findAllByPage(int pageSize, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(UserInfo userinfo) {
		/*
		 * UPDATE userinfo
		 * SET username=?,password=?,xxxx
		 * WHERE id=?
		 */
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateForTreansfor(UserInfo u1, UserInfo u2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteById(int id) {
		/*
		 * DELETE FROM userinfo
		 * WHERE id=?
		 */
		// TODO Auto-generated method stub
		return false;
	}

}
