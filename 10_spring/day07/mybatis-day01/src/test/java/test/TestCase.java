package test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import entity.Emp;
import entity.Emp2;

public class TestCase {
	private SqlSession session;
	@Before
	public void init(){
	//类加载器 ClassLoader
		SqlSessionFactoryBuilder ssfb = 
		new SqlSessionFactoryBuilder();
		/*
		 * getResourceAsStream是ClassLoader
		 * 提供的一个方法，负责读取配置文件的
		 * 内容，并提供一个输入流(inputStream)。
		 * 注：CLassLoader(类加载器):负责读取
		 * 类的字节码文件的内容，并将这些内容转换
		 * 成方法区中的class对象。
		 */
		SqlSessionFactory ssf = 
		ssfb.build(TestCase.class
				.getClassLoader()
				.getResourceAsStream(
				"SqlMapConfig.xml"));
		 session = ssf.openSession();
	}
	
	@Test
	public void test1(){
		Emp emp = new Emp();
		emp.setEname("Tom2");
		emp.setAge(22);
		session.insert("test.save", emp);
		//提交事务
		session.commit();
		session.close();
	}
	
	@Test
	public void test2(){
		List<Emp> emps = 
			session.selectList(
					"test.findAll");
		System.out.println(emps);
		session.close();
	}
	
	@Test
	public void test3(){
		Emp emp = 
				session
				.selectOne("test.findById",21);
		System.out.println(emp);
		session.close();
	}
	
	@Test
	public void test4(){
		Emp emp = 
				session.selectOne(
						"test.findById",21);
		emp.setAge(emp.getAge() + 10);
		session.update("test.modify", emp);
		session.commit();
		session.close();
	}
	
	@Test
	public void test5(){
		session.delete("test.delete", 21);
		session.commit();
		session.close();
	}
	
	@Test
	public void test6(){
		Map data = 
				session.selectOne(
						"test.findById2",
						23);
		//注意字段名要大写,
		//oracle会将字段名都转换成大写形式。
		System.out.println(
				data.get("ENAME"));
		session.close();
	}
	
	@Test
	public void test7(){
		Emp2 emp = 
				session.selectOne(
					"test.findById3", 23);
		System.out.println(emp);
	}
}




