package annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("mb1")
@Scope("singleton")
@Lazy(true)
public class MessageBean {

	public MessageBean() {
		System.out.println("MessageBean()");
	}
	
	@PostConstruct   
	public void init(){
		System.out.println("init()");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("destroy()");
	}
	
	
	public void sendMsg(){
		System.out.println("sendMsg()");
	}
	
}


