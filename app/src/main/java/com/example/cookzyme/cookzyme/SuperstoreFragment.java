package com.example.cookzyme.cookzyme;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SuperstoreFragment extends Fragment {

    public static SuperstoreFragment newInstance() {
        SuperstoreFragment fragment = new SuperstoreFragment();
        return fragment;
    }

    public SuperstoreFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_superstore, container, false);
        return rootView;
    
    }

}
