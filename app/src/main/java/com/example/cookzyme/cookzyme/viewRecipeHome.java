package com.example.cookzyme.cookzyme;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class viewRecipeHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_home);

        TextView tool = (TextView)findViewById(R.id.toolbar_title);
        tool.setText(recipe.name);
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
                                            Intent in = new Intent(viewRecipeHome.this,Splash.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }else if(selected.equals("Superstore")) {
                                            Intent in = new Intent(viewRecipeHome.this,Splash.class);
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
                Intent in = new Intent(viewRecipeHome.this, recipe.class);
                startActivity(in);
            }
        });
        //like button
        findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // like++
            }
        });


    }
}
