package dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import annotations.MyRepository;
import entity.Emp;
import entity.Emp2;

/**
 * Mapper映射器
 *
 */
@Repository("employeeDAO")
@MyRepository
//添加了该注解(@MyRepository)的映射器，才会被
//MapperScannerConfigurer扫描。
public interface EmpDAO {
	public void save(Emp emp);
	public List<Emp> findAll();
	public Emp findById(int id);
	public void modify(Emp emp);
	public void delete(int id);
	public Map findById2(int id);
	public Emp2 findById3(int id);
	
}




