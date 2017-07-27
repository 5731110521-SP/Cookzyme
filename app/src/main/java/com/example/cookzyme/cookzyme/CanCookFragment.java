package com.example.cookzyme.cookzyme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.cookzyme.cookzyme.customAdapter.customAdapter;
import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasIngredient;
import com.example.cookzyme.cookzyme.database.Ingredients;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class CanCookFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lv;
    private ArrayList<String> food_name;
    ProgressBar progressBarHome;
    public CanCookFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CanCookFragment newInstance() {
        CanCookFragment fragment = new CanCookFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_can_cook, container, false);
        progressBarHome=(ProgressBar)view.findViewById(R.id.progressBarHome);
        lv = (ListView) view.findViewById(R.id.listview1);
        new CanCookTask().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ViewRecipeFragment viewRecipe = new ViewRecipeFragment();

                Bundle bundle = new Bundle();
                bundle.putString("food_name",food_name.get(arg2));
                viewRecipe.setArguments(bundle);

                FragmentTransaction transaction =  getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.cancook, viewRecipe);
                transaction.addToBackStack(null);
                transaction.commit();
                /*}else{
                    recipe.index=arg2;
                    Intent in = new Intent(recipe.this, viewRecipeSuperstore.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                }*/
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

    private class CanCookTask extends AsyncTask<Void, Void, Void> {
        customAdapter adapter;
        public CanCookTask() {
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter(adapter);
            progressBarHome.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            List<Ingredients> refrigerator = RefrigeratorSectionFragment.refrigerator;
            ArrayList<Ingredients> refrigeratorSum = new ArrayList<Ingredients>();
            ArrayList<String> ingreInReSum = new ArrayList<String>();
            for (int i =0;i<refrigerator.size();i++) {
                if(ingreInReSum.contains(refrigerator.get(i).getIngredient_name())){
                    refrigeratorSum.get(ingreInReSum.indexOf(refrigerator.get(i).getIngredient_name())).setAmount(refrigeratorSum.get(ingreInReSum.indexOf(refrigerator.get(i).getIngredient_name())).getAmount()+refrigerator.get(i).getAmount());
                } else {
                    ingreInReSum.add(refrigerator.get(i).getIngredient_name());
                    refrigeratorSum.add(new Ingredients(refrigerator.get(i)));
                }
            }
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
                for (Ingredients i: refrigeratorSum
                        ) {
                    ingredientsInFridge.add(i.getIngredient_name());
                }
                for (Foods f : allFoods
                        ) {
                    boolean hasAllIngredients = true;
                    for (HasIngredient fh : foodHasIngredient
                            ) {
                        if (f.getFood_name().equals(fh.getFood_name())) {
                            if (!ingredientsInFridge.contains(fh.getIngredient_name())) {
                                hasAllIngredients = false;
                                break;
                            } else {
                                if (refrigeratorSum.get(ingredientsInFridge.indexOf(fh.getIngredient_name())).getIngredient_name().equals(fh.getIngredient_name())) {
                                    if (refrigeratorSum.get(ingredientsInFridge.indexOf(fh.getIngredient_name())).getUnit().equals(fh.getUnit())) {
                                        if (refrigeratorSum.get(ingredientsInFridge.indexOf(fh.getIngredient_name())).getAmount() < fh.getAmount()) {
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
                adapter = new customAdapter(getActivity(),name,cal,pic,like);
                food_name = (ArrayList<String>) name;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
