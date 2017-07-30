package com.tarena.netctoss.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarena.netctoss.dao.CostDAO;
import com.tarena.netctoss.entity.Cost;

@Controller
public class CostController {
	@Resource(name="costDAO")
	private CostDAO costDAO;
	
	@RequestMapping("/cost.do")
	public String cost(
			HttpServletRequest request){
		System.out.println("cost()");
		try{
			List<Cost> costs = 
					costDAO.findAll();
			request.setAttribute("costs", costs);
			return "fee_list";
		}catch(Exception e){
			e.printStackTrace();
			//系统异常，提示用户稍后重试
			return "error";
		}
		
	}
}
