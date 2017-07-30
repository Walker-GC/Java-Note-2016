package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import day02.DBUtil;
import day03.UserInfoDAO;
import day03.UserInfoDAOImp;

/**
 * 用户管理系统
 * 功能:
 * 1:注册新用户
 * 2:用户登录
 * 3:修改用户信息
 * 4:查看用户余额
 * 5:显示所有用户信息(不含有用户密码信息)
 * 6:转账
 * 7:注销
 * 
 * @author adminitartor
 *
 */
public class UserService {
	/*
	 * 当前登陆用户的信息
	 */
	private UserInfo userInfo;
	
	/*
	 * UserInfoDAO
	 */
	private UserInfoDAO userInfoDAO;
	
	/**
	 * 构造方法，用于做初始化操作
	 */
	public UserService(){
		/*
		 * 将来，这里不会直接new这个DAO的实现类
		 * 而是靠工厂反射一个实例回来。
		 * 若使用spring框架，DAO的初始化也是靠
		 * sprint注入实例。这些都可以达到业务
		 * 层与DAO层解耦的目的
		 */
		userInfoDAO = new UserInfoDAOImp();
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
				System.out.println("6:转账");
				System.out.println("7:注销");
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
					transforAccount();
					break;
				case 7:
					logout();
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 转账
	 */
	private void transforAccount(){
		/*
		 * 转账业务:
		 * 首先必须是登陆用户
		 * 要求用户输入转入账号的用户名
		 * 然后在输出转出的金额
		 * 该金额必须小于等于当前用户的余额
		 * 然后执行SQL语句，将当前用户的余额
		 * 与转入账户的余额进行相应的修改。
		 * 最终通知当前用户转账操作是否成功。
		 */
		if(userInfo==null){
			System.out.println("请先登陆");
			return;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入转入账号的用户名:");
		String inUser = scanner.nextLine();
		
		System.out.println("您当前余额为:"+this.userInfo.getAccount());
		double money = 0;
		while(true){
			System.out.println("请输入转出金额:");
			money = Double.parseDouble(scanner.nextLine());
			if(money>this.userInfo.getAccount()){
				System.out.println("余额金额不足。");
				continue;
			}
			break;
		}
		Connection conn = null;
		PreparedStatement outPs = null;
		PreparedStatement inPs = null;
		try {
			/*
			 * JDBC默认是自动提交事务的，即:
			 * 每当执行一条DML操作后，就自动
			 * 执行COMMIT。
			 * 若希望执行控制事务，需要先取消
			 * 自动提交事务，然后在控制事务。
			 * 事务的控制是Connection管理的
			 */
			conn = DBUtil.getConnection();
			//取消自动提交事务
			conn.setAutoCommit(false);
			
			//转出的sql
			String outSql = "UPDATE userinfo "
					      + "SET account=account-? "
					      + "WHERE username=?";
			outPs
				= conn.prepareStatement(outSql);
			outPs.setDouble(1, money);
			outPs.setString(2, this.userInfo.getUsername());
			int i = outPs.executeUpdate();
			if(i<=0){
				System.out.println("转账有误!");
				//回退事务
				conn.rollback();
				return;
			}
			this.userInfo.setAccount(this.userInfo.getAccount()-money);
			//转入的sql
			String inSql = "UPDATE userinfo "
					     + "SET account=account+? "
					     + "WHERE username=?";
			inPs
				= conn.prepareStatement(inSql);
			inPs.setDouble(1, money);
			inPs.setString(2, inUser);
			i = inPs.executeUpdate();
			if(i<=0){
				System.out.println("转账失败!");
				conn.rollback();
			}else{
				System.out.println("转账完毕");
				conn.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(outPs != null){
				try {
					outPs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(inPs != null){
				try {
					inPs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		
	}
	
	/**
	 * 注册新用户
	 */
	private void reg(){
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("欢迎注册!");
			String username = inputUserName();
			System.out.println("请输入密码:");
			String password = scanner.nextLine();
			System.out.println("请输入邮箱地址:");
			String email = scanner.nextLine();
			System.out.println("请输入昵称:");
			String nickname = scanner.nextLine();
			//下面可以做一些正确性的验证工作
			
			
			
			//保存该用户
			UserInfo userinfo 
				= new UserInfo(-1, username, password, email, nickname, 5000);
			boolean tf = userInfoDAO.save(userinfo);
			if(tf){
				System.out.println("注册成功!");
				this.userInfo = userinfo;
				System.out.println("欢迎您!"+userInfo.getNickname());
			}else{
				System.out.println("注册失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 要求用户在注册时输入用户名，并检查该
	 * 用户名是否已经被使用，若被使用会一致要求
	 * 用户重新输入直到可以使用为止。
	 * @return
	 * @throws Exception 
	 */
	private String inputUserName() throws Exception{
		try {
			Scanner scanner = new Scanner(System.in);
			while(true){
				System.out.println("请输入用户名:");	
				String username = scanner.nextLine();
				if(userInfoDAO.findByName(username)!=null){
					System.out.println("用户名已存在，请更换");
					continue;
				}
				return username;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
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
		try {	
			System.out.println("欢迎登陆");
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入用户名:");
			String username = scanner.nextLine();
			System.out.println("请输入密码:");
			String password = scanner.nextLine();
			
			//根据用户名查询用户信息
			UserInfo userinfo = userInfoDAO.findByName(username);
			if(userinfo!=null&&userinfo.getPassword().equals(password)){
				System.out.println("欢迎您:"+userinfo.getNickname());
				this.userInfo = userinfo;
			}else{
				System.out.println("登陆失败!用户名或密码不正确");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
		}
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
		if(userInfo==null){
			System.out.println("请先登陆");
			return;
		}
		System.out.println("您好:"+this.userInfo.getNickname()+",您当前余额为:"+this.userInfo.getAccount());
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
		if(userInfo==null){
			System.out.println("请先登陆");
			return;
		}
		System.out.println("再见!"+this.userInfo.getNickname());
		this.userInfo = null;
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






