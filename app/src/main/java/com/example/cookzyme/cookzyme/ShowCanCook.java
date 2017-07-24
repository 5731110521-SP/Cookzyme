package com.example.cookzyme.cookzyme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cookzyme.cookzyme.customAdapter.customAdapter;
import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasIngredient;
import com.example.cookzyme.cookzyme.database.Ingredients;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShowCanCook extends Activity {
    private ListView lv;
    private ArrayList<String> food_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_can_cook);
        lv = (ListView) findViewById(R.id.listview1);
        new CanCookTask().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent in = new Intent(getApplicationContext(), viewRecipeHome.class);
                in.putExtra("food_name",food_name.get(arg2));
                startActivity(in);
                /*}else{
                    recipe.index=arg2;
                    Intent in = new Intent(recipe.this, viewRecipeSuperstore.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                }*/
            }
        });
    }
    private class CanCookTask extends AsyncTask<Void, Void, Void>{
        customAdapter adapter;
        public CanCookTask() {
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... params) {
            List<Ingredients> refrigerator = RefrigeratorSectionFragment.refrigerator;
            List<Foods> canCook = new ArrayList<Foods>();
            MobileServiceTable<Foods> mFoods = AzureServiceAdapter.getInstance().getClient().getTable(Foods.class);
            MobileServiceTable<HasIngredient> mHasIngredient = AzureServiceAdapter.getInstance().getClient().getTable(HasIngredient.class);
            try {
                List<Foods> allFoods = new ArrayList<Foods>();
                List<HasIngredient> foodHasIngredient = new ArrayList<HasIngredient>();
                int nResults;
                do {
                    int currentCount = allFoods.size();
                    List<Foods> pagedResults = mFoods
                            .skip(currentCount).top(500)
                            .execute().get();
                    nResults = pagedResults.size();
                    if (nResults > 0) {
                        allFoods.addAll(pagedResults);
                    }
                } while (nResults > 0);
                do {
                    int currentCount = foodHasIngredient.size();
                    List<HasIngredient> pagedResults = mHasIngredient
                            .skip(currentCount).top(500)
                            .execute().get();
                    nResults = pagedResults.size();
                    if (nResults > 0) {
                        foodHasIngredient.addAll(pagedResults);
                    }
                } while (nResults > 0);

                List<String> ingredientsInFridge = new ArrayList<String>();
                for (Ingredients i: refrigerator
                     ) {
                    ingredientsInFridge.add(i.getIngredient_name());
                }
                for (Foods f : allFoods
                        ) {
                    Boolean hasAllIngredients = true;
                    for (HasIngredient fh : foodHasIngredient
                            ) {
                        if (f.getFood_name().equals(fh.getFood_name())) {
                            if (!ingredientsInFridge.contains(fh.getIngredient_name())) {
                                hasAllIngredients = false;
                                break;
                            } else {
                                if (refrigerator.get(ingredientsInFridge.indexOf(fh.getIngredient_name())).getIngredient_name().equals(fh.getIngredient_name())) {
                                    if (refrigerator.get(ingredientsInFridge.indexOf(fh.getIngredient_name())).getUnit().equals(fh.getUnit())) {
                                        if (refrigerator.get(ingredientsInFridge.indexOf(fh.getIngredient_name())).getAmount() < fh.getAmount()) {
                                            hasAllIngredients = false;
                                            break;
                                        }
                                    }
                                }

                            }

                        }
                    }
                    if (hasAllIngredients) {
                        canCook.add(f);
                    }
                }

                List<String> name = new ArrayList<String>();
                List<Integer> cal = new ArrayList<Integer>();
                List<BitmapDrawable> pic = new ArrayList<BitmapDrawable>();
                List<Integer> like = new ArrayList<Integer>();
                for (Foods f:canCook){
                    name.add(f.getFood_name());
                    cal.add(f.getEnergy());
                    URL newurl = new URL(f.getPath());
                    Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                    BitmapDrawable ob = new BitmapDrawable(getResources(), mIcon_val);
                    pic.add(ob);
                    like.add(f.getLike());
                }
                adapter = new customAdapter(getApplicationContext(),name,cal,pic,like);
                food_name = (ArrayList<String>) name;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
