package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import value.ExampleBean;
import value.SpelBean;

public class TestValue {
	private ApplicationContext ac;
	@Before
	public void init(){
		ac = 
		new ClassPathXmlApplicationContext(
				"value.xml");
	}
	@Test
	public void test1(){
		ExampleBean eb1 = 
		ac.getBean("eb2",ExampleBean.class);
		System.out.println(eb1);
	}
	
	@Test
	public void test2(){
		System.out.println(
				ac.getBean("config"));
	}
	
	@Test
	//测试 spring表达式
	public void test3(){
		SpelBean sb1 = 
				ac.getBean("sb1",
						SpelBean.class);
		System.out.println(sb1);
	}
}




