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


public class camera extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private TextView textView;
    private Bitmap photo;
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private ArrayList<String> tag = new ArrayList<String>();
    public static ArrayList<String> recipeName = new ArrayList<String>();
    String nameFood[] = {"Pork Ball","ก๋วยเตี๋ยวราดหน้าหมูสับ","ข้าวต้มหมูทรงเครื่อง"};
    int numofingre[] = {6,9,5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        this.imageView = (ImageView)this.findViewById(R.id.imageView);
        this.textView = (TextView)this.findViewById(R.id.textView);
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
                for(int i = 0 ; i < nameFood.length ; i++){
                    for(int j = 0; j < Splash.database.getArrayHasIngredients().size(); j++) {
                        if( nameFood[i] == Splash.database.getArrayHasIngredients().get(j).getFoodName()) {
                            boolean has=false;
                            for (int k = 0; k < tag.size(); k++){
                                if(tag.get(k).equals(Splash.database.getArrayHasIngredients().get(j).getIngredientName())){
                                    has=true;
                                }
                            }
                            if(!has) break;
                        }
                        recipeName.add(nameFood[i]);
                    }

                }
                bitmaps.clear();
            }
        });

        findViewById(R.id.btnCooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(camera.this, ChooseList.class);
                startActivity(in);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            bitmaps.add(photo);
            run2 r = new run2(photo);
            Thread t= new Thread(r);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textView.setText(r.getAns2()+ " " +bitmaps.size() );
            tag.add(r.getAns2());
        }
    }
}

