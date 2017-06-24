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
import android.widget.Button;
import android.widget.TextView;

public class Teach extends AppCompatActivity {

    int stepno=1;
    Food f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);

        TextView tool = (TextView)findViewById(R.id.toolbar_title);
        f = Splash.database.getArrayFood().get(recipe.index);
        tool.setText(f.getName());
        ((TextView)findViewById(R.id.textView1)).setText(f.getName());
        ((TextView)findViewById(R.id.textView2)).setText(f.getEnergy()+"");
        ((TextView)findViewById(R.id.textRank)).setText(f.getRank()+"");
        ((TextView)findViewById(R.id.textLike)).setText(f.getLike()+"");

        for (HowToCook x:Splash.database.getArrayHowToCook()) {
            if(f.getName().equals(x.getFoodName()) && stepno==x.getStepNo()){
                ((TextView)findViewById(R.id.textHow)).setText(x.getStepNo()+". "+x.getStep());
                stepno++;
                break;
            }
        }

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (HowToCook x:Splash.database.getArrayHowToCook()) {
                    if(f.getName().equals(x.getFoodName()) && stepno==x.getStepNo()){
                        ((TextView)findViewById(R.id.textHow)).setText(x.getStepNo()+". "+x.getStep());
                        stepno++;
                        break;
                    }
                }
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
                                        new AlertDialog.Builder(Teach.this);
                                builder.setTitle("Where are you");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selected = option[which];
                                        if(selected.equals("Home")) {
                                            Intent in = new Intent(Teach.this,Splash.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }else if(selected.equals("Superstore")) {
                                            Intent in = new Intent(Teach.this,Splash.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }
                                    }
                                });
                                builder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent in = new Intent(Teach.this,setting.class);
                                        startActivity(in);
                                        overridePendingTransition(0, 0);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            case R.id.setting:
                                Intent in = new Intent(Teach.this, setting.class);
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
                Intent in = new Intent(Teach.this, recipe.class);
                startActivity(in);
            }
        });

    }
}
