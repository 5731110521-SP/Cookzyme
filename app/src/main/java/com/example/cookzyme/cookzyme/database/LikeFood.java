package com.example.cookzyme.cookzyme.database;

/**
 * Created by Panuvit on 19/7/2560.
 */

public class LikeFood {
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    private String food_name;
    private String email;

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LikeFood(String food_name, String email) {
        this.food_name = food_name;
        this.email = email;

    }
}
