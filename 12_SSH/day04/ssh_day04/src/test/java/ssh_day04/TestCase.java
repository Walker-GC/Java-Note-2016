package ssh_day04;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tedu.entity.User;

public class TestCase {
	@Test
	public void test(){
		//读取数据库连接配置
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		//获取session对象
		Session session=factory.openSession();
		//session自动生成SQL查询DB
		String id="001";
		User user=(User)session.get(User.class,id);
		System.out.println(user);
		session.close();
	}
}
