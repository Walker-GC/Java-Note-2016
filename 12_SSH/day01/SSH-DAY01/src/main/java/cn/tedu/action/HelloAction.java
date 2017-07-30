package cn.tedu.action;

public class HelloAction {
	private String message;
	
	public String getMessage() {
		return message;
	}
	/*
	 * 默认方法execute()
	 */
	public String execute(){
		System.out.println("Hello World!!!");
		message="Hi......";
		//返回值是success
		return "success";
	}
}
