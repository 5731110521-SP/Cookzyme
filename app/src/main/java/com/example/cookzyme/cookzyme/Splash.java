package com.example.cookzyme.cookzyme;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
                Intent in = new Intent(Splash.this, recipe.class);
                startActivity(in);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
