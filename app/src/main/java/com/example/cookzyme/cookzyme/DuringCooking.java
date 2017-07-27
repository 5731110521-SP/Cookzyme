package com.example.cookzyme.cookzyme;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasIngredient;
import com.example.cookzyme.cookzyme.database.Ingredients;
import com.example.cookzyme.cookzyme.module.SQLiteDBHelper;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class DuringCooking extends Fragment implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private Button next,listen_again;
    private TextView step;
    private int stepNum;
    public static ArrayList<String> stepD = new ArrayList<>();
    private final static int REQUEST_VOICE_RECOGNITION = 10001;
    private String foodName;

    @Override
    public void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }

    public static DuringCooking newInstance() {

        DuringCooking fragment = new DuringCooking();
        return fragment;
    }

    public DuringCooking() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_during_cooking, container, false);
        tts = new TextToSpeech(getContext(), this, "com.google.android.tts");
        tts.setLanguage(new Locale("th"));
        tts.setSpeechRate((float)0.5);
        // change color button
        next = (Button ) rootView.findViewById(R.id.next);
        int colorButton1 = getResources().getColor(R.color.colorButton);
        GradientDrawable sd1 = (GradientDrawable) next.getBackground();
        sd1.setColor(colorButton1);
        listen_again = (Button ) rootView.findViewById(R.id.listen_again);
        GradientDrawable sd2 = (GradientDrawable) listen_again.getBackground();
        sd2.setColor(Color.BLACK);

        // get stepNum, stepD, foodName
        stepNum = getArguments().getInt("stepNum");
        stepD = getArguments().getStringArrayList("stepD");
        foodName = getArguments().getString("foodName");

        //set Text
        step = (TextView ) rootView.findViewById(R.id.step);
        step.setText(stepD.get(stepNum));
        ((android.support.v7.widget.Toolbar) rootView.findViewById(R.id.Toolbar)).setTitle(foodName);

        //ALL handle click
        if(stepNum<stepD.size()-1) {

        }else{
            next.setText("Finish");
        }

        rootView.findViewById(R.id.next).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(stepNum<stepD.size()-1) {
                    DuringCooking during = new DuringCooking();
                    stepNum++;
                    Bundle bundle = new Bundle();
                    bundle.putInt("stepNum", stepNum);
                    bundle.putStringArrayList("stepD", stepD);
                    bundle.putParcelableArrayList("hasIngre",getArguments().getParcelableArrayList("hasIngre"));
                    during.setArguments(bundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameViewRecipe1, during);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{

                    ArrayList<HasIngredient> hasIngre = getArguments().getParcelableArrayList("hasIngre");
                    ArrayList<String> ingreName = new ArrayList<String>();
                    for (Ingredients i:RefrigeratorSectionFragment.refrigerator
                         ) {
                        ingreName.add(i.getIngredient_name());
                    }
                    SQLiteDBHelper database = new SQLiteDBHelper(getContext());
                    for (HasIngredient hi:hasIngre
                         ) {
                        int ingreIndexInFridge = ingreName.indexOf(hi.getIngredient_name());
                        if(hi.getAmount()==0 || hi.getUnit().equals(null)||RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge).getUnit().equals(null)|| !RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge).getUnit().equals(hi.getUnit())){
                            continue;
                        }else {
                            double haveAmount = RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge).getAmount();
                            if(haveAmount > hi.getAmount()){
                                RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge).setAmount(haveAmount-hi.getAmount());
                                database.updateRefrigerator(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                RefrigeratorSectionFragment.customAdapterRefrigerator.setRefrigerator(RefrigeratorSectionFragment.refrigerator);
                            } else if(haveAmount == hi.getAmount()){
                                database.removeRefrigerator(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                RefrigeratorSectionFragment.customAdapterRefrigerator.remove(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                RefrigeratorSectionFragment.refrigerator.remove(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                            } else {
                                double remainingAmount = hi.getAmount();
                                while(remainingAmount>0){
                                    if(haveAmount > hi.getAmount()){
                                        RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge).setAmount(haveAmount-remainingAmount);
                                        database.updateRefrigerator(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                        RefrigeratorSectionFragment.customAdapterRefrigerator.setRefrigerator(RefrigeratorSectionFragment.refrigerator);
                                        remainingAmount=0;
                                    } else if(haveAmount == hi.getAmount()){
                                        database.removeRefrigerator(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                        RefrigeratorSectionFragment.customAdapterRefrigerator.remove(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                        RefrigeratorSectionFragment.refrigerator.remove(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                        remainingAmount=0;
                                    }else{
                                        database.removeRefrigerator(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                        RefrigeratorSectionFragment.customAdapterRefrigerator.remove(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                        RefrigeratorSectionFragment.refrigerator.remove(RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge));
                                        ingreName.remove(ingreIndexInFridge);
                                        ingreIndexInFridge = ingreName.indexOf(hi.getIngredient_name());
                                        remainingAmount-=haveAmount;
                                        haveAmount = RefrigeratorSectionFragment.refrigerator.get(ingreIndexInFridge).getAmount();
                                    }
                                }


                            }

                        }
                    }
                    database.closeDB();
                    Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.my_anim);
                    ImageView medal = (ImageView) rootView.findViewById(R.id.medal);
                    medal.setAnimation(anim);
//                    for(int i=0;i<stepD.size()+2;i++){
//                        getFragmentManager().popBackStack();
//                    }


//                    Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.my_anim);
//                    ImageView medal = (ImageView) rootView.findViewById(R.id.medal);
//                    medal.setAnimation(anim);
                    ShareFragment share = new ShareFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameViewRecipe1, share);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            }
        });
        rootView.findViewById(R.id.listen_again).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        rootView.findViewById(R.id.record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callVoiceRecognition();
            }
        });
        listen_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(stepD.get(stepNum), TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        return rootView;
    }

    private void callVoiceRecognition() {
//        System.out.println("At lease it call");

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(intent, REQUEST_VOICE_RECOGNITION);
        System.out.println("call voice Recognition");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        System.out.println("on activity Result");
        if(REQUEST_VOICE_RECOGNITION==requestCode)
        {

            if (data!=null)
            {
//                System.out.println("has Data");

                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

//                Toast.makeText(getActivity(),text.get(0),Toast.LENGTH_SHORT).show();
                for(int i=0;i<text.size();i++){
//                    System.out.println(text.get(i));
                    if(text.get(i).equalsIgnoreCase("okay")||text.get(i).equalsIgnoreCase("next")||text.get(i).equalsIgnoreCase("nick")
                            ||text.get(i).equalsIgnoreCase("neck")||text.get(i).equalsIgnoreCase("knick")
                            ||text.get(i).equalsIgnoreCase("knicks")||text.get(i).equalsIgnoreCase("nicks")
                            ||text.get(i).equalsIgnoreCase("necks")){
                        next.callOnClick();
                        break;
                    }
                }
            }
            else {
//                System.out.println("No Data,");
//                Toast.makeText(getActivity(), "No data YAA", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS) {
            // Do something here
            System.out.println("Success");
            tts.speak(stepD.get(stepNum), TextToSpeech.QUEUE_FLUSH,null);
        } else {
            System.out.println("Error");
        }
    }
}
