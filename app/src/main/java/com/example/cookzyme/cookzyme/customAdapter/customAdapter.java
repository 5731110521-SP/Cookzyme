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

import java.util.List;

/**
 * Created by Palid on 24-Jun-17.
 */

public class customAdapter extends BaseAdapter {
    Context mContext;
    private List<String> name;
    private List<Integer> cal;
    private List<BitmapDrawable> pic;
    private List<Integer> like;

    public customAdapter(Context context, List<String> name,List<Integer> cal, List<BitmapDrawable> pic,List<Integer> like) {
        this.mContext= context;
        this.name = name;
        this.cal = cal;
        this.pic = pic;
        this.like = like;
    }

    public int getCount() {
        return name.size();
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
            view = mInflater.inflate(R.layout.singlerow, parent, false);

        TextView textViewFoodName = (TextView)view.findViewById(R.id.foodName);
        textViewFoodName.setText(name.get(position));


        TextView textViewCalorie = (TextView)view.findViewById(R.id.calorie);
        textViewCalorie.setText(Integer.toString(cal.get(position))+" kcal");


        ImageView imageViewFoodPic = (ImageView)view.findViewById(R.id.foodPic);
        imageViewFoodPic.setBackgroundDrawable(pic.get(position));

        TextView textViewLike = (TextView)view.findViewById(R.id.textLike);
        textViewLike.setText(Integer.toString(like.get(position)));

        ImageView imageViewCarrot = (ImageView)view.findViewById(R.id.carrot);
        imageViewCarrot.setBackgroundResource(R.drawable.carrot_grey);

        ImageView imageViewStar = (ImageView)view.findViewById(R.id.star);
        imageViewStar.setBackgroundResource(R.drawable.star40);
        return view;
    }
}
