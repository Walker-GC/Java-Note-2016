package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {

	@Override
	protected void service(
		HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
		//获取cookie
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			res.setContentType(
				"text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			for(Cookie c: cookies) {
				out.println(
					c.getName()+":"+
					URLDecoder.decode(c.getValue(),"utf-8"));
			}
			out.close();
		}
	}

	
	
}








