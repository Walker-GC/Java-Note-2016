package cn.tedu.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import cn.tedu.entity.Person;

@Controller
public class StackAction {
	String message;
	public String getMessage() {
		return message;
	}
	public String execute(){
		message="demo";
		ActionContext context=
				ActionContext.getContext();
		ValueStack stack=context.getValueStack();
		Person p=new Person(1,"Jerry","Hello Jerry!");
		//将数据添加到值栈
		stack.push(p);
		context.getSession()
		.put("loginName", "Robin");
		System.out.println("demo Stack test");
		return "success";
	}
}
