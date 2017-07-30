package com.tarena.netctoss.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.netctoss.dao.AdminDAO;
import com.tarena.netctoss.entity.Admin;

@Controller
public class LoginController {
	@Resource(name="adminDAO")
	private AdminDAO adminDAO;
	
	@RequestMapping("/checkcode.do")
	//生成一张验证码图片
	public void checkcode(){
		
	}
	
	@RequestMapping("/toLogin.do")
	public String toLogin(){
		System.out.println("toLogin()");
		return "login";
	}
	
	@RequestMapping("/login.do")
	public String checkLogin(
			HttpServletRequest request,
			HttpSession session){
		System.out.println("checkLogin()");
		//读取帐号、密码
		String adminCode = 
				request.getParameter("adminCode");
		String pwd = 
				request.getParameter("pwd");
		System.out.println("adminCode:"
				+ adminCode + " pwd:" + pwd);
		try{
			Admin admin = 
				adminDAO.findByAdminCode(
							adminCode);
			if(admin != null && 
					admin.getPassword().equals(pwd)){
				//登录成功，绑订一些数据到session
				session.setAttribute("admin", admin);
				//登录成功,重定向到主页
				return "redirect:toIndex.do";
			}else{
				//登录失败,提示用户
				request.setAttribute(
						"login_error", 
						"帐号或密码错误");
				return "login";
			}
		}catch(Exception e){
			//记日志
			e.printStackTrace();
			//发生了系统异常，提示用户稍后重试
			return "error";
		}
		
	}
	
	
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "index";
	}
	
	
}



