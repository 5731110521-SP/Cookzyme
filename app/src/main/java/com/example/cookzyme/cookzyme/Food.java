package com.example.cookzyme.cookzyme;

public class Food {

	private String name;
	private String path;
	private int rank;
    private int like;
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
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	private int energy;
	public Food(String name,String path,int energy) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.path = path;
		this.energy = energy;
        rank=0;
        like=0;
	}

    public int getRank() {
        return rank;
    }

    public int getLike() {
        return like;
    }
}
