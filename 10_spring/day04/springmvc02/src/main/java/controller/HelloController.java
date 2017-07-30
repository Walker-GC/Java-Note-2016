package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 处理器类
 * 1.不用实现Controller接口。
 * 2.可以添加多个方法。
 * 3.方法名不做要求，返回值类型可以是
 * ModelAndView,也可以是String。
 * 4.在类名前添加@Controller。
 * 5.使用@RequestMapping来告诉
 * DispatcherServlet,请求路径与处理器的对
 * 应关系。
 */
@Controller
public class HelloController {
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println("hello()");
		return "hello";
	}
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "login";
	}
	
	@RequestMapping("/login.do")
	/*
	 * 使用request对象读取请求参数值。
	 */
	public String checkLogin1(
			HttpServletRequest request){
		System.out.println("checkLogin1()");
		String adminCode = 
				request.getParameter(
						"adminCode");
		String pwd = 
				request.getParameter("pwd");
		System.out.println("adminCode:"
						+ adminCode 
						+ " pwd:" + pwd);
		return "index";
	}
	
	@RequestMapping("/login2.do")
	/**
	 * 获取请求参数值的第二种方式，使用
	 * @RequestParam(String param),param
	 * 是实际请求参数名。
	 * 注：
	 * 	建议，即使实际请求参数名与形参名一致，
	 * 也要添加@RequestParam进行说明。
	 */
	public String checkLogin2(
			@RequestParam("adminCode") 
			String adminCode,
			@RequestParam("pwd") 
			String password){
		System.out.println("checkLogin2()");
		System.out.println("adminCode:"
				+ adminCode 
				+ " pwd:" + password);
		return "index";
	}
	
	@RequestMapping("/login3.do")
	/**
	 * 获取请求参数值的第三种方式：
	 * 封装成一个javabean。
	 */
	public String checkLogin3(AdminParam ap){
		System.out.println("checkLogin3()");
		System.out.println(
				"adminCode:" 
		+ ap.getAdminCode());
		return "index";
	}
	
	@RequestMapping("/login4.do")
	/**
	 * 向页面传值的第一种方式：
	 * 使用request对象。
	 */
	public String checkLoging4(
			AdminParam ap,
			HttpServletRequest request){
		System.out.println("checkLogin4()");
		request.setAttribute(
				"adminCode", ap.getAdminCode());
		//默认使用转发
		return "index";
	}
	
	@RequestMapping("/login5.do")
	/**
	 * 向页面传值的第二种方式：
	 * 通过session对象。
	 */
	public String checkLogin5(
			AdminParam ap,
			HttpSession session){
		System.out.println("checkLogin5()");
		session.setAttribute("adminCode",
				ap.getAdminCode());
		return "index";
	}
	
	@RequestMapping("/login6.do")
	/**
	 * 向页面传值的第三种方式：
	 * 通过ModelMap对象。
	 */
	public String checkLogin6(
			AdminParam ap,ModelMap mm){
		System.out.println("checkLogin6()");
		/*
		 * 相当于将数据绑订到了request对象
		 * (request.setAttribute),绑订名
		 * 就是"adminCode"。
		 */
		mm.addAttribute("adminCode",
				ap.getAdminCode());
		return "index";
	}
	
	@RequestMapping("/login7.do")
	/**
	 * 向页面传值的第四种方式：
	 * 通过ModelAndView对象。
	 */
	public ModelAndView checkLogin7(
			AdminParam ap){
		System.out.println("checkLogin7()");
		//将处理结果先放到Map里面。
		Map<String,Object> data = 
				new HashMap<String,Object>();
		//相当于将数据绑订到request。
		data.put("adminCode", ap.getAdminCode());
		//构造一个ModelAndView对象。
		ModelAndView mav = 
				new ModelAndView("index",data);
		return mav;
	}
	
	@RequestMapping("/login8.do")
	/**
	 * 重定向的第一种方式
	 */
	public String checkLogin8(){
		System.out.println("checkLogin8()");
		return "redirect:toIndex.do";
	}
	
	@RequestMapping("/login9.do")
	/**
	 * 重定向的第二种方式
	 */
	public ModelAndView checkLogin9(){
		System.out.println("checkLogin9()");
		RedirectView rv = 
				new RedirectView("toIndex.do");
		return new ModelAndView(rv);
	}
	
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "index";
	}
	
	
}





