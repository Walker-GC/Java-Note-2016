package cn.tedu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.UserPwd;
import cn.tedu.util.Result;
@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	public Result checkLogin(String name, String password) {
		Result result=new Result();
		//根据用户名获取对象
		UserPwd user=userDao.findByName(name);
		//登录不成功
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在！");
			return result;
		}
		//登录成功
		result.setStatus(0);
		result.setMsg("登录成功！");
		user.setPassword("");//屏蔽密码不返回
		result.setData(user);//返回user信息
		return result;
	}
}
