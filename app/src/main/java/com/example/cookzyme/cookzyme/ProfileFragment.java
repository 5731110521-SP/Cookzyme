package com.example.cookzyme.cookzyme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import com.example.cookzyme.cookzyme.database.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

import android.support.v4.app.FragmentTransaction;

import com.example.cookzyme.cookzyme.customAdapter.customAdapterGrid;


public class ProfileFragment extends Fragment {

    public static BitmapDrawable[] arrImg ;
    public static Posts[] arrMyPosts ;

    private static TextView username,cookingNum,followersNum,followingNum;
    private static ImageView headerPic;
    private static CircleImageView userPic;
    private MobileServiceTable<Users> mUsers;
    private MobileServiceTable<Posts> mPosts;
    private MobileServiceClient mClient;
    private View headerView;
    private List<Users> Users;
    private List<Posts> Posts;
    private URL newurl2 , newurl3 ,newurl4;
    public static BitmapDrawable myProfilePic;
    private BitmapDrawable ob3,ob4;
    private GridViewWithHeaderAndFooter gridView;
    ProgressBar progressBarProfile;
    public static String myUsername,myEmail;
    public static boolean ready;
    public static List<Integer> carrot = new ArrayList<>();
    public static List<String> PostId = new ArrayList<>();

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("position", "mind_lover_soul@hotmail.com");
        fragment.setArguments(bundle);
        return fragment;
    }

    public ProfileFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

        String position = getArguments().getString("position");
        myEmail = position;

        progressBarProfile = (ProgressBar)rootView.findViewById(R.id.progressBarProfile);

        ready = false;

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

        //new CustomVisonTask().execute();


        //ALL handle click
        final Button followerButton = (Button) headerView.findViewById(R.id.followButton);
        followerButton.setText("EDIT PROFILE");

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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    ViewPostInProfile viewPost = new ViewPostInProfile();
                    Bundle bundle = new Bundle();
                    bundle.putString("position", String.valueOf(position));
                    bundle.putString("status" , "ProfileFragment");
                    viewPost.setArguments(bundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, viewPost);
                    transaction.addToBackStack(null);
                    transaction.commit();

            }
        });

//        Another
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
                        .field("email").eq(myEmail)
                        .execute()
                        .get();
                myUsername = Users.get(0).getName();
                Posts = mPosts
                        .where()
                        .field("email").eq(myEmail)
                        .execute()
                        .get();
                newurl2 = new URL(Users.get(0).getPath());
                Bitmap mIcon_val2 = BitmapFactory.decodeStream(newurl2.openConnection().getInputStream());
                myProfilePic = new BitmapDrawable(getResources(), mIcon_val2);

                newurl3 = new URL(Users.get(0).getBgPath());
                Bitmap mIcon_val3 = BitmapFactory.decodeStream(newurl3.openConnection().getInputStream());
                ob3 = new BitmapDrawable(getResources(), mIcon_val3);

                arrImg = new BitmapDrawable[Posts.size()];
                arrMyPosts = new Posts[Posts.size()];
                for(int i =0; i < Posts.size(); i++){
                    newurl4 = new URL(Posts.get(i).getPath());
                    Bitmap mIcon_val4 = BitmapFactory.decodeStream(newurl4.openConnection().getInputStream());
                    ob4 = new BitmapDrawable(getResources(), mIcon_val4);
                    arrImg[i] = ob4;
                    PostId.add(Posts.get(i).getmId());
                    arrMyPosts[i] = new Posts(Posts.get(i).getEmail(),Posts.get(i).getPath(),Posts.get(i).description,Posts.get(i).food_name,Posts.get(i).like);
                }
                for(int i = 0; i< arrMyPosts.length; i++) {
                    List<LikePost> likePosts = HomeFeedFragment.mLikePost
                            .where().field("post_id").eq(PostId.get(i))
                            .and()
                            .field("like_email").eq(myEmail)
                            .execute().get();
                    if (likePosts.size() == 0) {
                        carrot.add(R.drawable.carrot_grey);
                    } else {
                        carrot.add(R.drawable.carrot);
                    }
                }
                ready = true;
                System.out.println("---PROFILE---");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            progressBarProfile.setVisibility(View.GONE);

            username = (TextView) headerView.findViewById(R.id.username);
            username.setText(myUsername);

            cookingNum = (TextView) headerView.findViewById(R.id.cookingNum);
            cookingNum.setText(String.valueOf(Posts.size()));

            followersNum = (TextView) headerView.findViewById(R.id.followersNum);
            followersNum.setText(String.valueOf(Users.get(0).getFollower()));

            followingNum = (TextView) headerView.findViewById(R.id.followingNum);
            followingNum.setText(String.valueOf(Users.get(0).getFollowing()));

            userPic = (CircleImageView ) headerView.findViewById(R.id.userPic);
            userPic.setImageDrawable(myProfilePic);

            headerPic = (ImageView) headerView.findViewById(R.id.headerPic);
            headerPic.setImageDrawable(ob3);

            gridView.setAdapter(new customAdapterGrid(getActivity(),arrImg));
        }
    }

}
