package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tarena.netctoss.dao.AdminDAO;
import com.tarena.netctoss.entity.Admin;

public class TestCase {
	@Test
	//测试DataSource
	public void test1() throws SQLException{
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		DataSource ds = 
				ac.getBean("ds",
						DataSource.class);
		System.out.println(
				ds.getConnection());
	}
	
	@Test
	//测试DAO
	public void test2(){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(
						"applicationContext.xml");
		AdminDAO dao = 
				ac.getBean("adminDAO",
						AdminDAO.class);
		Admin admin = 
				dao.findByAdminCode("caocao");
		System.out.println(admin);
	}
}



