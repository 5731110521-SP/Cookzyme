package com.example.cookzyme.cookzyme;

import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.text.method.ScrollingMovementMethod;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.GridView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.ScrollView;
        import android.widget.Toast;

        import com.example.cookzyme.cookzyme.ExpandableHeightGridView;

        import java.util.ArrayList;
        import java.util.List;

        import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class Profile extends Fragment {
    Integer[] arrImg = {R.drawable.carrot,R.drawable.carrot,R.drawable.carrot,R.drawable.egg, R.drawable.icon, R.drawable.egg, R.drawable.icon, R.drawable.egg, R.drawable.icon, R.drawable.egg};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

//        normal but scroll within grid
//        GridView gridview = (GridView) rootView.findViewById(R.id.gridView);
//        gridview.setAdapter(new customAdapterGrid(getActivity(),arrImg));



        GridViewWithHeaderAndFooter gridView = ( GridViewWithHeaderAndFooter)       rootView.findViewById(R.id.gridView);
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View headerView = layoutInflater.inflate(R.layout.header, null);
        gridView.addHeaderView(headerView);
        gridView.setAdapter(new customAdapterGrid(getActivity(),arrImg));

        //reduce scroll of grid view = can not scroll
//        gridview.setOnTouchListener(new View.OnTouchListener(){
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return event.getAction() == MotionEvent.ACTION_MOVE;
//            }
//
//        });

        //remove scroll bar
//        gridview.setVerticalScrollBarEnabled(false);
//
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();


            }
        });

//        Another how to
//        ExpandableHeightGridView mGridView = (ExpandableHeightGridView)
//                getView().findViewById(R.id.gridView);
//        mGridView.setExpanded(true);
//        customAdapterGrid adapter = new customAdapterGrid(getActivity(),arrImg);
//        mGridView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        return rootView;
    }
}
