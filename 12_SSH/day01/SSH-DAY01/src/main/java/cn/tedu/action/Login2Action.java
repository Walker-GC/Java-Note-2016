package cn.tedu.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import cn.tedu.entity.User;

public class Login2Action implements SessionAware{
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	Map<String,Object> session;
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String execute(){
		if(user.getName().equals("Tom")
				&& user.getPwd().equals("123")){
			session.put("loginName","Tom");
			return "success";
		}
		message="用户名或密码错误，请重新登录！";
		return "error";
	}
}
