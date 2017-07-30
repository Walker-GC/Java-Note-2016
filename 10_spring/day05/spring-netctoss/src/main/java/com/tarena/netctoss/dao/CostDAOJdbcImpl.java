package com.tarena.netctoss.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.tarena.netctoss.entity.Cost;

@Repository("costDAO")
public class CostDAOJdbcImpl implements 
CostDAO{
	@Resource(name="ds")
	private DataSource ds;
	public List<Cost> findAll() {
		
		return null;
	}

}
