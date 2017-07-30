package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tedu.entity.Teacher;

public class TestTeacher {
	
	@Test
	public void testTeacher(){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Teacher t = new Teacher();
		t.setName("苍老师");
		Transaction ts = session.beginTransaction();
		ts.begin();
		System.out.println(t);
		session.save(t);
		System.out.println(t);
		ts.commit();
		session.close();
		
	}
	
	

}
