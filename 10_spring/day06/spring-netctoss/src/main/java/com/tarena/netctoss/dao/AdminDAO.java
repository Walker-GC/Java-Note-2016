package com.tarena.netctoss.dao;

import com.tarena.netctoss.entity.Admin;

public interface AdminDAO {
	public Admin findByAdminCode(
			String adminCode);
}

