package cn.tedu.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

@Service("userService")
public class UserServiceImpl
	implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public User login(String name, 
			String password) 
			throws UserNameException, 
			PasswordException {
		//检验输入参数的合理性
		if(name==null||name.trim().isEmpty()){
			throw new UserNameException("名不能空");
		}
		String reg = "^\\w{3,10}$";
		if(! name.matches(reg)){
			throw new UserNameException("不合规");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("不能空");
		}
		if(! password.matches(reg)){
			throw new PasswordException("不合规");
		}
		//查询用户数据
		User user=userDao.findUserByName(name);
		if(user==null){
			throw new UserNameException("错误");
		}
		if(user.getPassword().equals(password)){
			//业务处理
			//登录成功，返回用户信息
			return user;
		}
		throw new PasswordException("密码错");
	}
}





