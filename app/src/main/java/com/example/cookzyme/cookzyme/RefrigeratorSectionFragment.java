package com.example.cookzyme.cookzyme;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RefrigeratorSectionFragment extends Fragment {

    ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.refrigerator_section_fragment, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.pagerRefrigerator);

        return rootView;
    }

}
