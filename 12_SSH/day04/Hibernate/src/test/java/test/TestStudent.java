package test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tedu.entity.Student;

public class TestStudent {
	
	public Session opensession() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		//获取session对象
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}

	@Test
	public void testStudent(){
		Session session = opensession();
		Student s = new Student();
		s.setName("John");
		/*
		 * hibernate save()方法会自动根据映射文件中的规则，
		 * 生成sql并且执行save会自动生成id
		 */
		Transaction t = session.beginTransaction();
		t.begin();
		System.out.println(s);
		session.save(s);
		System.out.println(s);
		t.commit();
		session.close();
	}
	
	@Test
	public void testupdate(){
		//读取数据库连接信息
				Session session = opensession();
				
				Transaction t = session.beginTransaction();
				t.begin();
				Student s = (Student)session.get(Student.class, 3);
				System.out.println(s);
				s.setName("M1");
				s.setName("M2");
				s.setName("M3");
				System.out.println(s);
				t.commit();
				System.out.println(s);
				session.close();
	}
    @Test
	public void testDelete(){
		Session session = opensession();
		Transaction t = session.beginTransaction();
		t.begin();
		Student stu = (Student)session.get(Student.class, 3);
		session.delete(stu);
		t.commit();
		session.close();
		
	}
    @Test
    public void test(){
    	Session session = opensession();
    	Transaction t = session.beginTransaction();
    	t.begin();
    	//通过get方法获取对象为持久状态
    	Student stu = (Student)session.get(Student.class, 2);
    	//从session缓存中将对象剔除,变成游离状态，再对此对象数据作改变时，数据库中的数据是不变的
    	System.out.println(stu);
    	//session.clear();会将全部对象从缓存中清除
    	session.evict(stu);
    	stu.setName("Andy");
    	System.out.println(stu);
    	t.commit();
    	session.close();
    }
    @Test
    //测试对象变成游离状态之后，再转为持久状态
    public void test1(){
    	Session session = opensession();
    	Transaction t = session.beginTransaction();
    	t.begin();
    	Student stu = (Student)session.get(Student.class, 2);
    	//从持久状态变成游离状态
    	session.evict(stu);
    	stu.setName("new");
    	System.out.println(stu);
    	//执行update（）方法会将修改过的数据更新到数据库
    	session.update(stu);
    	System.out.println(stu);
    	t.commit();
    	session.close();
    }
    @Test
    //测试HQL
    public void testHqlfrom(){
    	//1.获取session对象
    	Session session = opensession();
    	//2.编写HQL语句
    	String hql = "from Student";
    	//3.创建query对象
    	Query query = session.createQuery(hql);
    	//4.调用list方法返回结果
    	List<Student> list = query.list();
    	for(Student stu:list){
    		System.out.println(stu);
    	}
    	//5.关闭session
    	session.close();
    }
	//测试HQL Order by
    @Test
    public void testOrderby(){
    	Session session = opensession();
    	String hql = "from Student order by id desc";
    	Query query = session.createQuery(hql);
    	List<Student> list = query.list();
    	for(Student stu:list){
    		System.out.println(stu);
    	}
    	session.close();
    }
    
    @Test
    //测试param
    public void testParam(){
    	Session session = opensession();
    	String hql = "from Student "
    			+ "where name=:name";
    	Query query = session.createQuery(hql);
    	//为参数赋值
    	query.setString("name", "Tom");
    	List<Student> list = query.list();
    	for(Student stu:list){
    		System.out.println(stu);
    	}
    	session.close();
    }
}
