package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/hello.do")
	public String hello1(){
		System.out.println("hello1()");
		return "hello";
	}
	
	@RequestMapping("/abc/hello2.do")
	public String hello2(){
		System.out.println("hello2()");
		return "hello";
	}
}

