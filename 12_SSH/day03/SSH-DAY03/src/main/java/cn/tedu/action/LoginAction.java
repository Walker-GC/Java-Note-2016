package cn.tedu.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import cn.tedu.entity.UserPwd;
import cn.tedu.service.UserService;
import cn.tedu.util.Result;

@Controller
public class LoginAction implements SessionAware{
	private String name;
	private String password;
	Result result;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	private Map<String,Object> session;
	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Resource
	UserService userService;
	public String execute(){
		Result result=userService.checkLogin(
				name, password);
		this.result=result;
		if(result.getStatus()==0){
			UserPwd user=(UserPwd) result.getData();
			session.put("LoginUser", user);
		}else{
			UserPwd user=(UserPwd) result.getData();
			return "error";
		}
		return "success";
	}
	
}
