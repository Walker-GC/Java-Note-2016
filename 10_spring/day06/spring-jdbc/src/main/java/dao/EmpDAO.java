package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import entity.Emp;

//Repository：持久层
@Repository("empDAO")
public class EmpDAO {
	@Resource(name="jt")
	private JdbcTemplate jt;
	
	/**
	 * spring jdbc 将底层的jdbc异常
	 * 捕获之后，统一转换成了一些运行时
	 * 异常(RuntimeException)，然后抛出。
	 */
	public void save(Emp emp){
		String sql = "INSERT INTO emp "
				+ "VALUES(emp_seq.nextval,?,?)";
		Object[] args = 
				new Object[]{emp.getEname(),
						emp.getAge()};
		jt.update(sql, args);
	}
	
	public List<Emp> findAll(){
		String sql = "SELECT * FROM emp";
		return jt.query(sql, new EmpRowMapper());
	}
	
	/*
	 * queryForObject方法：如果找不到
	 * 对应的记录，会抛出异常。
	 */
	public Emp findById(int id){
		String sql = "SELECT * FROM emp "
				+ "WHERE id = ?";
		Object[] args = 
				new Object[]{id};
		return jt.queryForObject(
				sql,args, new EmpRowMapper());
	}
	
	public Emp findById2(int id){
		String sql = "SELECT * FROM emp "
				+ "WHERE id = ?";
		Object[] args = 
				new Object[]{id};
		List<Emp> emps = 
				jt.query(sql, args,
						new EmpRowMapper());
		if(emps != null && emps.size() > 0){
			return emps.get(0);
		}
		return null;
	}
	
	public void modify(Emp emp){
		String sql = "UPDATE emp "
				+ "SET ename=?,age=? "
				+ "WHERE id=?";
		Object[] args = 
				new Object[]{emp.getEname(),
						emp.getAge(),
						emp.getId()};
		jt.update(sql, args);
	}
	
	public void delete(int id){
		String sql = "DELETE FROM emp "
				+ "WHERE id=?";
		Object[] args = 
				new Object[]{id};
		jt.update(sql, args);
	}
	
	
	/*
	 * RowMapper类封装了如何将记录
	 * 转换成相应的实体对象。
	 */
	class EmpRowMapper 
		implements RowMapper<Emp>{
		//index:正在被遍历的记录的下标。
		public Emp mapRow(
				ResultSet rs, int index) 
						throws SQLException {
			Emp emp = new Emp();
			emp.setId(rs.getInt("id"));
			emp.setEname(rs.getString("ename"));
			emp.setAge(rs.getInt("age"));
			return emp;
		}
		
	}
}




