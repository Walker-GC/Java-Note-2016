package controller;
/**
 *  用于封装请求参数值的类，要求：
 *   (1)属性名与实际请求参数名一致。
 *   (2)提供相应的get/set方法。
 *
 */
public class AdminParam {
	private String adminCode;
	private String pwd;
	public String getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
