package cn.tedu.note.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;

public class TestCase {
	
	ApplicationContext ctx;
	@Before
	public void init(){
		ctx = 
		new ClassPathXmlApplicationContext(
			"spring-mybatis.xml",
			"spring-service.xml");
	}
	@Test //测试MyBatis配置
	public void testMapperScanner(){
		Object obj = 
			ctx.getBean("mapperScanner");
		System.out.println(obj); 
	}
	
	@Test
	public void testSaveUser(){
		//在Spring容器中获取 MapperScanner
		//创建的 UserDao 对象
		UserDao dao = ctx.getBean(
			"userDao", UserDao.class);
		User user = new User(
			"1", "Tom", "123", "","Tomcat");
		dao.saveUser(user);
		System.out.println(user); 
	}
	
	@Test
	public void testFindUserByName(){
		//在Spring容器中获取 MapperScanner
		//创建的 UserDao 对象
		UserDao dao = ctx.getBean(
			"userDao", UserDao.class);
		User user=dao.findUserByName("Tom");
		System.out.println("user:"+user); 
	}
	
	@Test
	public void testLogin(){
		UserService service =
			ctx.getBean("userService",
			UserService.class);
		User user=service.login("Tom","123");
		System.out.println(user);
		user = service.login("Tom","1231");
	}
}















