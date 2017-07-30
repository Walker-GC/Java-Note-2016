package ioc;

public class A {
	private B b;
	public A(){
		System.out.println("A()");
	}
	public A(B b) {
		System.out.println("A(B b)");
		this.b = b;
	}
	public void execute(){
		b.f1();
	}
	
}
