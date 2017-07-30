package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SomeController {
	@RequestMapping("/hello.do")
	public String hello1(
			HttpServletRequest request){
		System.out.println("hello1()");
		String number = 
				request.getParameter(
						"number");
		Integer.parseInt(number);
		return "hello";
	}
	
	@RequestMapping("/hello2.do")
	public String hello2(
			HttpServletRequest request){
		System.out.println("hello2()");
		String number = 
				request.getParameter(
						"number");
		number.charAt(10);
		return "hello";
	}
	
	
}



