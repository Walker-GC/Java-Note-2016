package cn.tedu.note.service;

import cn.tedu.note.entity.User;

public interface UserService {
	
	User login(String name, String password)
		throws UserNameException,
		PasswordException;
	
}



