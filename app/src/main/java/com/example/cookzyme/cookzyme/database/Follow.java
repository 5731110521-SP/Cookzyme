package com.example.cookzyme.cookzyme.database;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

import java.util.Date;

/**
 * Created by mintra nankongnaeb on 7/14/2017.
 */

public class Follow {
    public String email;
    public String following_email;

    @com.google.gson.annotations.SerializedName("id")
    public String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }
    @com.google.gson.annotations.SerializedName("updatedAt")
    public Date updatedat;
    @com.google.gson.annotations.SerializedName("version")
    public String version;
    @com.google.gson.annotations.SerializedName("createdAt")
    public Date createdat;
    @com.google.gson.annotations.SerializedName("deleted")
    public boolean deleted;
    public Follow(String email, String following_email) {
        this.email = email;
        this.following_email = following_email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollwing_email() {
        return following_email;
    }

    public void setFollwing_email(String follwing_email) {
        this.following_email = follwing_email;
    }
}
