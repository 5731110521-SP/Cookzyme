package com.example.cookzyme.cookzyme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
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

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.io.EOFException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeTabFragment extends Fragment {

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<Integer> cal = new ArrayList<>();
    private ArrayList<BitmapDrawable> pic = new ArrayList<>();
    private ArrayList<Integer> like = new ArrayList<>();
    private customAdapter adapter;
    private MobileServiceClient mClient;
    private MobileServiceTable<Foods> mFoods;

    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_recipe, container, false);

        try {
            mClient = new MobileServiceClient("https://cookzymeapp.azurewebsites.net", getActivity().getApplicationContext() );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mFoods = mClient.getTable(Foods.class);

        //listview
        new HomeTabFragment.CustomVisonTask().execute();

        listView = (ListView) rootView.findViewById(R.id.listview1);

        //click item on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //if(arg2==0){
                recipe.name = name.get(arg2);
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
    private class CustomVisonTask extends AsyncTask<Void,Void,Void> {
        private ArrayList<String> name2 = new ArrayList<>();
        private ArrayList<Integer> cal2 = new ArrayList<>();
        private ArrayList<BitmapDrawable> pic2 = new ArrayList<>();
        private ArrayList<Integer> like2 = new ArrayList<>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... bitmaps) {
            try {
                List<Foods> foodna = mFoods
                        .execute()
                        .get();
                List<Foods> allFoods = foodna;
                for(int i = 0 ;i < allFoods.size(); i++){
                    name2.add(allFoods.get(i).getFood_name());
                    cal2.add(allFoods.get(i).getEnergy());
                    URL newurl = new URL(allFoods.get(i).getPath());
                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    BitmapDrawable ob = new BitmapDrawable(getResources(), mIcon_val);
                    pic2.add(ob);
                    like2.add(allFoods.get(i).getLike());
                }
                adapter = new customAdapter(getContext(), name2, cal2, pic2,like2);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            listView.setAdapter(adapter);
        }
    }
}
