package first;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstSpring {

	public static void main(String[] args) {
		//启动spring容器
		/*
		 * ApplicationContext:接口。
		 * ClassPathXmlApplicationContext:是
		 * 实现了AppliationContext接口的类。
		 * 注意：配置文件的路径和文件名,spring
		 * 容器在启动时，需要读取配置文件。
		 */
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		System.out.println(ac);
	}

}
