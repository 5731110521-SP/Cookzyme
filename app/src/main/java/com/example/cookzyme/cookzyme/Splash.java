package com.example.cookzyme.cookzyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.microsoft.windowsazure.mobileservices.*;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent regIntent = new Intent(Splash.this, camera.class);

                startActivity(regIntent);
            }
        });

    }
}
