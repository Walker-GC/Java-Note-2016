package annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mg1")
public class Manager {
	
	@Value("苍松")
	private String name;
	@Value("#{config.pagesize}")
	private String pageSize;
	
	public Manager() {
		System.out.println("Manager()");
	}
	
	@Override
	public String toString() {
		return "Manager [name=" + name + ", pageSize=" + pageSize + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
