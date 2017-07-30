package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.entity.UserPwd;

public interface UserDao {
	public void save(User user);
	public UserPwd findByName(String name);
}
