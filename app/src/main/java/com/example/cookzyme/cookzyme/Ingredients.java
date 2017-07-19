package com.example.cookzyme.cookzyme;

import java.util.Date;

public class Ingredients {
	
	private String ingredient_name;
	private String path;
	private String unit;
    private int amount;
    private Date expire;

	public Ingredients(String ingredient_name,String path,String unit) {
		// TODO Auto-generated constructor stub
		this.ingredient_name = ingredient_name;
		this.path = path;
		this.unit=unit;
	}
    public Ingredients(String ingredient_name,String path) {
        // TODO Auto-generated constructor stub
        this.ingredient_name = ingredient_name;
        this.path = path;
    }
	public Ingredients(String ingredient_name,String path,String unit,int amount, Date expire) {
		// TODO Auto-generated constructor stub
		this.ingredient_name = ingredient_name;
		this.amount=amount;
		this.unit=unit;
		this.expire=expire;
		this.path = path;
	}
	public String getName() {
		return ingredient_name;
	}
	public void setName(String name) {
		this.ingredient_name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

    public String getIngredient_name() {
        return ingredient_name;
    }

    public String getUnit() {
        return unit;
    }

    public int getAmount() {
        return amount;
    }

    public Date getExpire() {
        return expire;
    }
}
