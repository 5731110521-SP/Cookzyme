package com.example.cookzyme.cookzyme;

import android.os.AsyncTask;

import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasIngredient;
import com.example.cookzyme.cookzyme.database.Ingredients;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Panuvit on 20/7/2560.
 */

public class Query {
    public void getCanCookFoodHome(){

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                List<Ingredients> refrigerator = new ArrayList<Ingredients>();
                List<Foods> canCook = new ArrayList<Foods>();
                MobileServiceTable<Foods> mFoods = AzureServiceAdapter.getInstance().getClient().getTable(Foods.class);
                MobileServiceTable<HasIngredient> mHasIngredient = AzureServiceAdapter.getInstance().getClient().getTable(HasIngredient.class);
                try {
                    List<Foods> mAllFoods = mFoods.execute().get();
                    List<Foods> allFoods = mAllFoods;
                    List<HasIngredient> mFoodHasIngredient = mHasIngredient.execute().get();
                    List<HasIngredient> foodHasIngredient = mFoodHasIngredient;
                    for (Foods f:allFoods
                         ) {
                        Boolean hasAllIngredients = true;
                        for (HasIngredient fh:foodHasIngredient
                             ) {
                            if(f.getFood_name().equals(fh.getFood_name())){

                                if(!refrigerator.contains(fh.getIngredient_name())){
                                    hasAllIngredients = false;
                                    break;
                                }else {
                                    if(refrigerator.get(refrigerator.indexOf(fh.getIngredient_name())).getIngredient_name().equals(fh.getIngredient_name())){
                                        if(refrigerator.get(refrigerator.indexOf(fh.getIngredient_name())).getUnit().equals(fh.getUnit())){
                                            if(refrigerator.get(refrigerator.indexOf(fh.getIngredient_name())).getAmount()<fh.getAmount()){
                                                hasAllIngredients = false;
                                                break;
                                            }
                                        }
                                    }

                                }

                            }
                        }
                        if(hasAllIngredients){
                            canCook.add(f);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
    public void getCanCookFoodStore(){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                List<Ingredients> ingredientList = new ArrayList<Ingredients>();
                List<Foods> canCook = new ArrayList<Foods>();
                MobileServiceTable<Foods> mFoods = AzureServiceAdapter.getInstance().getClient().getTable(Foods.class);
                MobileServiceTable<HasIngredient> mHasIngredient = AzureServiceAdapter.getInstance().getClient().getTable(HasIngredient.class);
                try {
                    List<Foods> mAllFoods = mFoods.execute().get();
                    List<Foods> allFoods = mAllFoods;
                    List<HasIngredient> mFoodHasIngredient = mHasIngredient.execute().get();
                    List<HasIngredient> foodHasIngredient = mFoodHasIngredient;
                    List<String> ingredientsInList = new ArrayList<String>();
                    for (Ingredients i: ingredientList
                            ) {
                        ingredientsInList.add(i.getIngredient_name());
                    }
                    for (Foods f:allFoods
                            ) {
                        Boolean hasIngredients = false;
                        for (HasIngredient fh:foodHasIngredient
                                ) {
                            if(f.getFood_name().equals(fh.getFood_name())){

                                if(ingredientsInList.contains(fh.getIngredient_name())){
                                    hasIngredients = true;
                                    break;
                                }

                            }
                        }
                        if(hasIngredients){
                            canCook.add(f);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
