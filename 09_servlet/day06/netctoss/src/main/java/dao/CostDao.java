package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Cost;
import util.DBUtil;

public class CostDao implements Serializable {

	public List<Cost> findAll() {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = 
				"select * from cost_lhh "
				+ "order by cost_id";
			Statement smt = 
				conn.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			List<Cost> list = new ArrayList<Cost>();
			while(rs.next()) {
				Cost c = new Cost();
				c.setCostId(rs.getInt("cost_id"));
				c.setName(rs.getString("name"));
				c.setBaseDuration(rs.getInt("base_duration"));
				c.setBaseCost(rs.getDouble("base_cost"));
				c.setUnitCost(rs.getDouble("unit_cost"));
				c.setStatus(rs.getString("status"));
				c.setDescr(rs.getString("descr"));
				c.setCreatime(rs.getTimestamp("creatime"));
				c.setStartime(rs.getTimestamp("startime"));
				c.setCostType(rs.getString("cost_type"));
				list.add(c);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
				"查询资费失败", e);
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public void save(Cost c) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = 
				"insert into cost_lhh "
				+ "values(cost_seq_lhh.nextval,"
				+ "?,?,?,?,'1',?,sysdate,null,?)";
			PreparedStatement ps = 
				conn.prepareStatement(sql);
			ps.setString(1, c.getName());
			//setInt()和setDouble()不允许传入null，
			//但实际业务中该字段是可能为空的，并且
			//数据库中该字段也允许为null。要想解决
			//这个问题，需将此字段当做Object处理。
			ps.setObject(2, c.getBaseDuration());
			ps.setObject(3, c.getBaseCost());
			ps.setObject(4,c.getUnitCost());
			ps.setString(5, c.getDescr());
			ps.setString(6, c.getCostType());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
				"增加资费失败", e);
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	public static void main(String[] args) {
		CostDao dao = new CostDao();
		Cost c = new Cost();
		c.setName("包月");
		//c.setBaseDuration(660);
		c.setBaseCost(1000.0);
		//c.setUnitCost(0.6);
		c.setDescr("包月真爽");
		c.setCostType("1");
		dao.save(c);
	}
	
}










