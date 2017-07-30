package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.EmpDAO;
import entity.Emp;

public class TestCase {
	private EmpDAO dao;
	@Before
	public void init(){
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
			"applicationContext.xml");
		dao = 
			ac.getBean("employeeDAO",EmpDAO.class);
	}
	@Test
	public void test1(){
		Emp emp = new Emp();
		emp.setEname("Giving");
		emp.setAge(22);
		dao.save(emp);
	}
	@Test
	public void test2(){
		List<Emp> emps = 
				dao.findAll();
		System.out.println(emps);
	}
	
	
}
