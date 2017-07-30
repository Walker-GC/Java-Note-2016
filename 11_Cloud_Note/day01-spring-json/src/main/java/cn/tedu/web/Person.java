package cn.tedu.web;

import java.io.Serializable;
import java.util.Arrays;

public class Person implements Serializable{
	
	private int id;
	private String name;
	private String[] friends;
	
	public Person() {
	}

	public Person(int id, String name, String[] friends) {
		super();
		this.id = id;
		this.name = name;
		this.friends = friends;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", friends=" + Arrays.toString(friends) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
