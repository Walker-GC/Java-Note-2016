package dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import entity.Emp;
import entity.Emp2;
@Repository("employeeDAO")
public class EmpDAOMybatisImpl implements 
EmpDAO{
	@Resource(name="sst")
	private SqlSessionTemplate sst;
	
	public void save(Emp emp) {
		sst.insert("save", emp);
	}

	public List<Emp> findAll() {
		return sst.selectList("findAll");
	}

	public Emp findById(int id) {
		return sst.selectOne("findById", 
				id);
	}

	public void modify(Emp emp) {
		sst.update("modify", emp);
	}

	public void delete(int id) {
		sst.delete("delete", id);
	}

	public Map findById2(int id) {
		return sst.selectOne("findById2",
				id);
	}

	public Emp2 findById3(int id) {
		return sst.selectOne("findById3",
				id);
	}

}
