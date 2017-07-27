package com.example.cookzyme.cookzyme;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.Space;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.customAdapter.categoryHorizontalScroll;
import com.example.cookzyme.cookzyme.customAdapter.customAdapter;
import com.example.cookzyme.cookzyme.database.Foods;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeTabFragment extends Fragment {
    public static String foodname;
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<Integer> cal = new ArrayList<>();
    private ArrayList<BitmapDrawable> pic = new ArrayList<>();
    private ArrayList<Integer> like = new ArrayList<>();
    private customAdapter adapter;
    private MobileServiceClient mClient;
    private MobileServiceTable<Foods> mFoods;

    private ListView listView;
    ProgressBar progressBarHome;

    public static HomeTabFragment newInstance() {
        return new HomeTabFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_tab_fragment, container, false);

        progressBarHome=(ProgressBar)rootView.findViewById(R.id.progressBarHome);
        progressBarHome.setVisibility(View.GONE);

//        try {
//            mClient = new MobileServiceClient("https://cookzymeapp.azurewebsites.net", getActivity().getApplicationContext() );
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        mFoods = mClient.getTable(Foods.class);

        LinearLayout llparent = (LinearLayout) rootView.findViewById(R.id.home_tab_ll);

        LinearLayout headerCategory = new LinearLayout(getContext());
        llparent.addView(headerCategory);
        headerCategory.setOrientation(LinearLayout.HORIZONTAL);
        headerCategory.setBackgroundColor(Color.WHITE);
        ViewGroup.LayoutParams paramsCategory = headerCategory.getLayoutParams();
        paramsCategory.height = 100;
        headerCategory.setLayoutParams(paramsCategory);
        headerCategory.setGravity(Gravity.CENTER_VERTICAL);
        TextView tvCategory = new TextView(getContext());
        headerCategory.addView(tvCategory);
        tvCategory.setText("Category");
        ViewGroup.LayoutParams paramstvCategory = tvCategory.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) paramstvCategory).leftMargin = 20;
        tvCategory.setLayoutParams(paramstvCategory);

        Space sp = new Space(getContext());
        headerCategory.addView(sp);
        sp.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        TextView tvCategorySeeAll = new TextView(getContext());
        headerCategory.addView(tvCategorySeeAll);
        tvCategorySeeAll.setText("See All");
        tvCategorySeeAll.setTextColor(getResources().getColor(R.color.colorButton));
        ViewGroup.LayoutParams paramstvCategorySeeAll = tvCategorySeeAll.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) paramstvCategorySeeAll).rightMargin = 20;
        tvCategorySeeAll.setLayoutParams(paramstvCategorySeeAll);

        LinearLayout header = new LinearLayout(getContext());
        llparent.addView(header);
        header.setBackgroundColor(Color.WHITE);
        ViewGroup.LayoutParams params = header.getLayoutParams();
        params.height = 80;
        header.setLayoutParams(params);
        header.setGravity(Gravity.CENTER_VERTICAL);
        TextView tv = new TextView(getContext());
        header.addView(tv);
        tv.setText("ข้าว");
        ViewGroup.LayoutParams paramstv = tv.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) paramstv).leftMargin = 20;
        tv.setLayoutParams(paramstv);

        categoryHorizontalScroll chsv = new categoryHorizontalScroll(this,getContext(),"ข้าว");
        llparent.addView(chsv);

        LinearLayout header2 = new LinearLayout(getContext());
        llparent.addView(header2);
        header2.setBackgroundColor(Color.WHITE);
        ViewGroup.LayoutParams params2 = header2.getLayoutParams();
        params2.height = 80;
        header2.setLayoutParams(params2);
        header2.setGravity(Gravity.CENTER_VERTICAL);
        TextView tv2 = new TextView(getContext());
        header2.addView(tv2);
        tv2.setText("กับข้าว");
        ViewGroup.LayoutParams paramstv2 = tv2.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) paramstv2).leftMargin = 20;
        tv2.setLayoutParams(paramstv2);

        categoryHorizontalScroll chsv2 = new categoryHorizontalScroll(this,getContext(),"กับข้าว");
        llparent.addView(chsv2);

        LinearLayout header3 = new LinearLayout(getContext());
        llparent.addView(header3);
        header3.setBackgroundColor(Color.WHITE);
        ViewGroup.LayoutParams params3 = header3.getLayoutParams();
        params3.height = 80;
        header3.setLayoutParams(params3);
        header3.setGravity(Gravity.CENTER_VERTICAL);
        TextView tv3 = new TextView(getContext());
        header3.addView(tv3);
        tv3.setText("เส้น");
        ViewGroup.LayoutParams paramstv3 = tv3.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) paramstv3).leftMargin = 20;
        tv3.setLayoutParams(paramstv3);

        categoryHorizontalScroll chsv3 = new categoryHorizontalScroll(this,getContext(),"เส้น");
        llparent.addView(chsv3);

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
                List<Foods> foodna = mFoods
                        .execute()
                        .get();
                List<Foods> allFoods = foodna;
                for(int i = 0 ;i < allFoods.size(); i++){
                    name.add(allFoods.get(i).getFood_name());
                    cal.add(allFoods.get(i).getEnergy());
                    URL newurl = new URL(allFoods.get(i).getPath());
                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    BitmapDrawable ob = new BitmapDrawable(getResources(), mIcon_val);
                    pic.add(ob);
                    like.add(allFoods.get(i).getLike());
                }
                adapter = new customAdapter(getContext(), name, cal, pic,like);
                System.out.println("---TAB---");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            progressBarHome.setVisibility(View.GONE);
            listView.setAdapter(adapter);
        }
    }
}
