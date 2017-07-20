package com.example.cookzyme.cookzyme.database;

public class HowToCook {
	
	private String foodName;
	private int stepNo;
	private String step;
	public HowToCook(String foodName, int stepNo, String step) {
		this.foodName = foodName;
		this.stepNo = stepNo;
		this.step = step;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getStepNo() {
		return stepNo;
	}
	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
}
