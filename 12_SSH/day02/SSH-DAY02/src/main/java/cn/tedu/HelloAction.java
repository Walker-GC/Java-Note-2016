package cn.tedu;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.tedu.service.DemoService;

@Controller
@Scope("prototype") //默认singleton
public class HelloAction {
	@Resource
	private DemoService demoService;
	
	public String execute(){
		demoService.hello();
		System.out.println("Hello World！！！");
		return "success";
	}
}
