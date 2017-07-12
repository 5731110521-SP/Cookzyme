package com.example.cookzyme.cookzyme;

import java.util.Date;

public class Ingredients {
	
	private String name;
	private String path;
    private int amount;
    private String unit;
    private Date expire;

	public Ingredients(String name,String path) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.path = path;
	}
	public Ingredients(String name,int amount, String unit,Date expire,String path) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.amount=amount;
		this.unit=unit;
		this.expire=expire;
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
