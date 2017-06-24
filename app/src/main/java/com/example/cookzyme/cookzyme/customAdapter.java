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

public class customAdapter extends BaseAdapter {
    Context mContext;
    private List<String> name;
    private List<Integer> cal;
    private List<Integer> pic;
    private List<Integer> rank;
    private List<Integer> like;

    public customAdapter(Context context, List<String> name,List<Integer> cal, List<Integer> pic,List<Integer> rank,List<Integer> like) {
        this.mContext= context;
        this.name = name;
        this.cal = cal;
        this.pic = pic;
        this.rank = rank;
        this.like = like;
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


        TextView textView1 = (TextView)view.findViewById(R.id.textViewsing2);
        textView1.setText(Integer.toString(cal.get(position)));


        ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(pic.get(position));

        TextView textView3 = (TextView)view.findViewById(R.id.textRank);
        textView3.setText(Integer.toString(rank.get(position)));

        TextView textView4 = (TextView)view.findViewById(R.id.textLike);
        textView4.setText(Integer.toString(like.get(position)));

        ImageView imageView5 = (ImageView)view.findViewById(R.id.imageView4);
        imageView5.setBackgroundResource(R.drawable.rank1);

        ImageView imageView6 = (ImageView)view.findViewById(R.id.imageView5);
        imageView6.setBackgroundResource(R.drawable.like);

        return view;
    }
}
