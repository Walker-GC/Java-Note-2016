package cn.tedu.note.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;
import cn.tedu.note.util.JsonResult;

/**
 * 组件扫描 
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<User> login(
		String name, String password){
		try{
			User user=userService.login(
					name, password);
			return new JsonResult<User>(user);
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult<User>(e);
		}
	}
}





