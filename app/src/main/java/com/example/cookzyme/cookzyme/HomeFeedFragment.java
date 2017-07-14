package com.example.cookzyme.cookzyme;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeFeedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_feed_fragment, container, false);
//
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) rootView.findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setVisibility(View.GONE);
//        rootView.findViewById(R.id.toolbar3).setVisibility(View.GONE);
        // zone list view
        // sample food
        ArrayList<String> username_list = new ArrayList<>();
        ArrayList<Integer> userPic_list = new ArrayList<>();
        ArrayList<String> fromMenu_list = new ArrayList<>();
        ArrayList<Integer> foodPic_list = new ArrayList<>();
        ArrayList<String> caption_list = new ArrayList<>();
        ArrayList<Integer> carrot_list = new ArrayList<>();
        ArrayList<Integer> likeNum_list = new ArrayList<>();
        ArrayList<Integer> commentNum_list = new ArrayList<>();


//        for(int i=0;i<Splash.database.getArrayFood().size();i++){
//            name.add(Splash.database.getArrayFood().get(i).getName());
//            userPic_list.add(Splash.database.getArrayFood().get(i).getEnergy());
//            fromMenu_list.add(Splash.database.getArrayFood().get(i).getPath());
//            foodPic_list.add(Splash.database.getArrayFood().get(i).getRank());
//            like.add(Splash.database.getArrayFood().get(i).getLike());
//        }

        List<String> username = username_list;
        List<Integer> userPic = userPic_list;
        List<String> fromMenu=fromMenu_list;
        List<Integer> foodPic=foodPic_list;
        List<String> caption=caption_list;
        List<Integer> carrot=carrot_list;
        List<Integer> likeNum=likeNum_list;
        List<Integer> commentNum = commentNum_list;
        //listview
        final customAdapterFeed adapter =new customAdapterFeed(getContext(), username, userPic, fromMenu,foodPic,caption,carrot,likeNum,commentNum);

//            public customAdapterFeed(Context context, List<String> username,List<Integer> userPic, List<String> fromMenu, List<Integer> foodPic, List<String> caption, List<Integer> carrot, List<Integer> likeNum, List<Integer> commentNum) {

            ListView listView = (ListView) rootView.findViewById(R.id.listviewFeed);
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
            }
        });
        return rootView;
    }
}
