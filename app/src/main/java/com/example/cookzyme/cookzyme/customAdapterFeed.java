package com.example.cookzyme.cookzyme;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Palida on 12-Jul-17.
 */

public class customAdapterFeed extends BaseAdapter {
    Context mContext;
    private ArrayList<String> username;
    private ArrayList<BitmapDrawable> userPic;
    private ArrayList<String> fromMenu;
    private ArrayList<BitmapDrawable> foodPic;
    private ArrayList<String> caption;
    private ArrayList<Integer> carrot;
    private ArrayList<Integer> likeNum;
    private ArrayList<Integer> commentNum;


    public customAdapterFeed(Context context, ArrayList<String> username,ArrayList<BitmapDrawable> userPic, ArrayList<String> fromMenu, ArrayList<BitmapDrawable> foodPic, ArrayList<String> caption, ArrayList<Integer> carrot, ArrayList<Integer> likeNum, ArrayList<Integer> commentNum) {
        this.mContext= context;
        this.username = username;
        this.userPic = userPic;
        this.fromMenu = fromMenu;
        this.foodPic = foodPic;
        this.caption = caption;
        this.carrot = carrot;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
    }

    public int getCount() {
        return username.size();
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
            view = mInflater.inflate(R.layout.singlerow_feed, parent, false);

            TextView textViewUsername = (TextView) view.findViewById(R.id.userName);
            textViewUsername.setText(username.get(position));


            ImageView imageViewUserPic = (ImageView) view.findViewById(R.id.userPic);
            imageViewUserPic.setBackgroundDrawable(userPic.get(position));


            TextView textViewFromMenu = (TextView) view.findViewById(R.id.fromMenu);
            textViewFromMenu.setText(fromMenu.get(position));

            ImageView imageViewFoodPic = (ImageView) view.findViewById(R.id.foodPic);
            imageViewFoodPic.setBackgroundDrawable(foodPic.get(position));

            TextView textViewCaption = (TextView) view.findViewById(R.id.caption);
            textViewCaption.setText(caption.get(position));

            ImageView imageViewCarrot = (ImageView) view.findViewById(R.id.carrot);
            imageViewCarrot.setBackgroundResource(carrot.get(position));

            TextView textViewLikeNum = (TextView) view.findViewById(R.id.likeNum);
            textViewLikeNum.setText(Integer.toString(likeNum.get(position)));

            TextView textViewCommentNum = (TextView) view.findViewById(R.id.commentNum);
            textViewCommentNum.setText(Integer.toString(commentNum.get(position)));
        return view;
    }
}
