package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Stock;
import net.sf.json.JSONArray;

public class ActionServlet extends 
HttpServlet{
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
		if("/quoto".equals(action)){
//			if(1==1){
//				throw new ServletException(
//						"模拟生成一个异常");
//			}
			//模拟生成几只股票的信息
			List<Stock> stocks = 
					new ArrayList<Stock>();
			Random r = new Random();
			for(int i = 0; i < 8; i++){
				Stock s = new Stock();
				s.setCode("00041" + r.nextInt(10));
				s.setName("沈阳机床" + r.nextInt(10));
				s.setPrice(r.nextInt(1000));
				stocks.add(s);
			}
			//fromObject方法也可以使用数组
			//作为参数。
			JSONArray jsonArr = 
					JSONArray.fromObject(stocks);
			String jsonStr = jsonArr.toString();
			System.out.println(jsonStr);
			out.println(jsonStr);
		}else if("/check_uname".equals(action)){
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
		}
	}

}
