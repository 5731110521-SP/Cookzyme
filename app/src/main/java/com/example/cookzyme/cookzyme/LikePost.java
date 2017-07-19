package com.example.cookzyme.cookzyme;

/**
 * Created by Panuvit on 19/7/2560.
 */

public class LikePost {
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    private String post_id;
    private String like_email;

    public LikePost(String post_id, String like_email) {
        this.post_id = post_id;
        this.like_email = like_email;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getLike_email() {
        return like_email;
    }

    public void setLike_email(String like_email) {
        this.like_email = like_email;
    }
}
