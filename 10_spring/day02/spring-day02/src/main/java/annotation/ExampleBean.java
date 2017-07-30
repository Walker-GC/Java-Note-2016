package annotation;

import org.springframework.stereotype.Component;

@Component("eb1")
public class ExampleBean {
   
	public ExampleBean() {
		System.out.println("ExampleBean()");
	}
	
}
