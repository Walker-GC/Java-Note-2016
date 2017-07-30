package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 处理器类
 *
 */
public class HelloController 
	implements Controller{

	public ModelAndView handleRequest(
			HttpServletRequest req, 
			HttpServletResponse res) 
					throws Exception {
		System.out.println("HelloController's "
				+ "handleRequest()");
		//创建一个ModelAndView对象。
		/* ModelAndView有两个构造器：
		 * 第一个
		 * 	ModelAndView(String viewName),
		 *  viewName是视图名。
		 * 第二个
		 *  ModelAndView(String viewName,
		 *  Map data), data是处理结果。
		 */
		return new ModelAndView("hello");
	}
	

}





