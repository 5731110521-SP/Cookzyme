package com.example.cookzyme.cookzyme.customAdapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.HomeFeedFragment;
import com.example.cookzyme.cookzyme.HomeTabFragment;
import com.example.cookzyme.cookzyme.ProfileFragment;
import com.example.cookzyme.cookzyme.ProfileFragment2;
import com.example.cookzyme.cookzyme.R;
import com.example.cookzyme.cookzyme.ViewPostInProfile;
import com.example.cookzyme.cookzyme.ViewRecipeFragment;
import com.example.cookzyme.cookzyme.database.Posts;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Palida on 12-Jul-17.
 */

public class customAdapterFeed extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> username;
    private ArrayList<BitmapDrawable> userPic;
    private ArrayList<String> fromMenu;
    private ArrayList<BitmapDrawable> foodPic;
    private ArrayList<String> caption;
    private ArrayList<Integer> carrot;
    private ArrayList<Integer> likeNum;
    private ArrayList<Integer> commentNum;
    private int index;

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
        return userPic.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View view, final ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null)
            view = mInflater.inflate(R.layout.singlerow_feed, parent, false);

            TextView textViewUsername = (TextView) view.findViewById(R.id.username);
            textViewUsername.setText(username.get(position));

            ImageView imageViewUserPic = (ImageView) view.findViewById(R.id.userPic);
            imageViewUserPic.setBackgroundDrawable(userPic.get(position));
            imageViewUserPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ProfileFragment2 Profile2 = new ProfileFragment2();

                    Bundle bundle = new Bundle();
                    bundle.putString("position", HomeFeedFragment.Email.get(position));
                    Profile2.setArguments(bundle);

                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameHomeSection, Profile2)
                            .addToBackStack(null)
                            .commit();
                }
            });

            TextView textViewFromMenu = (TextView) view.findViewById(R.id.fromMenu);
            textViewFromMenu.setText(fromMenu.get(position));
            textViewFromMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewRecipeFragment viewRecipe = new ViewRecipeFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("food_name", fromMenu.get(position) );
                    viewRecipe.setArguments(bundle);

                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameHomeSection, viewRecipe)
                            .addToBackStack(null)
                            .commit();
                }
            });

            ImageView imageViewFoodPic = (ImageView) view.findViewById(R.id.foodPic);
            imageViewFoodPic.setBackgroundDrawable(foodPic.get(position));

            TextView textViewCaption = (TextView) view.findViewById(R.id.caption);
            textViewCaption.setText(caption.get(position));

            ImageView imageViewCarrot = (ImageView) view.findViewById(R.id.carrot);
            imageViewCarrot.setBackgroundResource(carrot.get(position));
            imageViewCarrot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = position;
                    if(HomeFeedFragment.carrot.get(position) == R.drawable.carrot){
                        HomeFeedFragment.carrot.set(position,R.drawable.carrot_grey);
                        HomeFeedFragment.likeNum.set(position,HomeFeedFragment.likeNum.get(position)-1);
                        notifyDataSetChanged();
                        new CustomVisonTask().execute();
                    }
                    else {
                        HomeFeedFragment.carrot.set(position, R.drawable.carrot);
                        HomeFeedFragment.likeNum.set(position,HomeFeedFragment.likeNum.get(position)+1);
                        notifyDataSetChanged();
                        new CustomVisonTask().execute();
                    }
                }
            });

            TextView textViewLikeNum = (TextView) view.findViewById(R.id.likeNum);
            textViewLikeNum.setText(Integer.toString(likeNum.get(position)));

            TextView textViewCommentNum = (TextView) view.findViewById(R.id.commentNum);
            textViewCommentNum.setText(Integer.toString(commentNum.get(position)));

        return view;
    }

    private class CustomVisonTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... bitmaps) {
            try {
                HomeFeedFragment.mPosts
                            .update(new Posts(HomeFeedFragment.Email.get(index),
                                    HomeFeedFragment.path.get(index), HomeFeedFragment.caption.get(index), HomeFeedFragment.fromMenu.get(index),
                                    HomeFeedFragment.likeNum.get(index),
                                    HomeFeedFragment.PostId.get(index)))
                            .get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            System.out.println("UPDATE SUCCESSED");
        }
    }
}

