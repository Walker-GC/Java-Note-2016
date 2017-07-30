package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BmiController {
	@RequestMapping("/toBmi.do")
	public String toBmi(){
		return "bmi";
	}
	
	@RequestMapping("/bmi.do")
	public String bmi(
			HttpServletRequest request){
		String height = 
				request.getParameter("height");
		String weight = 
				request.getParameter("weight");
		System.out.println(height + " " + weight);
		//计算bmi指数
		double bmi = 
				Double.parseDouble(weight) /
				Double.parseDouble(height)/
				Double.parseDouble(height);
		//依据bmi指数，判断用户的体质
		String status = "正常";
		if(bmi < 19){
			status = "过轻";
		}
		if(bmi > 25){
			status = "过重";
		}
		//向页面传值
		request.setAttribute("status", status);
		return "view";
	}
	
	
	
}
