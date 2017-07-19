package com.example.cookzyme.cookzyme;

/**
 * Created by Panuvit on 19/7/2560.
 */

public class Comments {
    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    private String post_id;
    private  String commenter_email;
    private String comment;

    public Comments(String post_id, String commenter_email, String comment) {
        this.post_id = post_id;
        this.commenter_email = commenter_email;
        this.comment = comment;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getCommenter_email() {
        return commenter_email;
    }

    public void setCommenter_email(String commenter_email) {
        this.commenter_email = commenter_email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
