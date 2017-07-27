package com.example.cookzyme.cookzyme.database;

import java.util.Comparator;
import java.util.Date;

public class Ingredients implements Comparator<Ingredients>{
	@com.google.gson.annotations.SerializedName("id")
	private String mId;
	private String ingredient_name;
	private String path;
	private String unit;
    private double amount;
    private Date expire;



	public Ingredients(String ingredient_name, String path, String unit) {
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
	public Ingredients(String ingredient_name,String path,String unit,double amount, Date expire) {
		// TODO Auto-generated constructor stub
		this.ingredient_name = ingredient_name;
		this.amount=amount;
		this.unit=unit;
		this.expire=expire;
		this.path = path;
	}
	public Ingredients(String id,String ingredient_name,String path,String unit,double amount, Date expire) {
		// TODO Auto-generated constructor stub
		mId=id;
		this.ingredient_name = ingredient_name;
		this.amount=amount;
		this.unit=unit;
		this.expire=expire;
		this.path = path;
	}
	public Ingredients(Ingredients i) {
		// TODO Auto-generated constructor stub
		mId=i.getId();
		this.ingredient_name = i.getIngredient_name();
		this.amount=i.getAmount();
		this.unit=i.getUnit();
		this.expire=i.getExpire();
		this.path = i.getPath();
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

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getIngredient_name() {
        return ingredient_name;
    }

    public String getUnit() {
        return unit;
    }

    public double getAmount() {
        return amount;
    }

    public Date getExpire() {
        return expire;
    }

	public String getId() {
		return mId;
	}

	@Override
	public int compare(Ingredients o1, Ingredients o2) {
		return 0;
	}
}
