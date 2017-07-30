package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import autowire.Restaurant;
import ioc.A;

public class TestIoc {
	private ApplicationContext ac;
	@Before
	// @Before修饰的方法会
	//在测试方法运行之前先执行。
	public void init(){
		//启动spring容器
		ac = 
		new ClassPathXmlApplicationContext(
		"autowire.xml");
	}
	
	@Test
	//测试方法
	public void test1(){
		ac = new ClassPathXmlApplicationContext(
				"ioc.xml");
		//通过容器得到一个对象
		A a = ac.getBean("a1",A.class);
		a.execute();
	}
	
	@Test
	public void test2(){
		Restaurant rest = 
				ac.getBean("rest",
						Restaurant.class);
		System.out.println(rest);
	}
}




