package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import day02.DBUtil;

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
			String nickName = scanner.nextLine();
			//下面可以做一些正确性的验证工作
			
			
			
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.201.217:1521:orcl",
				"fancq",
				"zaq12wsx"
			);
			System.out.println("已连接数据库!");
			Statement state = conn.createStatement();
			String sql = "INSERT INTO userinfo "
					   + "(id,username,password,email,nickname,account) "
					   + "VALUES "
					   + "(seq_userinfo_id.NEXTVAL,'"+username+"','"+password+"','"+email+"','"+nickName+"',5000)";
			System.out.println(sql);
			int i = state.executeUpdate(sql);
			if(i>0){
				System.out.println("注册成功!");
			}
			//没有其他数据库操作则关闭数据库连接
			conn.close();
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
		Connection conn = null;
		try {
			Scanner scanner = new Scanner(System.in);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.201.217:1521:orcl",
				"openlab",
				"open123"
			);
			Statement state = conn.createStatement();
			while(true){
				System.out.println("请输入用户名:");
				String username = scanner.nextLine();
				String sql = "SELECT username "
						   + "FROM userinfo "
						   + "WHERE UPPER(username)=UPPER('"+username+"')";
				ResultSet rs = state.executeQuery(sql);
				if(rs.next()){
					System.out.println(username+"已经被占用,请更换.");
					continue;
				}
				return username;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(conn != null){
				conn.close();
			}
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
		Connection conn = null;
		try {	
			System.out.println("欢迎登陆");
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入用户名:");
			String username = scanner.nextLine();
			System.out.println("请输入密码:");
			String password = scanner.nextLine();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.201.217:1521:orcl",
				"fancq",
				"zaq12wsx"
			);
			
			Statement state = conn.createStatement();
			String sql = "SELECT id,username,password,email,nickname,account "
					   + "FROM userinfo "
					   + "WHERE username='"+username+"' "
					   + "AND password='"+password+"' ";
			/*
			 * 由于使用Statement执行SQL语句时，SQL中含有
			 * 用户输入的内容，需要先将SQL语句与用户输入的
			 * 内容拼接在一起，这就导致若用户输入的内容含有
			 * SQL语法中的关键字等信息会改变SQL原本的语义
			 * 这就是SQL注入攻击。
			 */
			
			ResultSet rs = state.executeQuery(sql);
			if(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String pwd = rs.getString("password");
				String email = rs.getString("email");
				String nickname = rs.getString("nickname");
				double account = rs.getDouble("account");
				UserInfo userinfo = new UserInfo(id,name,pwd,email,nickname,account);
				this.userInfo = userinfo;
				System.out.println("登陆成功!欢迎您:"+userinfo.getNickname());
			}else{
				System.out.println("登陆失败!用户名或密码不正确!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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






