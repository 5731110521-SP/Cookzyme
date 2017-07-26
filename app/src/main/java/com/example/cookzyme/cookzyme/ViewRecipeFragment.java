package com.example.cookzyme.cookzyme;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.transition.ChangeBounds;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.cookzyme.cookzyme.customAdapter.customAdapterIngredient;
import java.util.ArrayList;
import android.support.v4.app.FragmentTransaction;

import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasIngredient;
import com.example.cookzyme.cookzyme.database.Recipes;
import java.net.URL;
import java.util.List;

public class ViewRecipeFragment extends Fragment {

    private int stepNum;
    private ArrayList<String> stepD = new ArrayList<>();
    private ArrayList<String> ingredients = new ArrayList<>() ;
    private ArrayList<Integer> signal = new ArrayList<>();
    private TextView directText,ingre;
    private List<Foods> food;
    private List<HasIngredient> hasIngredient;
    private List<Recipes> directions;
    private String directiont = "";
    private BitmapDrawable ob2;
    private View rootView,directView,header;
    private FloatingActionButton cooking;
    ProgressBar progressBarHome;

    public static ViewRecipeFragment newInstance() {

        ViewRecipeFragment fragment = new ViewRecipeFragment();
        return fragment;
    }

    public ViewRecipeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_recipe, container, false);
        progressBarHome=(ProgressBar)rootView.findViewById(R.id.progressBarHome);
        stepNum = 0;

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        header = layoutInflater.inflate(R.layout.header_section, null);
        directView = layoutInflater.inflate(R.layout.direct_text, null);
        new CustomVisonTask().execute();
        rootView.setClickable(true);
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
                food = AzureServiceAdapter.getInstance().getClient().getTable(Foods.class)
                        .where()
                        .field("food_name").eq(getArguments().getString("food_name"))
                        .execute()
                        .get();
                URL newurl2 = null;
                newurl2 = new URL(food.get(0).getPath());
                Bitmap mIcon_val2 = BitmapFactory.decodeStream(newurl2.openConnection().getInputStream());
                ob2 = new BitmapDrawable(getResources(), mIcon_val2);

                hasIngredient = AzureServiceAdapter.getInstance().getClient().getTable(HasIngredient.class)
                        .where()
                        .field("food_name").eq(getArguments().getString("food_name"))
                        .execute()
                        .get();
                directions = AzureServiceAdapter.getInstance().getClient().getTable(Recipes.class)
                        .where()
                        .field("food_name").eq(getArguments().getString("food_name"))
                        .execute()
                        .get();
                for(int i = 0; i<hasIngredient.size(); i++){
                    ingredients.add(hasIngredient.get(i).getIngredient_name()+ "\n");
                    signal.add(R.drawable.warning);
                }
                for (Recipes r:directions
                     ) {
                    directiont += r.getStepNo()+". "+r.getStep()+"\n\n";
                    stepD.add(r.getStepNo()+". "+r.getStep());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        protected void onPostExecute(Void result) {

            ((android.support.v7.widget.Toolbar) header.findViewById(R.id.Toolbar)).setTitle(food.get(0).getFood_name());
            ((ImageView)header.findViewById(R.id.foodPic)).setBackgroundDrawable(ob2);
            ((TextView)header.findViewById(R.id.foodName)).setText(food.get(0).getFood_name());
            ((TextView)header.findViewById(R.id.calorie)).setText(food.get(0).getEnergy()+" kcal");
            ((TextView)header.findViewById(R.id.textLike)).setText(food.get(0).getLike()+"");
//            ((TextView)rootView.findViewById(R.id.ingredient)).setText(in);
            final customAdapterIngredient adapterIngre = new customAdapterIngredient(getActivity().getApplicationContext(), ingredients,signal);
            ListView listViewIngre = (ListView) rootView.findViewById(R.id.listviewIngre);
            listViewIngre.setAdapter(adapterIngre);
//        listViewDirect.setEnabled(false)

        System.out.println("Sed na");
        cooking = (FloatingActionButton) rootView.findViewById(R.id.cooking);
        cooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DuringCooking during = new DuringCooking();

                Bundle bundle = new Bundle();
                bundle.putInt("stepNum", stepNum);
                bundle.putString("foodName",food.get(0).getFood_name());
                bundle.putStringArrayList("stepD",stepD);
                during.setArguments(bundle);

                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameViewRecipe1, during);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

            listViewIngre.addFooterView(directView);

            directText = (TextView) directView.findViewById(R.id.directText);
            directText.setText(directiont);


            ingre = (TextView) rootView.findViewById(R.id.directText);
            listViewIngre.addHeaderView(header);
            progressBarHome.setVisibility(View.GONE);
        }
    }
}
