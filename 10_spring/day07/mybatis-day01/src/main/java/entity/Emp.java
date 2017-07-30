package entity;
/**
 * 实体类
 *	属性名要与表的字段名一致（大小写可以不一样）
 */
public class Emp {
	private Integer id;
	private String ename;
	private Integer age;
	
	@Override
	public String toString() {
		return "Emp [id=" + id + ", ename=" + ename + ", age=" + age + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}



