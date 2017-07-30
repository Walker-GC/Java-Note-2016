package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {

	@Override
	protected void service(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		//获取session，此session是登录时
		//服务器创建的，在此请求中浏览器
		//通过cookie发送了SID，服务器依据
		//SID找到了那个session。
		HttpSession session = req.getSession();
		String code = (String)
			session.getAttribute("code");
		res.setContentType(
			"text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println(code);
		out.close();
	}
	
}









