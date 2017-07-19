package com.example.cookzyme.cookzyme;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.table.TableQueryCallback;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class HomeFeedFragment extends Fragment {
    private MobileServiceClient mClient;
    private ArrayList<String> followingEmail = new ArrayList<>();
    private ArrayList<BitmapDrawable> followingPic = new ArrayList<>();
    private ArrayList<String> fromMenu= new ArrayList<>();
    private ArrayList<BitmapDrawable> foodPic= new ArrayList<>();
    private ArrayList<String> caption=new ArrayList<>();
    private ArrayList<Integer> carrot=new ArrayList<>();
    private ArrayList<Integer> likeNum=new ArrayList<>();
    private ArrayList<Integer> commentNum = new ArrayList<>();

    private View rootView;
    private ListView listView;
    private customAdapterFeed adapter;
    private customAdapterFeed adapter2;
    private Thread a;
    private MobileServiceTable<Follow> mFollowers;
    private MobileServiceTable<Users> mUsers;
    private MobileServiceTable<Posts> mPosts;
    private boolean ready;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         rootView = inflater.inflate(R.layout.home_feed_fragment, container, false);

        try {
            mClient = new MobileServiceClient("https://cookzymeapp.azurewebsites.net", getActivity().getApplicationContext() );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mFollowers = mClient.getTable(Follow.class);
        mUsers = mClient.getTable(Users.class);
        mPosts = mClient.getTable(Posts.class);

        listView = (ListView) rootView.findViewById(R.id.listviewFeed);

        a = new Thread(new Runnable() {
            private ArrayList<String> followingEmail2 = new ArrayList<>();
            private ArrayList<BitmapDrawable> followingPic2 = new ArrayList<>();
            private ArrayList<String> fromMenu2 = new ArrayList<>();
            private ArrayList<BitmapDrawable> foodPic2 = new ArrayList<>();
            private ArrayList<String> caption2 =new ArrayList<>();
            private ArrayList<Integer> carrot2 =new ArrayList<>();
            private ArrayList<Integer> likeNum2 =new ArrayList<>();
            private ArrayList<Integer> commentNum2 = new ArrayList<>();

            @Override
            public void run() {
                try {
                    List<Follow> followingEmailna = mFollowers
                            .where()
                            .field("email").eq("khamint@hotmail.com")
                            .select("following_email")
                            .execute()
                            .get();
                    System.out.println("---STEP 01---");
                    List<String> allMyFollowing  = new ArrayList<>();
                    for(int i = 0; i<followingEmailna.size(); i++){
                        allMyFollowing.add( followingEmailna.get(i).following_email);
                        List<Users> Usersna = mUsers
                                .where()
                                .field("email").eq(allMyFollowing.get(i))
                                .execute()
                                .get();
                        URL newurl = new URL(Usersna.get(0).path);
                        Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                        BitmapDrawable ob = new BitmapDrawable(getResources(), mIcon_val);
                        followingEmail2.add(Usersna.get(0).name);
                        followingPic2.add(ob);
                        System.out.println("---STEP 02---");
                        List<Posts> Postsna = mPosts
                                .where()
                                .field("email").eq(allMyFollowing.get(i))
                                .execute()
                                .get();
                        List<Posts> allMyFollowingPost = new ArrayList<>();
                        for(int j =0; j<Postsna.size(); j++) {
                            allMyFollowingPost.add(Postsna.get(i));
                            String nameoffood = Postsna.get(i).food_name;
                            String pathofpost = Postsna.get(i).path;
                            fromMenu2.add(nameoffood);
                            URL newurl2 = new URL(pathofpost);
                            Bitmap mIcon_val2 = BitmapFactory.decodeStream(newurl2.openConnection().getInputStream());
                            BitmapDrawable ob2 = new BitmapDrawable(getResources(), mIcon_val2);
                            foodPic2.add(ob2);
                            caption2.add(Postsna.get(i).description);
                            System.out.println("---STEP 03---");
                            carrot2.add(R.drawable.carrot_grey);
                            likeNum2.add(2);
                            commentNum2.add(5);
                            adapter2 = new customAdapterFeed(getContext(), followingEmail2, followingPic2, fromMenu2,
                                    foodPic2,caption2,carrot2,likeNum2,commentNum2);
                            adapter = adapter2;
                        }

                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a.start();

        BitmapDrawable eiei = new BitmapDrawable();
        followingEmail.add("CooKzyMe");
        followingPic.add(eiei);
        fromMenu.add("CooKzyMe");
        foodPic.add(eiei);
        caption.add("CooKzeMe");
        carrot.add(R.drawable.carrot_grey);
        likeNum.add(0);
        commentNum.add(0);

        adapter = new customAdapterFeed(getContext(), followingEmail, followingPic, fromMenu,foodPic,caption,carrot,likeNum,commentNum);

        //listview

        listView.setAdapter(adapter);

        //click item on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //if(arg2==0){
//                recipe.name=name_list.get(arg2);
//                Intent in = new Intent(getActivity(), viewRecipeHome.class);
//                startActivity(in);
                /*}else{
                    recipe.index=arg2;
                    Intent in = new Intent(recipe.this, viewRecipeSuperstore.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                }*/
                setnaka(adapter);
                followingEmail.clear();
                followingPic.clear();
                fromMenu.clear();
                foodPic.clear();
                caption.clear();
                carrot.clear();
                likeNum.clear();
                commentNum.clear();
            }
        });
        return rootView;
    }
    private void setnaka(customAdapterFeed adapter){
        this.listView.setAdapter(adapter);
    }

}
