package basic;

public class ExampleBean {

	public ExampleBean() {
		System.out.println("ExampleBean()");
	}
	
	public void init(){
		System.out.println("init()");
	}
	
	public void sendMessage(){
		System.out.println("sendMessage()");
	}
	
	public void destroy(){
		System.out.println("destroy()");
	}
	
}
