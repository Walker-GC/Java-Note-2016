package cn.tedu.note.service;

import cn.tedu.note.entity.User;

/**
 * 服务接口
 */
public interface UserService {
	/**
	 * 封装登录逻辑
	 * 如果登录成功就返回登录的成功的用户信息
	 * 否则登录失败就抛出异常
	 * @param name 用户名
	 * @param password 密码
	 * @return 登录成功的用户信息
	 * @throws NameOrPasswordException 登录失败
	 */
	User login(String name, String password)
		throws NameOrPasswordException;
}






