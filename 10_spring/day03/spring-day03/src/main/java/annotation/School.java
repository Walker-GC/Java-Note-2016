package annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("school")
public class School {
	private Waiter wt;
	
	public School() {
		System.out.println("School()");
	}

	@Autowired
	public School(
			@Qualifier("wt") Waiter wt) {
		System.out.println(
				"School(Waiter wt)");
		this.wt = wt;
	}

	@Override
	public String toString() {
		return "School [wt=" + wt + "]";
	}
	
}
