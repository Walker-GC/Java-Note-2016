package cn.tedu.entity;

import java.io.Serializable;

public class UserPwd implements Serializable{
	private int id ;
	private String name;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserPwd [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
