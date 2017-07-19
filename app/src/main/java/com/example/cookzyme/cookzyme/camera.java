package com.example.cookzyme.cookzyme;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

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

    Context MyActivity = this;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private Bitmap image1;
    private TextView textView;
    private Bitmap image;
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private ArrayList<String> tag = new ArrayList<String>();
    public static ArrayList<String> recipeName = new ArrayList<String>();
    String nameFood[] = {"Pork Ball","ก๋วยเตี๋ยวราดหน้าหมูสับ","ข้าวต้มหมูทรงเครื่อง","ข้าวผัดกุ้ง","ข้าวมันไก่","ข้าวไก่กระเทียม","น้ำพริกอ่องอกไก่","ผัดกะเพราไก่","ฟักทองผัดไข่","สุกี้กุ้งสดแห้ง","แกงจืดไข่","ไก่ทอดหาดใหญ่","ไข่เจียว เห็ดเข็มทอง"};
    private Uri imageUri;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
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
                                        new AlertDialog.Builder(camera.this);
                                builder.setTitle("Where are you");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selected = option[which];
                                        if(selected.equals("Home")) {
                                            Intent in= new Intent(camera.this,camera.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                        }else if(selected.equals("Superstore")) {
                                            Intent in = new Intent(camera.this,cameraShopping.class);
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
                                in = new Intent(camera.this, setting.class);
                                startActivity(in);
                                overridePendingTransition(0, 0);
                                break;
                            case R.id.recipe:
                                in = new Intent(camera.this, recipe.class);
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
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                if (checkPermissionWRITE_EXTERNAL_STORAGE(MyActivity)) {
                    imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                }else{
                    System.out.println("******************************************");
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });
        int sum = 0;
        photoButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag.size()==0)return;
                for(int i = 0 ; i < nameFood.length ; i++){
                    boolean has=false;
                    for(int j = 0; j < Splash.database.getArrayHasIngredients().size(); j++) {
                        if( nameFood[i].equals(Splash.database.getArrayHasIngredients().get(j).getFoodName())) {
                            has=false;
                            for (int k = 0; k < tag.size(); k++){
                                if(tag.get(k).equals(Splash.database.getArrayHasIngredients().get(j).getIngredientName())){
                                    has=true;
                                }
                            }
                            if(!has) break;
                        }

                    }
                    if(has)recipeName.add(nameFood[i]);
                }
                bitmaps.clear();

                Intent in = new Intent(camera.this, ChooseList.class);
                startActivity(in);

            }
        });

        Button facebookInfo = (Button)findViewById(R.id.fb_share_button);
        facebookInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(camera.this, FacebookInfo.class);
                startActivity(in);
                finish();
            }
        });
    }

    public boolean checkPermissionWRITE_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (requestCode == CAMERA_REQUEST)
                    if (resultCode == Activity.RESULT_OK) {
                        try {

                            Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            imageView.setImageBitmap(thumbnail);
                            SharePhoto photo = new SharePhoto.Builder()
                                    //.setImageUrl(Uri.parse("https://static.pexels.com/photos/126407/pexels-photo-126407.jpeg"))
                                    .setBitmap(thumbnail)
                                    .build();
                            SharePhotoContent content2 = new SharePhotoContent.Builder()
                                    .addPhoto(photo)
                                    .build();

                            ShareButton shareButton2 = (ShareButton)findViewById(R.id.fb_share_button2);
                            shareButton2.setShareContent(content2);
                            image = thumbnail;
                            bitmaps.add(image1);
                            // imageurl = getRealPathFromURI(imageUri);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
        }

    }

}