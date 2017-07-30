package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import annotation.ExampleBean;

public class TestAnnotation {
	@Test
	public void test1(){
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				"annotation.xml");
		ExampleBean eb1 = 
				ac.getBean("exampleBean",
						ExampleBean.class);
		System.out.println(eb1);
	}
}
