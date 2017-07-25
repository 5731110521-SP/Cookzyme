package com.example.cookzyme.cookzyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import com.example.cookzyme.cookzyme.customAdapter.customAdapterFeed;
import com.example.cookzyme.cookzyme.database.Follow;
import com.example.cookzyme.cookzyme.database.Posts;
import com.example.cookzyme.cookzyme.database.Users;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;

public class HomeFeedFragment extends Fragment {
    private MobileServiceClient mClient;
    public static ArrayList<String> followingEmail = new ArrayList<>();
    public static ArrayList<BitmapDrawable> followingPic = new ArrayList<>();
    public static ArrayList<String> fromMenu= new ArrayList<>();
    public static ArrayList<BitmapDrawable> foodPic= new ArrayList<>();
    public static ArrayList<String> caption=new ArrayList<>();
    public static ArrayList<Integer> carrot=new ArrayList<>();
    public static ArrayList<Integer> likeNum=new ArrayList<>();
    public static ArrayList<Integer> commentNum = new ArrayList<>();
    public static List<Posts> allMyFollowingPost;
    public static List<String> PostId = new ArrayList<>();
    public static List<String> path = new ArrayList<>();
    public static List<String> Email = new ArrayList<>();
    private View rootView;
    public static ListView listView;
    public static customAdapterFeed adapter;
    private MobileServiceTable<Follow> mFollowers;
    private MobileServiceTable<Users> mUsers;
    public static MobileServiceTable<Posts> mPosts;
    CallbackManager callbackManager;

    public static String myProfilepic;
    public static String myEmail;
    public static String myFirstName;
    public static String myLastName;
    private  int eiei;

    public static HomeFeedFragment newInstance() {
        HomeFeedFragment fragment = new HomeFeedFragment();
        return fragment;
    }

    public HomeFeedFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.home_feed_fragment, container, false);

        callbackManager = CallbackManager.Factory.create();

        try {
            mClient = new MobileServiceClient("https://cookzymeapp.azurewebsites.net", getActivity().getApplicationContext() );
            mFollowers = mClient.getTable(Follow.class);
            mUsers = mClient.getTable(Users.class);
            mPosts = mClient.getTable(Posts.class);
            listView = (ListView) rootView.findViewById(R.id.listviewFeed);

            new CustomVisonTask().execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mFollowers = mClient.getTable(Follow.class);
        mUsers = mClient.getTable(Users.class);
        mPosts = mClient.getTable(Posts.class);
        listView = (ListView) rootView.findViewById(R.id.listviewFeed);

        new CustomVisonTask().execute();

        adapter = new customAdapterFeed(getContext(), followingEmail, followingPic, fromMenu,foodPic,caption,carrot,likeNum,commentNum);

        //listview

        //click item on listview
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> l, View view, int position, long id) {
//                eiei = position;
//                System.out.println("111111111111111111111111111");
//                ImageView imageViewUserPic = (ImageView) view.findViewById(R.id.userPic);
//                imageViewUserPic.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        System.out.println(eiei);
//                        System.out.println("22222222222222222222");
//                    }
//                });
//            }
//        });


        return rootView;
    }

       private class CustomVisonTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... bitmaps) {
//            LoginManager.getInstance().logInWithReadPermissions(HomeFeedFragment.this, Arrays.asList("user_friends", "email", "public_profile"));
//
//            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//                @Override
//                public void onSuccess(LoginResult loginResult) {
//                    findMyEmail(loginResult);
//                }
//
//                @Override
//                public void onCancel() {
//
//                }
//
//                @Override
//                public void onError(FacebookException error) {
//
//                }
//            });
            try {
                List<Follow> followingEmailna = mFollowers
                        .where()
                        .field("email").eq("Khamint@hotmail.com")
                        .select("following_email")
                        .execute()
                        .get();
                List<String> allMyFollowing = new ArrayList<>();
                allMyFollowing.add("Khamint@hotmail.com");
                for(int k = 0; k<followingEmailna.size(); k++) {allMyFollowing.add(followingEmailna.get(k).following_email);}
                for (int i = 0; i < allMyFollowing.size(); i++) {
                    List<Users> Usersna = mUsers
                            .where()
                            .field("email").eq(allMyFollowing.get(i))
                            .execute()
                            .get();
                    URL newurl = new URL(Usersna.get(0).path);
                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    BitmapDrawable ob = new BitmapDrawable(getResources(), mIcon_val);
                    List<Posts> Postsna = mPosts
                            .where()
                            .field("email").eq(allMyFollowing.get(i))
                            .execute()
                            .get();
                    allMyFollowingPost = new ArrayList<>();
                    for (int j = 0; j < Postsna.size(); j++) {
                        Email.add(Usersna.get(0).email);
                        followingEmail.add(Usersna.get(0).name);
                        followingPic.add(ob);
                        allMyFollowingPost.add(Postsna.get(j));
                        String nameoffood = Postsna.get(j).food_name;
                        String pathofpost = Postsna.get(j).path;
                        path.add(pathofpost);
                        fromMenu.add(nameoffood);
                        URL newurl2 = new URL(pathofpost);
                        Bitmap mIcon_val2 = BitmapFactory.decodeStream(newurl2.openConnection().getInputStream());
                        BitmapDrawable ob2 = new BitmapDrawable(getResources(), mIcon_val2);
                        foodPic.add(ob2);
                        caption.add(Postsna.get(j).description);
                        carrot.add(R.drawable.carrot_grey);
                        likeNum.add(allMyFollowingPost.get(j).getLike());
                        PostId.add(allMyFollowingPost.get(j).getmId());
                        commentNum.add(5);
                    }
                }
                adapter = new customAdapterFeed(getContext(), followingEmail, followingPic, fromMenu,
                        foodPic, caption, carrot, likeNum, commentNum);
                System.out.println("---HOME FEED---");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            rootView.findViewById(R.id.progressBarFeed).setVisibility(View.GONE);
            listView.setAdapter(adapter);
        }
    }

    private void findMyEmail(final LoginResult loginResult)
    {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Log.i("Response",response.toString());

                            myEmail = response.getJSONObject().getString("email");
                            myFirstName = response.getJSONObject().getString("first_name");
                            myLastName = response.getJSONObject().getString("last_name");
                            String gender = response.getJSONObject().getString("gender");

                            com.facebook.Profile profile = com.facebook.Profile.getCurrentProfile();
                            if (com.facebook.Profile.getCurrentProfile()!=null)
                            {
                                myProfilepic = "" + com.facebook.Profile.getCurrentProfile().getProfilePictureUri(500, 500);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private class CustomVisonTask2 extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... bitmaps) {

            return null;
        }

        protected void onPostExecute(Void result) {

        }
    }
}

