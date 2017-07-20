package com.example.cookzyme.cookzyme.customAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Palida on 17-Jul-17.
 */

public class customAdapterGrid  extends BaseAdapter {
    private  Integer[] arrImg;
    private Context mContext;

    public customAdapterGrid(Context context, Integer[] arrImg) {
        this.mContext = context;
        this.arrImg = arrImg;
    }

    public int getCount() {
        return this.arrImg.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(12, 12, 12, 12);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(arrImg[position]);

        return imageView;
    }

}
