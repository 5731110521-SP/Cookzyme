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
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.speech.tts.Synthesizer;

public class Teach extends AppCompatActivity {

    int stepno=1;
    Foods f;
    private Synthesizer m_syn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);

        if (m_syn == null) {
            // Create Text To Speech Synthesizer.
            m_syn = new Synthesizer(getString(R.string.api_key));
        }

        m_syn.SetServiceStrategy(Synthesizer.ServiceStrategy.AlwaysService);


        TextView tool = (TextView)findViewById(R.id.toolbar_title);

        for (Foods x:Splash.database.getArrayFood()
                ) {
            if(x.getFood_name().equals(recipe.name)){
                f=x;
                break;
            }
        }

        tool.setText(f.getFood_name());
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(f.getPath());
        ((TextView)findViewById(R.id.textView1)).setText(f.getFood_name());
        ((TextView)findViewById(R.id.textView2)).setText(f.getEnergy()+" kcal");
        ((TextView)findViewById(R.id.textLike)).setText(f.getLike()+"");

        for (HowToCook x:Splash.database.getArrayHowToCook()) {
            if(f.getFood_name().equals(x.getFoodName()) && stepno==x.getStepNo()){
                ((TextView)findViewById(R.id.textHow)).setText(x.getStepNo()+". "+x.getStep());
                stepno++;
                speak();
                break;
            }
        }

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (HowToCook x:Splash.database.getArrayHowToCook()) {
                    if(f.getFood_name().equals(x.getFoodName()) && stepno==x.getStepNo()){
                        ((TextView)findViewById(R.id.textHow)).setText(x.getStepNo()+". "+x.getStep());
                        stepno++;
                        speak();
                        break;
                    }
                    if(Splash.database.getArrayHowToCook().get(Splash.database.getArrayHowToCook().size()-1)==x){
                        finish();
                    }
                }
            }
        });

        findViewById(R.id.again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        TextView tv1 = (TextView)findViewById(R.id.toolbar_title);
                        switch (item.getItemId()) {
                            case R.id.camera:
                                final String []option = {"Home","Superstore"};
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(Teach.this);
                                builder.setTitle("Where are you");
                                builder.setItems(option, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String selected = option[which];
                                        if(selected.equals("Home")) {
                                            Intent in = new Intent(Teach.this,Splash.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }else if(selected.equals("Superstore")) {
                                            Intent in = new Intent(Teach.this,Splash.class);
                                            startActivity(in);
                                            overridePendingTransition(0, 0);
                                            finish();
                                        }
                                    }
                                });
                                builder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent in = new Intent(Teach.this,setting.class);
                                        startActivity(in);
                                        overridePendingTransition(0, 0);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                                break;
                            case R.id.setting:
                                Intent in = new Intent(Teach.this, setting.class);
                                startActivity(in);
                                overridePendingTransition(0, 0);
                                break;
                        }
                        return true;
                    }
                });
        //back button
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }

    private void speak() {
        String text = "<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xmlns:mstts=\"http://www.w3.org/2001/mstts\" xml:lang=\"th-TH\"><voice xml:lang=\"th-TH\" name=\"Microsoft Server Speech Text to Speech Voice (th-TH, Pattara)\"><prosody rate=\"-30.00%\">" +
                ((TextView)findViewById(R.id.textHow)).getText()
                +"</prosody></voice></speak>";
        m_syn.SpeakSSMLToAudio(text);
    }
}
