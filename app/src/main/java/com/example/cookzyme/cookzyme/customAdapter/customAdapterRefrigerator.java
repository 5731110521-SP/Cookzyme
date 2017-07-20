package com.example.cookzyme.cookzyme.customAdapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.R;
import com.example.cookzyme.cookzyme.database.Ingredients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Palida on 12-Jul-17.
 */

public class customAdapterRefrigerator extends BaseAdapter {
    Context mContext;
    private List<BitmapDrawable> foodPic;
    private List<String> ingredientName;
    private List<Integer> sign;
    private List<Integer> num;
    private List<String> pronoun;
    private ArrayList<Ingredients> refrigerator;

    public customAdapterRefrigerator(Context context, List<BitmapDrawable> foodPic, List<String> ingredientName, List<Integer> sign, List<Integer> num, List<String> pronoun) {
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
        this.foodPic = new ArrayList<>();

    }

    public void setFoodPic(List<BitmapDrawable> foodPic) {
        this.foodPic = foodPic;
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


        ImageView imageViewFoodPic = (ImageView)view.findViewById(R.id.foodPic);
        imageViewFoodPic.setBackgroundDrawable(foodPic.get(position));

        TextView textViewIngredientName = (TextView)view.findViewById(R.id.ingredientName);
        textViewIngredientName.setText(refrigerator.get(position).getIngredient_name());
        ImageView imageViewSign = (ImageView)view.findViewById(R.id.sign);

        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        long expire = refrigerator.get(position).getExpire().getTime()/(24*60*60*1000);
        long today = date.getTime()/(24*60*60*1000);
        if(expire-today<0){
            imageViewSign.setImageResource(R.drawable.warning_red);
        }else if(expire-today<3){
            imageViewSign.setImageResource(R.drawable.warning);
        }
//        ImageView imageViewSign = (ImageView)view.findViewById(R.id.sign);
//        imageViewSign.setBackgroundResource(sign.get(position));

        TextView textViewNum = (TextView)view.findViewById(R.id.num);
        textViewNum.setText(Integer.toString(refrigerator.get(position).getAmount()));

        TextView textViewPronoun = (TextView)view.findViewById(R.id.pronoun);
        textViewPronoun.setText(refrigerator.get(position).getUnit());

        return view;
    }
}
