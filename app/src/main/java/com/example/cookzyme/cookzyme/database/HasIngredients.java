package com.example.cookzyme.cookzyme.database;

public class HasIngredients {
	private String foodName;
	private String ingredientName;
	public HasIngredients(String foodName, String ingredientName) {
		this.foodName = foodName;
		this.ingredientName = ingredientName;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getIngredientName() {
		return ingredientName;
	}
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

}
