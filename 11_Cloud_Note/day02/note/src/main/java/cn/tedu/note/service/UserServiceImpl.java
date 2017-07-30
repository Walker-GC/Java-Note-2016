package cn.tedu.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

/**
 * 业务层实现类
 * @Service 与组件扫描配合, 扫描到
 *   cn.tedu.note.service
 */
@Service("userService")
public class UserServiceImpl
	implements UserService{

	@Autowired //注入的组件ID是 userDao
	private UserDao userDao;
	
	public User login(String name, 
			String password) 
		throws NameOrPasswordException {
		//检查入口参数!!!
		if(name==null||name.trim().isEmpty()){
			throw new NameOrPasswordException("用户为空");
		}
		if(password==null||password.trim().isEmpty()){
			throw new NameOrPasswordException("密码空");
		}
		name=name.trim();
		password.trim();
		User user=userDao.findUserByName(name);
		if(user==null){
			throw new NameOrPasswordException("木有人");
		}
		if(password.equals(user.getPassword())){
			//登录成功!!
			return user;
		}
		throw new NameOrPasswordException("错误密码");
	}
}



