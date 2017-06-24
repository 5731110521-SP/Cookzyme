package com.example.cookzyme.cookzyme;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.microsoft.speech.tts.Synthesizer;
import com.microsoft.speech.tts.Voice;
import com.microsoft.windowsazure.mobileservices.*;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import com.squareup.picasso.Picasso;

public class Splash extends AppCompatActivity {

    private static final String[] PERMISSIONS_READ_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private Synthesizer m_syn;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    String imagePath;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FILE_URI = 100;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_CONTENT_RESOLVER = 101;
    Context mContext;
    PermissionsChecker checker;
    private static int SPLASH_TIME_OUT = 4000;
    final static Database database=new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = getApplicationContext();
        checker = new PermissionsChecker(this);

        Log.d("CREATION",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                                                         public void onClick(View v) {
                                                             Intent regIntent = new Intent(Splash.this, ToDoActivity.class);

                                                             startActivity(regIntent);
                                                         }
                                                     });
/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(Splash.this, recipe.class);
                startActivity(in);
                finish();
            }
        }, SPLASH_TIME_OUT);
*/
        if (m_syn == null) {
            // Create Text To Speech Synthesizer.
            m_syn = new Synthesizer(getString(R.string.api_key));
        }

        m_syn.SetServiceStrategy(Synthesizer.ServiceStrategy.AlwaysService);

        findViewById(R.id.TTS).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String text = "<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xmlns:mstts=\"http://www.w3.org/2001/mstts\" xml:lang=\"en-US\"><voice xml:lang=\"en-US\" name=\"Microsoft Server Speech Text to Speech Voice (en-US, BenjaminRUS)\">" +
                        ((EditText)findViewById(R.id.TTSeditText)).getText()
                        +"</voice></speak>";
                m_syn.SpeakSSMLToAudio(text);
            }
        });

        findViewById(R.id.cvision).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent regIntent = new Intent(Splash.this, cvision.class);
                Log.d("CREATION","e");
                startActivity(regIntent);
            }
        });

        imageView = (ImageView)findViewById(R.id.imageView);

        Button photoButton = (Button) findViewById(R.id.camera);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                /*Uri mPhotoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new ContentValues());
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
                startActivityForResult(intent,CAMERA_REQUEST);*/
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent regIntent = new Intent(Splash.this, recipe.class);
                startActivity(regIntent);
            }
        });

    }

    public void showImagePopup(View view) {
        if (checker.lacksPermissions(PERMISSIONS_READ_STORAGE)) {
            startPermissionsActivity(PERMISSIONS_READ_STORAGE);
        } else {
            // File System.
            final Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_PICK);

            // Chooser of file system options.
            final Intent chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.string_choose_image));
            startActivityForResult(chooserIntent, 1010);
        }
    }

    private void startPermissionsActivity(String[] permission) {
        PermissionsActivity.startActivityForResult(this, 0, permission);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

            /*System.out.println(encodeTobase64(photo));
            MultipartEntity mpEntity = new MultipartEntity();
            mpEntity.addPart("image", new StringBody(encodeTobase64(photo), ContentType.TEXT_PLAIN));*/
        }
        if (resultCode == RESULT_OK && requestCode == 1010) {
            if (data == null) {
                return;
            }
            Uri selectedImageUri=data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);

                Picasso.with(mContext).load(new File(imagePath))
                        .into(imageView);

                cursor.close();
            }

            //((EditText)findViewById(R.id.TTSeditText)).setText(imagePath);
        }
    }

}
