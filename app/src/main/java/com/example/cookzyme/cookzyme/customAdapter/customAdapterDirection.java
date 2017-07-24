package com.example.cookzyme.cookzyme.customAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.R;

import java.util.ArrayList;

/**
 * Created by Palida on 21-Jul-17.
 */

public class customAdapterDirection extends BaseAdapter {
    Context mContext;
    private ArrayList<String> step;

    public customAdapterDirection(Context context, ArrayList<String> step) {
        this.mContext= context;
        this.step = step;
    }

    public int getCount() {
        return step.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.singlerow_direction, parent, false);

        TextView textViewStep = (TextView)view.findViewById(R.id.step);
        textViewStep.setText(step.get(position));

        return view;
    }
}