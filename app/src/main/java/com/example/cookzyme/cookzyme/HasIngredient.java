package com.example.cookzyme.cookzyme;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

import java.util.Date;

/**
 * Created by mintra nankongnaeb on 7/17/2017.
 */

public class HasIngredient {
    public String food_name;
    public String ingredient_name;
    public int amount;
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @com.google.gson.annotations.SerializedName("id")
    private String mId;
    public String getId() { return mId; }
    public final void setId(String id) { mId = id; }

    public HasIngredient(String food_name, String ingredient_name, int amount) {
        this.food_name = food_name;
        this.ingredient_name = ingredient_name;
        this.amount = amount;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
