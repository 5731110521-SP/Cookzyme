package com.example.cookzyme.cookzyme;

/**
 * Created by Panuvit on 19/7/2560.
 */

public class Recipes {
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    private String food_name;
    private int stepNo;
    private String step;

    public Recipes(String food_name, int stepNo, String step) {
        this.food_name = food_name;
        this.stepNo = stepNo;
        this.step = step;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
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
