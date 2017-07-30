package test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

public class TestCase {
	@Test
	public void testUser(){
		ClassPathXmlApplicationContext ctx=
				new ClassPathXmlApplicationContext(""
						+ "spring-mybatis.xml");
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user =new User();
		user.setId(1);
		user.setName("White");
		dao.save(user);
	}
}
