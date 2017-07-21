package com.example.cookzyme.cookzyme.customAdapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.R;

import java.util.List;

/**
 * Created by Palida on 20-Jul-17.
 */

public class customAdapterIngredient extends BaseAdapter {
    Context mContext;
    private List<String> step;
    private List<Integer> signal;

    public customAdapterIngredient(Context context, List<String> step,List<Integer> signal) {
        this.mContext= context;
        this.step = step;
        this.signal = signal;
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
            view = mInflater.inflate(R.layout.singlerow, parent, false);

        TextView textViewStep = (TextView)view.findViewById(R.id.step);
        textViewStep.setText(step.get(position));

        ImageView imageViewSignal = (ImageView)view.findViewById(R.id.signal);
        imageViewSignal.setBackgroundResource(signal.get(position));

        return view;
    }
}
