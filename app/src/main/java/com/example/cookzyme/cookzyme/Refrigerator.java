package com.example.cookzyme.cookzyme;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Refrigerator {
    ArrayList<Ingredients> ingredients;

    public Refrigerator(ArrayList<Ingredients> ingredients) {
        this.ingredients=ingredients;
    }
}
