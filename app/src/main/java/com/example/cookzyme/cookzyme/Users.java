package com.example.cookzyme.cookzyme;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

import java.util.Date;

/**
 * Created by mintra nankongnaeb on 7/7/2017.
 */

public class Users {
    public String email;
    public String password;
    public String name;
    public Date birthdate;
    public String path;
    public String bgpath;
    public int following;
    public int follower;
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    public Users(String email, String password, String name, Date birthdate, String path, String bgPath, int following, int follower) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.path = path;
        this.bgpath = bgPath;
        this.following = following;
        this.follower = follower;
    }

    public String getBgPath() {
        return bgpath;
    }

    public void setBgPath(String bgPath) {
        this.bgpath = bgPath;
    }

    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }
}
