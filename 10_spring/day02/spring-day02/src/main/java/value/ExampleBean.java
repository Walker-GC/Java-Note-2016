package value;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ExampleBean {
	private String name;
	private int age;
	private List<String> cities;
	private Set<String> interest;
	private Map<String,Double> score;
	private Properties db;
	public ExampleBean() {
		System.out.println("ExampleBean()");
	}
	
	public Map<String, Double> getScore() {
		return score;
	}

	public Properties getDb() {
		return db;
	}

	public void setDb(Properties db) {
		this.db = db;
	}

	public void setScore(Map<String, Double> score) {
		this.score = score;
	}

	public Set<String> getInterest() {
		return interest;
	}

	public void setInterest(Set<String> interest) {
		this.interest = interest;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		System.out.println("setCities()");
		this.cities = cities;
	}

	@Override
	public String toString() {
		return "ExampleBean [name=" + name + ", age=" + age + ", cities=" + cities + ", interest=" + interest
				+ ", score=" + score + ", db=" + db + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("setName()");
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		System.out.println("setAge()");
		this.age = age;
	}
	
	
}
