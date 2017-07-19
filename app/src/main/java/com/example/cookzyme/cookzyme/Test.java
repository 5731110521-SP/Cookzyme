package com.example.cookzyme.cookzyme;

import java.util.Date;

/**
 * Created by mintra nankongnaeb on 7/17/2017.
 */

public class Test {
    @com.google.gson.annotations.SerializedName("id")

    private String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }
    @com.google.gson.annotations.SerializedName("updatedAt")
    private Date updatedat;
    @com.google.gson.annotations.SerializedName("version")
    private String version;
    @com.google.gson.annotations.SerializedName("createdAt")
    private Date createdat;
    @com.google.gson.annotations.SerializedName("deleted")
    private boolean deleted;


    public Test(String mId) {
        this.mId = mId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
