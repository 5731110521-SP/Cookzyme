package com.example.cookzyme.cookzyme.customAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.R;
import com.example.cookzyme.cookzyme.RefrigeratorSectionFragment;
import com.example.cookzyme.cookzyme.database.Ingredients;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Palida on 12-Jul-17.
 */

public class customAdapterRefrigerator extends ArrayAdapter<Ingredients> {
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
    RefrigeratorSectionFragment refrigeratorSectionFragment;

    public void setRefrigerator(ArrayList<Ingredients> refrigerator) {
        this.refrigerator = refrigerator;
        notifyDataSetChanged();
    }

    int mLayoutResourceId;

    public customAdapterRefrigerator(Context context, int layoutResourceId,RefrigeratorSectionFragment refrigeratorSectionFragment) {
        super(context, layoutResourceId);
        this.refrigeratorSectionFragment=refrigeratorSectionFragment;
        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    public customAdapterRefrigerator(Context context,int layoutResourceId,RefrigeratorSectionFragment refrigeratorSectionFragment, ArrayList<Ingredients> refrigerator) {
        super(context, layoutResourceId);
        this.mContext= context;
        this.refrigerator=refrigerator;
        this.refrigeratorSectionFragment=refrigeratorSectionFragment;
        this.mLayoutResourceId=layoutResourceId;
//        this.foodPic = new ArrayList<>();
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Ingredients currentItem = getItem(position);

        p=position;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mLayoutResourceId, parent, false);
        }

        newurl = null;
        try {
            newurl = new URL(currentItem.getPath());
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
        textViewIngredientName.setText(currentItem.getIngredient_name());
        ImageView imageViewSign = (ImageView)view.findViewById(R.id.sign);

        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        long expire = currentItem.getExpire().getTime()/(24*60*60*1000);
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
        if(currentItem.getAmount()==0){
            textViewNum.setVisibility(View.GONE);
            textViewPronoun.setVisibility(View.GONE);
        }else{
            textViewNum.setText(Double.toString(currentItem.getAmount()));
            textViewPronoun.setText(currentItem.getUnit());
        }

        view.findViewById(R.id.deleteIngredient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RefrigeratorSectionFragment activity = refrigeratorSectionFragment;
                activity.removeItem(currentItem);
            }
        });

        return view;
    }
}
