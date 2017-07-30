package cn.tedu.note.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.PasswordException;
import cn.tedu.note.service.UserNameException;
import cn.tedu.note.service.UserService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<User> login(String name,
			String password){
		User user = 
			userService.login(name, password);
		return new JsonResult<User>(user);
	}
		 
	//UserController.java
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult<User> regist(
			String name, String nick, 
			String password, String confirm){
		User user = userService
			.regist(name, nick,
			password, confirm);
		return new JsonResult<User>(user);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(
			UserNameException.class)
	@ResponseBody
	public JsonResult userName(
			UserNameException e){
		e.printStackTrace();
		return new JsonResult(2, e);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(PasswordException.class)
	public JsonResult password(
			PasswordException e){
		e.printStackTrace();
		return new JsonResult(3, e);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler
	@ResponseBody
	public JsonResult exp(Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	}
}







