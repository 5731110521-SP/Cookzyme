package com.example.cookzyme.cookzyme;

public class Ingredients {
	
	private String name;
	private String path;
	public Ingredients(String name,String path) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
