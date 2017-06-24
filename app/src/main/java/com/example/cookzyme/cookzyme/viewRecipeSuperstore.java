package com.example.cookzyme.cookzyme;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class viewRecipeSuperstore extends AppCompatActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_superstore);


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
                                        new AlertDialog.Builder(viewRecipeSuperstore.this);
                                builder.setTitle("Where are you");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selected = option[which];
                                        if(selected.equals("Home")) {
                                            Intent in = new Intent(viewRecipeSuperstore.this,Splash.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }else if(selected.equals("Superstore")) {
                                            Intent in = new Intent(viewRecipeSuperstore.this,Splash.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }
                                    }
                                });
                                builder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent in = new Intent(viewRecipeSuperstore.this,setting.class);
                                        startActivity(in);
                                        overridePendingTransition(0, 0);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            case R.id.setting:
                                Intent in = new Intent(viewRecipeSuperstore.this, setting.class);
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
                Intent in = new Intent(viewRecipeSuperstore.this, recipe.class);
                startActivity(in);
            }
        });
        //share on facebook
        findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(viewRecipeSuperstore.this, recipe.class);
                startActivity(in);
            }
        });
        //teach
        findViewById(R.id.teach).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(viewRecipeSuperstore.this, recipe.class);
                startActivity(in);
            }
        });
        //like button
        findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // like++
            }
        });
        //location button
        findViewById(R.id.location).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.textIngre);
                text.setText("Egg");
                ImageView image1 = (ImageView) dialog.findViewById(R.id.foodpic);
                image1.setImageResource(R.drawable.egg);

                ImageView image2 = (ImageView) dialog.findViewById(R.id.direct);
                image2.setImageResource(R.drawable.arrow);

                Button dialogButton = (Button) dialog.findViewById(R.id.cancel);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button dialogButton1 = (Button) dialog.findViewById(R.id.gotit);
                // if button is clicked, close the custom dialog
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(viewRecipeSuperstore.this, recipe.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                    }
                });

                dialog.show();
            }
        });
}
}
