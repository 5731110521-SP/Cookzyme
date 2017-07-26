package com.example.cookzyme.cookzyme;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.speech.RecognizerIntent;
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

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class DuringCooking extends Fragment {
    private Button next,listen_again;
    private TextView step;
    private int stepNum;
    private ArrayList<String> stepD = new ArrayList<>();
    private final static int REQUEST_VOICE_RECOGNITION = 10001;

    public static DuringCooking newInstance() {

        DuringCooking fragment = new DuringCooking();
        return fragment;
    }

    public DuringCooking() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_during_cooking, container, false);

        // change color button
        next = (Button ) rootView.findViewById(R.id.next);
        int colorButton1 = getResources().getColor(R.color.colorButton);
        GradientDrawable sd1 = (GradientDrawable) next.getBackground();
        sd1.setColor(colorButton1);
        listen_again = (Button ) rootView.findViewById(R.id.listen_again);
        GradientDrawable sd2 = (GradientDrawable) listen_again.getBackground();
        sd2.setColor(Color.BLACK);

        // get stepNum, stepD
        stepNum = getArguments().getInt("stepNum");
        stepD = getArguments().getStringArrayList("stepD");

        //set Text
        step = (TextView ) rootView.findViewById(R.id.step);
        step.setText(stepD.get(stepNum));

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
                    during.setArguments(bundle);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameViewRecipe1, during);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else{
                    Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.my_anim);
                    ImageView medal = (ImageView) rootView.findViewById(R.id.medal);
                    medal.setAnimation(anim);
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

}
