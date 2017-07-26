package com.example.cookzyme.cookzyme;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.example.cookzyme.cookzyme.customAdapter.customAdapterIngredient;
import java.util.ArrayList;
import android.support.v4.app.FragmentTransaction;


public class ViewRecipeFragment extends Fragment {

    private ArrayList<String> step = new ArrayList<>() ;
    private ArrayList<Integer> signal = new ArrayList<>();
    private TextView directText,ingre;
    private FloatingActionButton cooking;
    private int stepNum;
    private ArrayList<String> stepD = new ArrayList<>();

    public static ViewRecipeFragment newInstance() {

        ViewRecipeFragment fragment = new ViewRecipeFragment();
        return fragment;
    }

    public ViewRecipeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_recipe, container, false);


        step.add("1 tok kai tok kai tok kai tok kai tok kai tok kai tok kai tok kai tok kai tok kai tok kai tok kai");
        step.add("2 sai kata");
        signal.add(R.drawable.warning);
        signal.add(R.drawable.warning);
        stepNum = 0;
        stepD.add("1 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("2 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("3 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("4 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("5 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("6 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("7 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("8 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("9 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("10 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");
        stepD.add("11 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");

        final customAdapterIngredient adapterIngre = new customAdapterIngredient(getActivity().getApplicationContext(), step,signal);
        ListView listViewIngre = (ListView ) rootView.findViewById(R.id.listviewIngre);
        listViewIngre.setAdapter(adapterIngre);
//        listViewDirect.setEnabled(false)

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        View directView = layoutInflater.inflate(R.layout.direct_text, null);
        listViewIngre.addFooterView(directView);

        directText = (TextView) directView.findViewById(R.id.directText);
        directText.setText("1 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n2 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n3 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n4 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n5 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n6 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n7 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n8 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n9 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n10 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่ \n11 ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่  ล้างสันใจไก่");

        View header = layoutInflater.inflate(R.layout.header_section, null);
        ingre = (TextView) rootView.findViewById(R.id.directText);
        listViewIngre.addHeaderView(header);

        cooking = (FloatingActionButton) rootView.findViewById(R.id.cooking);
        cooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DuringCooking during = new DuringCooking();

                Bundle bundle = new Bundle();
                bundle.putInt("stepNum", stepNum);
                bundle.putStringArrayList("stepD",stepD);
                during.setArguments(bundle);

                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameViewRecipe1, during);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return rootView;
    }
}
