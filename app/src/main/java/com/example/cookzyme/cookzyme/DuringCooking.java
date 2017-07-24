package com.example.cookzyme.cookzyme;

import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.customAdapter.customAdapterIngredient;

import java.util.ArrayList;
import java.util.Random;


public class DuringCooking extends Fragment {

    private Button confirmButton;
    private MediaRecorder mediaRecorder = new MediaRecorder();
    Random random ;
    MediaPlayer mediaPlayer ;
    ImageView record;

    public static DuringCooking newInstance() {

        DuringCooking fragment = new DuringCooking();
        return fragment;
    }

    public DuringCooking() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_during_cooking, container, false);

        // change color button
        confirmButton = (Button ) rootView.findViewById(R.id.confirm);
        int colorButton = getResources().getColor(R.color.colorButton);
        GradientDrawable sd = (GradientDrawable) confirmButton.getBackground();
        sd.setColor(colorButton);

        // other part
        record = (ImageView) rootView.findViewById(R.id.record);


        return rootView;
    }
}
