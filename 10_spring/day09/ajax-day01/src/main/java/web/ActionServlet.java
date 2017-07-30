package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet 	extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		//分析请求资源路径，依据请求不同，调用
		//不同的分支来处理。
		String uri =
				request.getRequestURI();
		System.out.println("uri:" + uri);
		String action = 
			  uri.substring(
					  uri.lastIndexOf("/"),
					  uri.lastIndexOf("."));
		System.out.println("action:" + action);
		response.setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if("/check_uname".equals(action)){
			String username = 
					request.getParameter(
							"username");
			System.out.println("username:"
							+ username);
			if("Sally".equals(username)){
				out.println("用户名被占用");
			}else{
				out.println("可以使用");
			}
		}else if("/getNumber".equals(action)){
			Random rd = new Random();
			int number = rd.nextInt(1000);
			System.out.println("number:"
			+ number);
			out.println(number);
		}
		
	}
}



