package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CostDao;
import entity.Cost;

public class MainServlet extends HttpServlet {

	@Override
	protected void service(
		HttpServletRequest req, 
		HttpServletResponse res) 
		throws ServletException, IOException {
		//获取请求路径
		String path = req.getServletPath();
		//根据规范处理路径
		if("/findCost.do".equals(path)) {
			findCost(req,res);
		} else if("/toAddCost.do".equals(path)) {
			toAddCost(req,res);
		} else {
			throw new RuntimeException("查无此页");
		}
	}
	
	//打开增加资费页面
	protected void toAddCost(
		HttpServletRequest req, 
		HttpServletResponse res) 
		throws ServletException, IOException {
		//转发到JSP
		//相对路径参考资费查询
		req.getRequestDispatcher(
			"WEB-INF/cost/add.jsp")
			.forward(req, res);
	}
	
	//查询资费
	protected void findCost(
		HttpServletRequest req, 
		HttpServletResponse res) 
		throws ServletException, IOException {
		//查询所有的资费
		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();
		//将其转发给JSP
		req.setAttribute("costs", list);
		//当前:/netctoss/findCost.do
		//目标:/netctoss/WEB-INF/cost/find.jsp
		req.getRequestDispatcher(
			"WEB-INF/cost/find.jsp")
			.forward(req, res);
	}
	
}













