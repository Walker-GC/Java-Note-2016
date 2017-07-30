package day01;

import java.util.Scanner;

/**
 * 用户管理系统
 * 功能:
 * 1:注册新用户
 * 2:用户登录
 * 3:修改用户信息
 * 4:查看用户余额
 * 5:显示所有用户信息(不含有用户密码信息)
 * 6:注销
 * 
 * @author adminitartor
 *
 */
public class UserService {
	/*
	 * 当前登陆用户的信息
	 */
	private UserInfo userInfo;
	
	/**
	 * 构造方法，用于做初始化操作
	 */
	public UserService(){
		
	}
	/**
	 * 管理系统的启动方法
	 */
	public void start(){
		try {
			Scanner scanner = new Scanner(System.in);
			while(true){
				System.out.println("欢迎使用用户管理系统");
				System.out.println("1:注册新用户");
				System.out.println("2:用户登陆");
				System.out.println("3:修改用户信息");
				System.out.println("4:查看用户余额");
				System.out.println("5:显示所有用户信息");
				System.out.println("6:注销");
				System.out.println("请输入序号来进行相应操作:");
				int index = Integer.parseInt(scanner.nextLine());
				switch (index) {
				case 1:
					reg();
					break;
				case 2:
					login();
					break;
				case 3:
					update();
					break;
				case 4:
					showAccount();
					break;
				case 5:
					showAllUser();
					break;
				case 6:
					logout();
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 注册新用户
	 */
	private void reg(){
		System.out.println("欢迎注册");
	}
	/**
	 * 用户登陆
	 * 要求用户输入用户名及密码
	 * 根据用户输入的用户名与密码查询userinfo表
	 * 若可以查询出该用户记录，则创建一个UserInfo
	 * 实例，并将查询出的该用户信息设置到这个实例
	 * 上，然后将该实例赋给UserService的属性
	 * userInfo上表示其登陆成功。
	 * 
	 */
	private void login(){
		System.out.println("欢迎登陆");
	}
	/**
	 * 修改当前用户信息(需要登陆后才可操作)
	 * 修改用户信息只能修改当前登陆用户的:
	 * 密码，邮箱，昵称
	 */
	private void update(){
		if(userInfo==null){
			System.out.println("请先登陆");
			return;
		}
	}
	/**
	 * 查看当前登陆用户的余额(需要登陆后才可操作)
	 */
	private void showAccount(){
		
	}
	/**
	 * 显示所有用户信息
	 * 仅显示所有用户的用户名，邮箱，昵称，余额
	 */
	private void showAllUser(){
		
	}
	/**
	 * 注销(登出操作，需要登陆后才可操作)
	 */
	private void logout(){
		
	}
	
	public static void main(String[] args) {
		try {
			UserService userService = new UserService();
			userService.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}






