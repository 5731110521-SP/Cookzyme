package com.example.cookzyme.cookzyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Palida on 12-Jul-17.
 */

public class customAdapterRefrigerator extends BaseAdapter {
    Context mContext;
    private List<String> foodPic;
    private List<String> ingredientName;
    private List<Integer> sign;
    private List<Integer> num;
    private List<String> pronoun;
    private ArrayList<Ingredients> refrigerator;

    public customAdapterRefrigerator(Context context, List<String> foodPic, List<String> ingredientName, List<Integer> sign, List<Integer> num, List<String> pronoun) {
        this.mContext= context;
        this.foodPic = foodPic;
        this.ingredientName = ingredientName;
        this.sign = sign;
        this.num = num;
        this.pronoun = pronoun;
    }

    public customAdapterRefrigerator(Context context, ArrayList<Ingredients> refrigerator) {
        this.mContext= context;
        this.refrigerator=refrigerator;
    }

    public int getCount() {
        return refrigerator.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.singlerow_refrigerator, parent, false);

//        ImageView imageViewFoodPic = (ImageView)view.findViewById(R.id.foodPic);
//        imageViewFoodPic.setBackgroundResource(foodPic.get(position));

        TextView textViewIngredientName = (TextView)view.findViewById(R.id.ingredientName);
        textViewIngredientName.setText(refrigerator.get(position).getIngredient_name());

//        ImageView imageViewSign = (ImageView)view.findViewById(R.id.sign);
//        imageViewSign.setBackgroundResource(sign.get(position));

        TextView textViewNum = (TextView)view.findViewById(R.id.num);
        textViewNum.setText(Integer.toString(refrigerator.get(position).getAmount()));

        TextView textViewPronoun = (TextView)view.findViewById(R.id.pronoun);
        textViewPronoun.setText(refrigerator.get(position).getUnit());

        return view;
    }
}
