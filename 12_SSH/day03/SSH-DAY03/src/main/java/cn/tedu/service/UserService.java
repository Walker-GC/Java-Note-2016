package cn.tedu.service;

import cn.tedu.util.Result;

public interface UserService {
	public Result checkLogin(String name,
			String password);
}
