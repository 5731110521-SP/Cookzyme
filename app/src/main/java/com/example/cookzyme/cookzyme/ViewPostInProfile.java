package com.example.cookzyme.cookzyme;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class ViewPostInProfile extends Fragment {



    public static ViewPostInProfile newInstance() {
        ViewPostInProfile fragment = new ViewPostInProfile();
        return fragment;
    }

    public ViewPostInProfile() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.singlerow_feed, container, false);   // some thing here
        // Gets linearlayout
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.linear);
        // Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        // Changes the height and width to the specified *pixels*
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layout.setLayoutParams(params);



        return rootView;
    }
}
