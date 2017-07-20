package com.example.cookzyme.cookzyme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class Profile extends Fragment {
    private BitmapDrawable[] arrImg ;
    private static TextView username,cookingNum,followersNum,followingNum;
    private static ImageView headerPic;
    private static CircleImageView userPic;
    private MobileServiceTable<Users> mUsers;
    private MobileServiceTable<Posts> mPosts;
    private MobileServiceClient mClient;
    private View headerView;
    private List<Users> Users;
    private List<Posts> Posts;
    private URL newurl2 = null;
    private BitmapDrawable ob2;
    private URL newurl3 = null;
    private BitmapDrawable ob3;
    private URL newurl4 = null;
    private BitmapDrawable ob4;
    private GridViewWithHeaderAndFooter gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

//        normal but scroll within grid
//        GridView gridview = (GridView) rootView.findViewById(R.id.gridView);
//        gridview.setAdapter(new customAdapterGrid(getActivity(),arrImg));

        try {
            mClient = new MobileServiceClient("https://cookzymeapp.azurewebsites.net", getActivity().getApplicationContext() );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mUsers = mClient.getTable(Users.class);
        mPosts = mClient.getTable(Posts.class);

        gridView = ( GridViewWithHeaderAndFooter)       rootView.findViewById(R.id.gridView);
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        headerView = layoutInflater.inflate(R.layout.header, null);
        gridView.addHeaderView(headerView);

        new CustomVisonTask().execute();

        //ALL handle click
        headerView.findViewById(R.id.followButton).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        headerView.findViewById(R.id.challengeButton).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        headerView.findViewById(R.id.cooking_layout).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        headerView.findViewById(R.id.follower_layout).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            }
        });
        headerView.findViewById(R.id.following_layout).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        //reduce scroll of grid view = can not scroll
//        gridview.setOnTouchListener(new View.OnTouchListener(){
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return event.getAction() == MotionEvent.ACTION_MOVE;
//            }
//
//        });

        //remove scroll bar
//        gridview.setVerticalScrollBarEnabled(false);
//
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();


            }
        });

//        Another how to
//        ExpandableHeightGridView mGridView = (ExpandableHeightGridView)
//                getView().findViewById(R.id.gridView);
//        mGridView.setExpanded(true);
//        customAdapterGrid adapter = new customAdapterGrid(getActivity(),arrImg);
//        mGridView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        return rootView;
    }

    private class CustomVisonTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(Void... bitmaps) {
            try {
                Users = mUsers
                        .where()
                        .field("email").eq("mind_lover_soul@hotmail.com")
                        .execute()
                        .get();
                Posts = mPosts
                        .where()
                        .field("email").eq("mind_lover_soul@hotmail.com")
                        .execute()
                        .get();
                newurl2 = new URL(Users.get(0).getPath());
                Bitmap mIcon_val2 = BitmapFactory.decodeStream(newurl2.openConnection().getInputStream());
                ob2 = new BitmapDrawable(getResources(), mIcon_val2);

                newurl3 = new URL(Users.get(0).getBgPath());
                Bitmap mIcon_val3 = BitmapFactory.decodeStream(newurl3.openConnection().getInputStream());
                ob3 = new BitmapDrawable(getResources(), mIcon_val3);

                arrImg = new BitmapDrawable[Posts.size()];
                for(int i =0; i < Posts.size(); i++){
                    newurl4 = new URL(Posts.get(i).getPath());
                    Bitmap mIcon_val4 = BitmapFactory.decodeStream(newurl4.openConnection().getInputStream());
                    ob4 = new BitmapDrawable(getResources(), mIcon_val4);
                    arrImg[i] = ob4;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            username = (TextView) headerView.findViewById(R.id.username);
            Profile.username.setText(Users.get(0).getName());

            cookingNum = (TextView) headerView.findViewById(R.id.cookingNum);
            Profile.cookingNum.setText(String.valueOf(Posts.size()));

            followersNum = (TextView) headerView.findViewById(R.id.followersNum);
            Profile.followersNum.setText(String.valueOf(Users.get(0).getFollower()));

            followingNum = (TextView) headerView.findViewById(R.id.followingNum);
            Profile.followingNum.setText(String.valueOf(Users.get(0).getFollowing()));

            userPic = (CircleImageView ) headerView.findViewById(R.id.userPic);
            userPic.setImageDrawable(ob2);

            headerPic = (ImageView) headerView.findViewById(R.id.headerPic);
            headerPic.setImageDrawable(ob3);

            gridView.setAdapter(new customAdapterGrid(getActivity(),arrImg));
        }
    }

}
