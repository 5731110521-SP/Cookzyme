package com.example.cookzyme.cookzyme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
            ((ImageView)findViewById(R.id.imageView)).setImageBitmap(photo);
            bitmaps.add(photo);
            run2 r = new run2(photo);
            Thread t= new Thread(r);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textView.setText(r.getAns2());
            tag.clear();
            tag.add(r.getAns2());
            System.out.println(" ---------------- " + tag.size());
        }
    }
}

