package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tedu.entity.User;

public class TestCase {
	
	@Test
	public void test(){
		//读取数据库连接参数
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		//获取session对象
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		//操作数据库
		int id=1;
		User user = (User)session.get(User.class, id);
		System.out.println(user);
		session.close();
	}

}