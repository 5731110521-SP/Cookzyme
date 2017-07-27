package com.example.cookzyme.cookzyme.database;

import android.os.Parcel;
import android.os.Parcelable;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

import java.util.Date;

/**
 * Created by mintra nankongnaeb on 7/17/2017.
 */

public class HasIngredient implements Parcelable{
    public String food_name;
    public String ingredient_name;
    public double amount;
    private String unit;

    protected HasIngredient(Parcel in) {
        food_name = in.readString();
        ingredient_name = in.readString();
        amount = in.readDouble();
        unit = in.readString();
        mId = in.readString();
    }

    public static final Creator<HasIngredient> CREATOR = new Creator<HasIngredient>() {
        @Override
        public HasIngredient createFromParcel(Parcel in) {
            return new HasIngredient(in);
        }

        @Override
        public HasIngredient[] newArray(int size) {
            return new HasIngredient[size];
        }
    };

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

    public HasIngredient(String food_name, String ingredient_name, double amount) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(food_name);
        dest.writeString(ingredient_name);
        dest.writeDouble(amount);
        dest.writeString(unit);
        dest.writeString(mId);
    }
}
