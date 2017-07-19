package com.example.cookzyme.cookzyme;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

import java.util.Date;

/**
 * Created by mintra nankongnaeb on 7/14/2017.
 */

public class Posts {
    public String email;
    public String path;
    public String description;
    public String food_name;
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }

    public Posts(String email, String path, String description, String foodname) {
        this.email = email;
        this.path = path;
        this.description = description;
        this.food_name = foodname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodname() {
        return food_name;
    }

    public void setFoodname(String foodname) {
        this.food_name = foodname;
    }
}
