package com.example.cookzyme.cookzyme.customAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.R;
import com.example.cookzyme.cookzyme.database.Ingredients;

import java.net.URL;
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
    Bitmap mIcon_val;
    URL newurl;
    int p;

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
//        this.foodPic = new ArrayList<>();
    }

    public void setFoodPic(List<BitmapDrawable> foodPic) {
        this.foodPic = foodPic;
    }

    public int getCount() {
        return refrigerator.size();
    }

    public Object getItem(int position) {
        return refrigerator.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        p=position;

        if(view == null)
            view = mInflater.inflate(R.layout.singlerow_refrigerator, parent, false);

        newurl = null;
        try {
            newurl = new URL(refrigerator.get(position).getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mIcon_val = null;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BitmapDrawable ob = new BitmapDrawable(view.getResources(), mIcon_val);

        ImageView imageViewFoodPic = (ImageView)view.findViewById(R.id.foodPic);
        imageViewFoodPic.setBackgroundDrawable(ob);
//        ImageView imageViewFoodPic = (ImageView)view.findViewById(R.id.foodPic);
//        imageViewFoodPic.setBackgroundDrawable(foodPic.get(position));

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
        TextView textViewPronoun = (TextView)view.findViewById(R.id.pronoun);
        if(refrigerator.get(position).getAmount()==0){
            textViewNum.setVisibility(View.GONE);
            textViewPronoun.setVisibility(View.GONE);
        }else{
            textViewNum.setText(Integer.toString(refrigerator.get(position).getAmount()));
            textViewPronoun.setText(refrigerator.get(position).getUnit());
        }



        return view;
    }
}
