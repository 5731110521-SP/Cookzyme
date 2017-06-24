package com.example.cookzyme.cookzyme;

/**
 * Created by ice on 24-Jun-17.
 */

public class TextT {
    @com.google.gson.annotations.SerializedName("text")
    private String mTexttoTranslate;
    @com.google.gson.annotations.SerializedName("idt")
    private String mIdT;
    @com.google.gson.annotations.SerializedName("from")
    private String mFrom;
    @com.google.gson.annotations.SerializedName("to")
    private String mTo;
    @com.google.gson.annotations.SerializedName("translateDone")
    private String mTranslateDone;
    @com.google.gson.annotations.SerializedName("completet")
    private boolean mCompleteT;
    public TextT(String TexttoTranslate, String id,String to) {
        this.setTexttoTranslate(TexttoTranslate);
        this.setId(id);
        this.setTo(to);
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof TextT && ((TextT) o).mIdT == mIdT;
    }

    public void setTo(String to) {
        this.mTo = to;
    }

    public void setId(String id) {
        this.mIdT = id;
    }

    public void setTexttoTranslate(String texttoTranslate) {
        this.mTexttoTranslate = texttoTranslate;
    }

    public String getmTranslateDone() {
        return mTranslateDone;
    }
}