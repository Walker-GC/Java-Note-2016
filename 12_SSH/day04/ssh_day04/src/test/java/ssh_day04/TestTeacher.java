package ssh_day04;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tedu.entity.Teacher;

public class TestTeacher {
	@Test
	public void test(){
		//读取数据库的连接参数
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		//获取Session对象
		SessionFactory factory=cfg.buildSessionFactory();
		Session session=factory.openSession();
		//创建对象,将对象保存至数据库的表中
		Teacher user=new Teacher("传奇哥");
		//利用session对象操作数据库
		Transaction t= session.beginTransaction();
		t.begin();//开启事务
		System.out.println(user);
		session.save(user);
		System.out.println(user);
		t.commit();
		session.close();
		
		
		
		
		
		
		
		
		
		
	}
}
