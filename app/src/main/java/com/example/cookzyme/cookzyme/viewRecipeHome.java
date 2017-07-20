package com.example.cookzyme.cookzyme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasIngredient;
import com.example.cookzyme.cookzyme.database.HasIngredients;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class viewRecipeHome extends AppCompatActivity {
    private Foods f ;
    private MobileServiceClient mClient;
    private MobileServiceTable<Foods> mFoods;
    private MobileServiceTable<HasIngredient> mHasIngredient;
    private List<Foods> food;
    private List<HasIngredient> hasIngredient;
    private BitmapDrawable ob2;
    private String in = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_home);

        try {
            mClient = new MobileServiceClient("https://cookzymeapp.azurewebsites.net", this );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mFoods = mClient.getTable(Foods.class);
        mHasIngredient = mClient.getTable(HasIngredient.class);

        new CustomVisonTask().execute();

        TextView tool = (TextView)findViewById(R.id.toolbar_title);
        tool.setText(HomeTabFragment.foodname);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        TextView tv1 = (TextView)findViewById(R.id.toolbar_title);
                        switch (item.getItemId()) {
                            case R.id.camera:
                                final String []option = {"Home","Superstore"};
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(viewRecipeHome.this);
                                builder.setTitle("Where are you");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selected = option[which];
                                        if(selected.equals("Home")) {
                                            Intent in = new Intent(viewRecipeHome.this,camera.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }else if(selected.equals("Superstore")) {
                                            Intent in = new Intent(viewRecipeHome.this,cameraShopping.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }
                                    }
                                });
                                builder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent in = new Intent(viewRecipeHome.this,setting.class);
                                        startActivity(in);
                                        overridePendingTransition(0, 0);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            case R.id.setting:
                                Intent in = new Intent(viewRecipeHome.this, setting.class);
                                startActivity(in);
                                overridePendingTransition(0, 0);
                                break;
                        }
                        return true;
                    }
                });
        //back button
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(viewRecipeHome.this, HomeTabFragment.class);
                startActivity(in);
            }
        });
        //share on facebook
        findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(viewRecipeHome.this, recipe.class);
                startActivity(in);
            }
        });
        //teach
        findViewById(R.id.teach).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(viewRecipeHome.this, Teach.class);
                startActivity(in);
            }
        });
        //like button
        findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                f.setLike(f.getLike()+1);
                ((TextView) findViewById(R.id.textLike)).setText(f.getLike()+"");
            }
        });


    }

    private class CustomVisonTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... bitmaps) {
            try {
                food = mFoods
                        .where()
                        .field("food_name").eq(HomeTabFragment.foodname)
                        .execute()
                        .get();
                URL newurl2 = null;
                newurl2 = new URL(food.get(0).getPath());
                Bitmap mIcon_val2 = BitmapFactory.decodeStream(newurl2.openConnection().getInputStream());
                ob2 = new BitmapDrawable(getResources(), mIcon_val2);

                hasIngredient = mHasIngredient
                        .where()
                        .field("food_name").eq(HomeTabFragment.foodname)
                        .execute()
                        .get();

                for(int i = 0; i<hasIngredient.size(); i++){
                    in += hasIngredient.get(i).getIngredient_name()+ "\n";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            ((ImageView)findViewById(R.id.imageView1)).setBackgroundDrawable(ob2);
            ((TextView)findViewById(R.id.textView1)).setText(food.get(0).getFood_name());
            ((TextView)findViewById(R.id.textView2)).setText(food.get(0).getEnergy()+" kcal");
            ((TextView)findViewById(R.id.textLike)).setText(food.get(0).getLike()+"");
            ((TextView)findViewById(R.id.textIngre)).setText(in);
        }
    }

}
