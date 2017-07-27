package com.example.cookzyme.cookzyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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
import com.example.cookzyme.cookzyme.customAdapter.customAdapterFeed;
import com.example.cookzyme.cookzyme.customAdapter.customAdapterGrid;
import com.example.cookzyme.cookzyme.database.LikePost;
import com.example.cookzyme.cookzyme.database.Posts;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class ViewPostInProfile extends Fragment {
    private static TextView username,fromMenu,caption,likeNum,commentNum;
    private static ImageView userPic,foodPic,carrot;
    private int index;
    private String position;
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
        position = getArguments().getString("position");
        if(getArguments().getString("status") == "ProfileFragment2") {
            while (!ProfileFragment2.ready) {
            }
            //ALL set
            userPic = (ImageView) rootView.findViewById(R.id.userPic);
            userPic.setImageDrawable(ProfileFragment2.myProfilePic);

            username = (TextView) rootView.findViewById(R.id.username);
            username.setText(ProfileFragment2.myUsername);

            fromMenu = (TextView) rootView.findViewById(R.id.fromMenu);
            fromMenu.setText(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].food_name);

            foodPic = (ImageView) rootView.findViewById(R.id.foodPic);
            foodPic.setImageDrawable(ProfileFragment2.arrImg[Integer.parseInt(position)]);

            caption = (TextView) rootView.findViewById(R.id.caption);
            caption.setText(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].description);

            carrot = (ImageView) rootView.findViewById(R.id.carrot);
            carrot.setImageResource(ProfileFragment2.carrot.get(Integer.parseInt(position)));
            carrot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = Integer.parseInt(position);
                    if(ProfileFragment2.carrot.get(index) == R.drawable.carrot){
                        ProfileFragment2.carrot.set(index,R.drawable.carrot_grey);
                        ProfileFragment2.arrMyPosts[Integer.parseInt(position)].setLike(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].getLike()-1);
                        likeNum.setText(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].like + "");
                        carrot.setImageResource(ProfileFragment2.carrot.get(Integer.parseInt(position)));
                        new updateToLikePostDatabase().execute("");
                        new CustomVisonTask().execute();
                    }
                    else {
                        ProfileFragment2.carrot.set(index,R.drawable.carrot);
                        ProfileFragment2.arrMyPosts[Integer.parseInt(position)].setLike(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].getLike()+1);
                        likeNum.setText(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].like + "");
                        carrot.setImageResource(ProfileFragment2.carrot.get(Integer.parseInt(position)));
                        new updateToLikePostDatabase().execute("add");
                        new CustomVisonTask().execute();
                    }
                }
            });

            likeNum = (TextView) rootView.findViewById(R.id.likeNum);
            likeNum.setText(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].like + "");

            commentNum = (TextView) rootView.findViewById(R.id.commentNum);
            commentNum.setText("266");

        }else{
            while (!ProfileFragment.ready) {
            }
            userPic = (ImageView) rootView.findViewById(R.id.userPic);
            userPic.setImageDrawable(ProfileFragment.myProfilePic);

            username = (TextView) rootView.findViewById(R.id.username);
            username.setText(ProfileFragment.myUsername);

            fromMenu = (TextView) rootView.findViewById(R.id.fromMenu);
            fromMenu.setText(ProfileFragment.arrMyPosts[Integer.parseInt(position)].food_name);

            foodPic = (ImageView) rootView.findViewById(R.id.foodPic);
            foodPic.setImageDrawable(ProfileFragment.arrImg[Integer.parseInt(position)]);

            caption = (TextView) rootView.findViewById(R.id.caption);
            caption.setText(ProfileFragment.arrMyPosts[Integer.parseInt(position)].description);

            carrot = (ImageView) rootView.findViewById(R.id.carrot);
            carrot.setImageResource(ProfileFragment.carrot.get(Integer.parseInt(position)));
            carrot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = Integer.parseInt(position);
                    if (getArguments().getString("status") == "ProfileFragment") {
                        if (ProfileFragment.carrot.get(index) == R.drawable.carrot) {
                            ProfileFragment.carrot.set(index, R.drawable.carrot_grey);
                            ProfileFragment.arrMyPosts[Integer.parseInt(position)].setLike(ProfileFragment.arrMyPosts[Integer.parseInt(position)].getLike() - 1);
                            likeNum.setText(ProfileFragment.arrMyPosts[Integer.parseInt(position)].like + "");
                            carrot.setImageResource(ProfileFragment.carrot.get(Integer.parseInt(position)));
                            // new updateToLikePostDatabase().execute("");
                            // new CustomVisonTask().execute();
                        } else {
                            ProfileFragment.carrot.set(index, R.drawable.carrot);
                            ProfileFragment.arrMyPosts[Integer.parseInt(position)].setLike(ProfileFragment.arrMyPosts[Integer.parseInt(position)].getLike() + 1);
                            likeNum.setText(ProfileFragment.arrMyPosts[Integer.parseInt(position)].like + "");
                            carrot.setImageResource(ProfileFragment.carrot.get(Integer.parseInt(position)));
                            // new updateToLikePostDatabase().execute("add");
                            // new CustomVisonTask().execute();
                        }
                    } else {
                        if (ProfileFragment2.carrot.get(index) == R.drawable.carrot) {
                            ProfileFragment2.carrot.set(index, R.drawable.carrot_grey);
                            ProfileFragment2.arrMyPosts[Integer.parseInt(position)].setLike(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].getLike() - 1);
                            likeNum.setText(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].like + "");
                            carrot.setImageResource(ProfileFragment2.carrot.get(Integer.parseInt(position)));
                            // new updateToLikePostDatabase().execute("");
                            // new CustomVisonTask().execute();
                        } else {
                            ProfileFragment2.carrot.set(index, R.drawable.carrot);
                            ProfileFragment2.arrMyPosts[Integer.parseInt(position)].setLike(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].getLike() +1);
                            likeNum.setText(ProfileFragment2.arrMyPosts[Integer.parseInt(position)].like + "");
                            carrot.setImageResource(ProfileFragment2.carrot.get(Integer.parseInt(position)));
                            // new updateToLikePostDatabase().execute("add");
                            // new CustomVisonTask().execute();
                        }
                    }
                }
            });

            likeNum = (TextView) rootView.findViewById(R.id.likeNum);
            likeNum.setText(ProfileFragment.arrMyPosts[Integer.parseInt(position)].like + "");

            commentNum = (TextView) rootView.findViewById(R.id.commentNum);
            commentNum.setText("266");
        }
        return rootView;
    }

    private class CustomVisonTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... bitmaps) {
            if(getArguments().getString("status") == "ProfileFragment" ){
                try {
                    HomeFeedFragment.mPosts
                            .update(new Posts(ProfileFragment.arrMyPosts[index].getEmail(),
                                    ProfileFragment.arrMyPosts[index].getPath(),
                                    ProfileFragment.arrMyPosts[index].getDescription(),
                                    ProfileFragment.arrMyPosts[index].getFoodname(),
                                    ProfileFragment.arrMyPosts[index].getLike(),
                                    ProfileFragment.PostId.get(index)))
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    HomeFeedFragment.mPosts
                            .update(new Posts(ProfileFragment2.arrMyPosts[index].getEmail(),
                                    ProfileFragment2.arrMyPosts[index].getPath(),
                                    ProfileFragment2.arrMyPosts[index].getDescription(),
                                    ProfileFragment2.arrMyPosts[index].getFoodname(),
                                    ProfileFragment2.arrMyPosts[index].getLike(),
                                    ProfileFragment2.PostId.get(index)))
                            .get();
                    HomeFeedFragment.mLikePost
                            .insert(new LikePost(ProfileFragment.PostId.get(index),ProfileFragment.myEmail))
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            System.out.println("UPDATE SUCCESSED");
        }
    }

    private class updateToLikePostDatabase extends AsyncTask<String,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... add) {
            if(getArguments().getString("status") == "ProfileFragment") {
                if (add[0] == "add") {
                    try {
                        HomeFeedFragment.mLikePost
                                .insert(new LikePost(ProfileFragment.PostId.get(index), ProfileFragment.myEmail))
                                .get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    List<LikePost> likePosts2 = new ArrayList<>();
                    try {
                        List<LikePost> likePosts = HomeFeedFragment.mLikePost
                                .where()
                                .field("post_id").eq(ProfileFragment.PostId.get(index))
                                .and()
                                .field("like_email").eq(ProfileFragment.myEmail)
                                .execute()
                                .get();
                        likePosts2 = likePosts;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(likePosts2.get(0).getmId());
                        System.out.println(likePosts2.get(0).getPost_id());
                        System.out.println(likePosts2.get(0).getLike_email());
                        HomeFeedFragment.mLikePost
                                .delete(new LikePost(likePosts2.get(0).getmId(),likePosts2.get(0).getPost_id(),likePosts2.get(0).getLike_email()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else{
                if (add[0] == "add") {
                    try {
                        HomeFeedFragment.mLikePost
                                .insert(new LikePost(ProfileFragment2.PostId.get(index), ProfileFragment2.myEmail))
                                .get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    List<LikePost> likePosts2 = new ArrayList<>();
                    try {
                        List<LikePost> likePosts = HomeFeedFragment.mLikePost
                                .where()
                                .field("post_id").eq(ProfileFragment2.PostId.get(index))
                                .and()
                                .field("like_email").eq(ProfileFragment2.myEmail)
                                .execute()
                                .get();
                        likePosts2 = likePosts;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(likePosts2.get(0).getmId());
                        System.out.println(likePosts2.get(0).getPost_id());
                        System.out.println(likePosts2.get(0).getLike_email());
                        HomeFeedFragment.mLikePost
                                .delete(new LikePost(likePosts2.get(0).getmId(),likePosts2.get(0).getPost_id(),likePosts2.get(0).getLike_email()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            System.out.println("INSERT OR DELETE SUCCESSED");
        }
    }

}
