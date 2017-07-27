package com.example.cookzyme.cookzyme.customAdapter;

import com.example.cookzyme.cookzyme.R;
import com.example.cookzyme.cookzyme.ViewRecipeFragment;
import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasCategory;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class categoryHorizontalScroll extends HorizontalScrollView {

    private MobileServiceClient mClient;
    private MobileServiceTable<HasCategory> mHasCategorys;
    private MobileServiceTable<Foods> mFoods;
    LinearLayout llParent;
    private ArrayList<BitmapDrawable> pic = new ArrayList<>();
    ProgressBar progressBar;
    private int i ;
    Fragment fragment;
    ArrayList<String> foodnames=new ArrayList<>();

    public categoryHorizontalScroll(Fragment fragment, Context context, String category) {
        super(context);

        this.fragment = fragment;

        try {
            mClient = new MobileServiceClient("https://cookzymeapp.azurewebsites.net", context.getApplicationContext() );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        llParent = new LinearLayout(context);
        addView(llParent);

        progressBar = new ProgressBar(context);
        llParent.addView(progressBar);
        ViewGroup.LayoutParams params = progressBar.getLayoutParams();
        params.height = 210*3;
        progressBar.setLayoutParams(params);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        new categoryTask(context).execute(category);
    }


    private class categoryTask extends AsyncTask<String,Void,ArrayList<Foods>> {
        Context context;
        public categoryTask(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Foods> doInBackground(String... input) {
            try {
                ArrayList<Foods> categoryHasFoods = new ArrayList<>();
                mHasCategorys = mClient.getTable(HasCategory.class);
                List<HasCategory> hasCategoryList = mHasCategorys
                        .where().field("category").eq(input[0])
                        .execute()
                        .get();
                List<HasCategory> allHasCategorys = hasCategoryList;
                mFoods = mClient.getTable(Foods.class);
                for(int i = 0 ;i < allHasCategorys.size(); i++){
                    List<Foods> foodsList = mFoods
                            .where().field("food_name").eq(allHasCategorys.get(i).getFood_name())
                            .execute()
                            .get();
                    List<Foods> allFoods = foodsList;
                    categoryHasFoods.add(allFoods.get(0));
                }
                for(int i =0;i<categoryHasFoods.size();i++){
                    URL newurl = new URL(categoryHasFoods.get(i).getPath());
                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    BitmapDrawable ob = new BitmapDrawable(context.getResources(), mIcon_val);
                    pic.add(ob);
                }
                return categoryHasFoods;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(ArrayList<Foods> result) {
            for(i = 0 ;i < result.size(); i++){
                foodnames.add(result.get(i).getFood_name());
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout ll = new LinearLayout(context);
                ll.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ViewRecipeFragment viewRecipe = new ViewRecipeFragment();

                        Bundle bundle = new Bundle();
                        bundle.putString("food_name",   foodnames.get(i-1) );
                        viewRecipe.setArguments(bundle);

                        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameHomeSection, viewRecipe);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
                inflater.inflate(R.layout.singer_food_horizontal, ll, true);
                llParent.addView(ll);
                try {
                    ((LinearLayout)ll.getChildAt(0)).getChildAt(0).setBackgroundDrawable(pic.get(i));
                    ((TextView)((LinearLayout)((LinearLayout)((LinearLayout)ll.getChildAt(0)).getChildAt(1)).getChildAt(0)).getChildAt(0)).setText(result.get(i).getFood_name());
                    ((TextView)((LinearLayout)((LinearLayout)((LinearLayout)ll.getChildAt(0)).getChildAt(1)).getChildAt(0)).getChildAt(1)).setText(result.get(i).getEnergy()+" kcal");
                    ((TextView)((LinearLayout)((LinearLayout)ll.getChildAt(0)).getChildAt(1)).getChildAt(2)).setText(result.get(i).getLike()+"");
                    if(progressBar.getVisibility()==VISIBLE){
                        progressBar.setVisibility(GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
