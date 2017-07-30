package ssh_day04;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tedu.entity.Student;

public class TestStudent {
	public static Session openSession(){
	//读取数据库连接配置
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory factory=cfg.buildSessionFactory();
	//获取session对象
	Session session=factory.openSession();
	return session;
	}
	//@Test
	public void test(){
		//读取数据库连接配置
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		//获取session对象
		Session session=factory.openSession();
		Student s =new Student("苍老师");
		Transaction t=session.beginTransaction();
		t.begin();
		System.out.println(s);
		/**
		 * 当自动调用save方法时,Hibernate会根据映射规则,
		 * 生成insert语句并执行.
		 */
		session.save(s);
		System.out.println(s);
		t.commit();//提交,事务结束
		session.close();	
	}
	//@Test
	public void testUpdate(){
		Session s=openSession();
		Transaction t=s.beginTransaction();
		t.begin();
		//通过get方法获取一个持久状态的对象
		Student stu=(Student)s.get(Student.class,3);
		System.out.println(stu);
		stu.setName("传奇哥");  //生成一个update语句
		t.commit();
		System.out.println(stu);
		s.close();
	}
	//@Test
	public void testDelete(){
		Session s=openSession();
		Transaction t=s.beginTransaction();
		t.begin();
		Student stu=(Student)s.get(Student.class,2);
		System.out.println(stu);
		s.delete(stu);
		t.commit();
		s.close();
	}
	//@Test
	public void testObject(){
		Session s=openSession();
		Transaction t = s.beginTransaction();
		t.begin();
		Student stu=(Student)s.get(Student.class,1);
		System.out.println(stu);
		/**
		 * 将一个对象踢出缓存,与数据失联
		 * 对象属性变化将不再影响数据库数据
		 */
		s.evict(stu);	
		stu.setName("Andy");
		System.out.println(stu);
		t.commit();
		s.close();
		}
	//@Test
	public void testObjectUpdate(){
		Session s=openSession();
		Transaction t=s.beginTransaction();
		t.begin();
		Student stu=(Student)s.get(Student.class,1);
		System.out.println(stu);
		s.evict(stu);
		stu.setName("Robin");
		System.out.println(stu);//与数据库状态对比
		s.update(stu);
		t.commit();
		s.close();
	}
	/**
	 * HQL查询案例
	 */
	//@Test	//From 类名
	public void testSelect(){
		//1-获取Session对象
		Session s=openSession();
		//2-编写HQL语句
		String hql="from Student";
		//3-创建查询对象
		Query query=s.createQuery(hql);
		//4-返回查询结果
		List<Student> list=query.list();
		for(Student stu:list){
			System.out.println(stu);
		}
		//5-关闭Session
		s.close();
	}
	//@Test	//WHERE案例
	public void testWhere(){
		Session s=openSession();
		String hql="from Student where name=:name";
		Query query=s.createQuery(hql);
		query.setString("name", "苍老师");
		List<Student> list=query.list();
		for(Student stu:list){
			System.out.println(stu);
		}
	}
	@Test	//order by案例
	public void testOrderBy(){
		Session s=openSession();
		String hql="from Student order by id desc";
		Query query=s.createQuery(hql);
		List<Student> list=query.list();
		for(Student stu:list){
			System.out.println(stu);
		}
	}
	
	
	
	
	
	
	
}
