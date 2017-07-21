package com.example.cookzyme.cookzyme.ref;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cookzyme.cookzyme.R;
import com.example.cookzyme.cookzyme.customAdapter.customAdapterGrid;

public class letmetry extends AppCompatActivity {


    String[] Cmd = {"View Full", "Send Photo", "Shared Photo", "Delete Photo"};
    Integer[] arrImg = {R.drawable.egg, R.drawable.icon, R.drawable.egg, R.drawable.icon, R.drawable.egg, R.drawable.icon, R.drawable.egg};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);
//
//
//        GridView gridview = (GridView) findViewById(R.id.gridView);
//        gridview.setAdapter(new customAdapterGrid(this,arrImg));
//
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(letmetry.this, "" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}