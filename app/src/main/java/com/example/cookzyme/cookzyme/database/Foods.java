package com.example.cookzyme.cookzyme.database;

public class Foods {

	private String food_name;
	private String path;
	private int like;
	private int energy;
	@com.google.gson.annotations.SerializedName("id")
	private String mId;

	public Foods(String food_name, String path, int like, int energy) {
		this.food_name = food_name;
		this.path = path;
		this.like = like;
		this.energy = energy;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
