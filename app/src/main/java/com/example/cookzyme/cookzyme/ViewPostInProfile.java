package com.example.cookzyme.cookzyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookzyme.cookzyme.ExpandableHeightGridView;
import com.example.cookzyme.cookzyme.customAdapter.customAdapterGrid;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class ViewPostInProfile extends Fragment {
    private static TextView username,fromMenu,caption,likeNum,commentNum;
    private static ImageView userPic,foodPic,carrot;

    public static ViewPostInProfile newInstance() {
        ViewPostInProfile fragment = new ViewPostInProfile();
        return fragment;
    }

    public ViewPostInProfile() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.singlerow_feed, container, false);   // some thing here

        // Changes height of singlerowFeed
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.linear);
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layout.setLayoutParams(params);

        // get position of grid from ProfileFragment
        String position = getArguments().getString("position");

        while (!ProfileFragment.ready){
        }
        //ALL set
        userPic = (ImageView) rootView.findViewById(R.id.userPic);
        userPic.setImageDrawable(ProfileFragment.myProfilePic);
        username = (TextView) rootView.findViewById(R.id.username);
        username.setText(ProfileFragment.myUsername);
        fromMenu = (TextView) rootView.findViewById(R.id.fromMenu);
        fromMenu.setText(ProfileFragment.arrMyPosts[Integer.parseInt(position)].food_name);
        foodPic = (ImageView) rootView.findViewById(R.id.foodPic);
        foodPic.setImageDrawable(ProfileFragment.arrImg[Integer.parseInt(position)] );
        caption = (TextView) rootView.findViewById(R.id.caption);
        caption.setText(ProfileFragment.arrMyPosts[Integer.parseInt(position)].description);
        carrot = (ImageView) rootView.findViewById(R.id.carrot);
        carrot.setImageResource(R.drawable.carrot_grey);
        likeNum = (TextView) rootView.findViewById(R.id.likeNum);
        likeNum.setText("155");
        commentNum = (TextView) rootView.findViewById(R.id.commentNum);
        commentNum.setText("266");



        return rootView;
    }

}
