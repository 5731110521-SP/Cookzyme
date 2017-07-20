package com.example.cookzyme.cookzyme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasIngredients;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class viewRecipeHome extends AppCompatActivity {
    private Foods f ;
    private MobileServiceClient mClient;
    private MobileServiceTable<Foods> mFoods;
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

        for (Foods x:Splash.database.getArrayFood()
             ) {
            if(x.getFood_name().equals(recipe.name)){
                f=x;
                break;
            }
        }

        TextView tool = (TextView)findViewById(R.id.toolbar_title);
        tool.setText(f.getFood_name());
//        try {
//            URL newurl2 = null;
//            newurl2 = new URL(f.getPath());
//            Bitmap mIcon_val2 = BitmapFactory.decodeStream(newurl2.openConnection().getInputStream());
//            BitmapDrawable ob2 = new BitmapDrawable(getResources(), mIcon_val2);
//            ((ImageView)findViewById(R.id.imageView1)).setBackgroundDrawable(ob2);;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //BitmapDrawable eiei = new BitmapDrawable();
        //((ImageView)findViewById(R.id.imageView1)).setBackgroundDrawable(eiei);
        ((TextView)findViewById(R.id.textView1)).setText(f.getFood_name());
        ((TextView)findViewById(R.id.textView2)).setText(f.getEnergy()+" kcal");
        ((TextView)findViewById(R.id.textLike)).setText(f.getLike()+"");

        String in="";
        for (HasIngredients x:Splash.database.getArrayHasIngredients()) {
            if(f.getFood_name().equals(x.getFoodName())){
                in=in+x.getIngredientName()+"\n";
            }
        }
        ((TextView)findViewById(R.id.textIngre)).setText(in);


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
                Intent in = new Intent(viewRecipeHome.this, recipe.class);
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
                        .where()
                        .field("food_name").eq(recipe.name)
                        .execute()
                        .get();
                List<Foods> allFoods = foodna;


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {

        }
    }

}
