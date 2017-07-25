package com.example.cookzyme.cookzyme.database;

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
    public int like;
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }

    public Posts(String email, String path, String description, String food_name, int like, String mId) {
        this.email = email;
        this.path = path;
        this.description = description;
        this.food_name = food_name;
        this.like = like;
        this.mId = mId;
    }

    public Posts(String email, String path, String description, String food_name, int like) {
        this.email = email;
        this.path = path;
        this.description = description;
        this.food_name = food_name;
        this.like = like;
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

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
