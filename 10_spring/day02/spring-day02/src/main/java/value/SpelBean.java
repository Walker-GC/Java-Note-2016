package value;

public class SpelBean {
	private String name;
	private String city;
	private double score;
	private String pageSize;
	
	public SpelBean() {
		System.out.println("SpelBean()");
	}
	
	@Override
	public String toString() {
		return "SpelBean [name=" + name + ", city=" + city + ", score=" + score + ", pageSize=" + pageSize + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
}
