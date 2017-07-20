package com.example.cookzyme.cookzyme;

import android.support.v4.app.Fragment;
        import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookzyme.cookzyme.customAdapter.customAdapterGrid;

import de.hdodenhof.circleimageview.CircleImageView;
        import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class Profile extends Fragment {
    Integer[] arrImg = {R.drawable.carrot,R.drawable.carrot,R.drawable.carrot,R.drawable.egg, R.drawable.icon, R.drawable.egg, R.drawable.icon, R.drawable.egg, R.drawable.icon, R.drawable.egg};
    private static TextView username,cookingNum,followersNum,followingNum;
    private static ImageView headerPic;
    private static CircleImageView userPic;


    public static Profile newInstance() {
        Profile fragment = new Profile();
        return fragment;
    }

    public Profile() { }

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


        //ALL set
        username = (TextView) headerView.findViewById(R.id.username);
        Profile.username.setText("Palida");
        cookingNum = (TextView) headerView.findViewById(R.id.cookingNum);
        Profile.cookingNum.setText("222");
        followersNum = (TextView) headerView.findViewById(R.id.followersNum);
        Profile.followersNum.setText("255");
        followingNum = (TextView) headerView.findViewById(R.id.followingNum);
        Profile.followingNum.setText("144");
        userPic = (CircleImageView ) headerView.findViewById(R.id.userPic);
        userPic.setImageResource(R.drawable.avatar);
        headerPic = (ImageView) headerView.findViewById(R.id.headerPic);
        headerPic.setImageResource(R.drawable.chef);

        //ALL handle click
        headerView.findViewById(R.id.followButton).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        headerView.findViewById(R.id.challengeButton).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        headerView.findViewById(R.id.cooking_layout).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
        headerView.findViewById(R.id.follower_layout).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            }
        });headerView.findViewById(R.id.following_layout).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });



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
//                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString(String.valueOf(position), "From Activity");

                ViewPostInProfile viewPost = new ViewPostInProfile();
                viewPost.setArguments(bundle);
                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, viewPost);
                transaction.addToBackStack(null);
                transaction.commit();
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
