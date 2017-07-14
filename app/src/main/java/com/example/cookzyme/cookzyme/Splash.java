package com.example.cookzyme.cookzyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    final static Database database=new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FacebookSdk.sdkInitialize(getApplicationContext(), new FacebookSdk.InitializeCallback() {
                    @Override
                    public void onInitialized() {
                        if(AccessToken.getCurrentAccessToken() == null){
                            System.out.println("not logged in yet");
                            Intent in = new Intent(Splash.this, HomeActivity.class);
                            startActivity(in);
                            finish();
                        } else {
                            System.out.println("Logged in");
                            Intent in = new Intent(Splash.this, HomeActivity.class);
                            startActivity(in);
                            finish();
                        }
                    }
                });
            }
        }, SPLASH_TIME_OUT);

    }
}
