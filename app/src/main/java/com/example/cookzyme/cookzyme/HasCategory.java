package com.example.cookzyme.cookzyme;

/**
 * Created by Panuvit on 19/7/2560.
 */

public class HasCategory {
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    private String food_name;
    private String category;

    public HasCategory(String food_name, String category) {
        this.food_name = food_name;
        this.category = category;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
