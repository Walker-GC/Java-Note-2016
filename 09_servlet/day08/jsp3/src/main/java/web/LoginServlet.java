package web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void service(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		//接收参数
		String code = req.getParameter("code");
		//将账号存入cookie
		//每个cookie只能保存一份数据，必须是字符串
		Cookie c1 = new Cookie("code",code);
		//修改cookie生存时间，将其存在硬盘上
		c1.setMaxAge(60000);
		//修改cookie的生效路径
		c1.setPath("/jsp3");
		//将cookie绑定到response上，在响应时
		//服务器会自动将其发送给浏览器。
		res.addCookie(c1);
		
		Cookie c2 = new Cookie("city",
			URLEncoder.encode("北京", "utf-8"));
		res.addCookie(c2);
	}

	
	
}






