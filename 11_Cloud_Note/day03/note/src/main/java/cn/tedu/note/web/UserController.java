package cn.tedu.note.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
		try{
			User user = 
				userService.login(name, password);
			return new JsonResult<User>(user);
		}catch(PasswordException e){
			e.printStackTrace();
			return new JsonResult<User>(3,e);
		}catch(UserNameException e){
			e.printStackTrace();
			return new JsonResult<User>(2,e);
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult<User>(e);
		}
	}
	
	//JSON: {state:0, data:{id:...}, message:null}
	//JSON: {state:1, data:null, message:名字空}
	 
}







