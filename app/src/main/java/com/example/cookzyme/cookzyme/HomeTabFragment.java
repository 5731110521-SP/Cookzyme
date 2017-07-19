package com.example.cookzyme.cookzyme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_recipe, container, false);
//
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) rootView.findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setVisibility(View.GONE);
//        rootView.findViewById(R.id.toolbar3).setVisibility(View.GONE);
        // zone list view
        // sample food
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> cal = new ArrayList<>();
        ArrayList<BitmapDrawable> pic = new ArrayList<>();
        ArrayList<Integer> rank = new ArrayList<>();
        ArrayList<Integer> like = new ArrayList<>();

        for(int i=0;i<Splash.database.getArrayFood().size();i++){

        }

        final List<String> name_list = name;
        List<Integer> cal_list = cal;
        List<BitmapDrawable> pic_list = pic;
        List<Integer> rank_list = rank;
        List<Integer> like_list = like;
        //listview
        final customAdapter adapter =new customAdapter(getContext(), name_list, cal_list, pic_list,like_list);

        ListView listView = (ListView) rootView.findViewById(R.id.listview1);
        listView.setAdapter(adapter);
        //click item on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //if(arg2==0){
                recipe.name=name_list.get(arg2);
                Intent in = new Intent(getActivity(), viewRecipeHome.class);
                startActivity(in);
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
