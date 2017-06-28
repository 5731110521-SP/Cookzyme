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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseList extends AppCompatActivity {
    public static String name="";
    public static int index=0;
    List<String> name_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_list);

        //navi bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.camera:
                                final String []option = {"Home","Superstore"};
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(ChooseList.this);
                                builder.setTitle("Where are you");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selected = option[which];
                                        if(selected.equals("Home")) {
                                            Intent in = new Intent(ChooseList.this,camera.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }else if(selected.equals("Superstore")) {
                                            Intent in = new Intent(ChooseList.this,cameraShopping.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }
                                    }
                                });
                                builder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent in = new Intent(ChooseList.this,recipe.class);
                                        startActivity(in);
                                        overridePendingTransition(0, 0);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            case R.id.setting:
                                Intent in = new Intent(ChooseList.this, setting.class);
                                startActivity(in);
                                overridePendingTransition(0, 0);
                                break;
                        }
                        return true;
                    }
                });

        // zone list view
        // sample food
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> cal = new ArrayList<>();
        ArrayList<Integer> pic = new ArrayList<>();
        ArrayList<Integer> rank = new ArrayList<>();
        ArrayList<Integer> like = new ArrayList<>();

        for(int i=0;i<Splash.database.getArrayFood().size();i++){
            for (String x:camera.recipeName
                 ) {
                if(x.equals(Splash.database.getArrayFood().get(i).getName())) {
                    name.add(Splash.database.getArrayFood().get(i).getName());
                    cal.add(Splash.database.getArrayFood().get(i).getEnergy());
                    pic.add(Splash.database.getArrayFood().get(i).getPath());
                    rank.add(Splash.database.getArrayFood().get(i).getRank());
                    like.add(Splash.database.getArrayFood().get(i).getLike());
                    break;
                }
            }
        }

        name_list = name;
        List<Integer> cal_list = cal;
        List<Integer> pic_list = pic;
        List<Integer> rank_list = rank;
        List<Integer> like_list = like;
        //listview
        final customAdapter adapter =new customAdapter(getApplicationContext(), name_list, cal_list, pic_list,rank_list,like_list);

        ListView listView = (ListView)findViewById(R.id.listview1);
        listView.setAdapter(adapter);
        //click item on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //if(arg2==0){
                recipe.name=name_list.get(arg2);
                Intent in = new Intent(ChooseList.this, viewRecipeHome.class);
                startActivity(in);
                overridePendingTransition(0, 0);
                /*}else{
                    recipe.index=arg2;
                    Intent in = new Intent(recipe.this, viewRecipeSuperstore.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);
                }*/
            }
        });

    }


}
