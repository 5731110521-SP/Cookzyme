package com.example.cookzyme.cookzyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Palid on 24-Jun-17.
 */

public class customAdapterChosse extends BaseAdapter {
    Context mContext;
    private List<String> name;
    private List<Integer> cal;
    private List<Integer> pic;
    private List<Integer> rank;
    private List<Integer> like;

    public customAdapterChosse(Context context, List<String> name, List<Integer> pic) {
        this.mContext= context;
        this.name = name;
        this.pic = pic;
    }

    public int getCount() {
        return name.size();
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

        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText(name.get(position));

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(pic.get(position));


        return view;
    }
}
