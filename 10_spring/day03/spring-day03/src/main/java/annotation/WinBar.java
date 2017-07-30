package annotation;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("wb1")
public class WinBar {
	
	private Waiter wt;

	public WinBar() {
		System.out.println("WinBar()");
	}

	public Waiter getWt() {
		return wt;
	}
	
	@Resource(name="wt")
	//name属性指定要注入的bean的id
	public void setWt(Waiter wt) {
		System.out.println("setWt()");
		this.wt = wt;
	}

	@Override
	public String toString() {
		return "WinBar [wt=" + wt + "]";
	}
	
	
}
