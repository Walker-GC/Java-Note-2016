package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ioc.A;

public class TestIoc {
	@Test
	public void test1(){
		//启动spring容器
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				"config/ioc.xml");
		//通过容器获得一个对象
		A a = ac.getBean("a1",A.class);
		a.execute();
	}
	
	
	
}
