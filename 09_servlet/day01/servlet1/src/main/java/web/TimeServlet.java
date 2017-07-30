package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeServlet extends HttpServlet {

	@Override
	protected void service(
		HttpServletRequest req, 
		HttpServletResponse res) 
		throws ServletException, IOException {
		//告诉浏览器给它输出的是什么格式的内容
		res.setContentType("text/html");
		//获取输出流，该流指向的目标就是浏览器
		PrintWriter out = res.getWriter();
		//此处偷懒省略代码N行
		Date date = new Date();
		SimpleDateFormat sdf = 
			new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(date);
		out.println("<p>"+now+"</p>");
		out.close();
	}
	
}




