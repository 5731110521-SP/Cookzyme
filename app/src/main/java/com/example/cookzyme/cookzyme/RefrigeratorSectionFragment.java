package com.example.cookzyme.cookzyme;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class RefrigeratorSectionFragment extends Fragment {

    customAdapterRefrigerator customAdapterRefrigerator;
    ListView listView;
    FloatingActionButton addIngredient;
    FloatingActionButton cooking;
    static ArrayList<Ingredients> refrigerator;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.refrigerator_section_fragment, container, false);
        listView=(ListView) rootView.findViewById(R.id.refrigeratorListView);
        addIngredient=(FloatingActionButton) rootView.findViewById(R.id.addIngredient);
        cooking=(FloatingActionButton) rootView.findViewById(R.id.cooking);
        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), addIngredient.class);
                startActivityForResult(in,0);
            }
        });
        cooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDBHelper database = new SQLiteDBHelper(getContext());
                database.getReadableDatabase().execSQL("delete from "+ SQLiteDBHelper.REFRIGERATOR_TABLE_NAME);
                database.closeDB();
                refrigerator=null;
                listView.setAdapter(null);

            }
        });

        if(refrigerator==null){
            refrigerator=new ArrayList<>();

            SQLiteDBHelper database = new SQLiteDBHelper(getContext());
            refrigerator=database.getAllRefrigerator();
            database.closeDB();
                if(refrigerator.size()>0){
                    customAdapterRefrigerator =new customAdapterRefrigerator(rootView.getContext().getApplicationContext(),refrigerator);
                    listView.setAdapter(customAdapterRefrigerator);
                }
        }else{
            customAdapterRefrigerator =new customAdapterRefrigerator(rootView.getContext().getApplicationContext(),refrigerator);
            listView.setAdapter(customAdapterRefrigerator);
        }
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == getActivity().RESULT_OK) {
                SQLiteDBHelper database = new SQLiteDBHelper(getContext());
                refrigerator=database.getAllRefrigerator();
                database.closeDB();
                customAdapterRefrigerator =new customAdapterRefrigerator(rootView.getContext().getApplicationContext(),refrigerator);
                listView.setAdapter(customAdapterRefrigerator);
            }
        }
    }
}
