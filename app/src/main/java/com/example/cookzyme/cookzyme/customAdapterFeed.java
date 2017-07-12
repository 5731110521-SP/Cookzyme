package com.example.cookzyme.cookzyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Palida on 12-Jul-17.
 */

public class customAdapterFeed extends BaseAdapter {
    Context mContext;
    private List<String> username;
    private List<Integer> userPic;
    private List<String> fromMenu;
    private List<Integer> foodPic;
    private List<String> caption;
    private List<Integer> carrot;
    private List<Integer> likeNum;
    private List<Integer> commentNum;


    public customAdapterFeed(Context context, List<String> username,List<Integer> userPic, List<String> fromMenu, List<Integer> foodPic, List<String> caption, List<Integer> carrot, List<Integer> likeNum, List<Integer> commentNum) {
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

        TextView textViewUsername = (TextView)view.findViewById(R.id.userName);
        textViewUsername.setText(username.get(position));


        ImageView imageViewUserPic = (ImageView)view.findViewById(R.id.userPic);
        imageViewUserPic.setBackgroundResource(userPic.get(position));


        TextView textViewFromMenu = (TextView)view.findViewById(R.id.fromMenu);
        textViewFromMenu.setText(fromMenu.get(position));

        ImageView imageViewFoodPic = (ImageView)view.findViewById(R.id.foodPic);
        imageViewFoodPic.setBackgroundResource(foodPic.get(position));

        TextView textViewCaption = (TextView)view.findViewById(R.id.caption);
        textViewCaption.setText(caption.get(position));

        ImageView imageViewCarrot = (ImageView)view.findViewById(R.id.carrot);
        imageViewCarrot.setBackgroundResource(carrot.get(position));

        TextView textViewLikeNum = (TextView)view.findViewById(R.id.likeNum);
        textViewLikeNum.setText(Integer.toString(likeNum.get(position)));

        TextView textViewCommentNum = (TextView)view.findViewById(R.id.commentNum);
        textViewCommentNum.setText(Integer.toString(commentNum.get(position)));

        return view;
    }
}
