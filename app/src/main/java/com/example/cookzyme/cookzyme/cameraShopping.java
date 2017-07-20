package com.example.cookzyme.cookzyme;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class cameraShopping extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private TextView textView;
    private Bitmap photo;
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private ArrayList<String> tag = new ArrayList<String>();
    public static ArrayList<String> recipeName = new ArrayList<String>();
    String nameFood[] = {"Pork Ball","ก๋วยเตี๋ยวราดหน้าหมูสับ","ข้าวต้มหมูทรงเครื่อง","ข้าวผัดกุ้ง","ข้าวมันไก่","ข้าวไก่กระเทียม","น้ำพริกอ่องอกไก่","ผัดกะเพราไก่","ฟักทองผัดไข่","สุกี้กุ้งสดแห้ง","แกงจืดไข่","ไก่ทอดหาดใหญ่","ไข่เจียว เห็ดเข็มทอง",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //navi bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    Intent in;
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.camera:
                                final String []option = {"Home","Superstore"};
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(cameraShopping.this);
                                builder.setTitle("Where are you");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selected = option[which];
                                        if(selected.equals("Home")) {
                                            Intent in = new Intent(cameraShopping.this,camera.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                        }else if(selected.equals("Superstore")) {
                                            Intent in = new Intent(cameraShopping.this,cameraShopping.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                        }
                                    }
                                });
                                builder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        //Intent in = new Intent(cameraShopping.this,recipe.class);
                                        //startActivity(in);
                                        overridePendingTransition(0, 0);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            case R.id.setting:
                                in = new Intent(cameraShopping.this, setting.class);
                                startActivity(in);
                                overridePendingTransition(0, 0);
                                break;
                            case R.id.recipe:
                                in = new Intent(cameraShopping.this, recipe.class);
                                startActivity(in);
                                overridePendingTransition(0, 0);
                                break;
                        }
                        return true;
                    }
                });

        this.imageView = (ImageView)this.findViewById(R.id.imageView);
        this.textView = (TextView)this.findViewById(R.id.toolbar_title);
        Button photoButton = (Button) this.findViewById(R.id.btnCamera);
        Button photoButton2 = (Button) this.findViewById(R.id.btnCooking);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        int sum = 0;
        photoButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag.size()==0)return;
                for(int j=0;j<Splash.database.getArrayHasIngredients().size();j++) {
                    if (tag.get(0).equals(Splash.database.getArrayHasIngredients().get(j).getIngredientName())) {
                        recipeName.add(Splash.database.getArrayHasIngredients().get(j).getFoodName());
                    }
                }
                bitmaps.clear();
                camera.recipeName=recipeName;
                Intent in = new Intent(cameraShopping.this, ChooseList.class);
                startActivity(in);

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
//            ((ImageView)findViewById(R.id.imageView)).setImageBitmap(photo);
//            bitmaps.add(photo);

            ((ImageView)findViewById(R.id.imageView)).setImageBitmap(photo);
            bitmaps.add(photo);

//            System.out.println(" ---------------- " + tag.size());

        }
    }
}

