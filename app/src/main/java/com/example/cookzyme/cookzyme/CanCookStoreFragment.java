package com.example.cookzyme.cookzyme;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookzyme.cookzyme.customAdapter.customAdapter;
import com.example.cookzyme.cookzyme.database.Foods;
import com.example.cookzyme.cookzyme.database.HasIngredient;
import com.example.cookzyme.cookzyme.database.Ingredients;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CanCookStoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CanCookStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CanCookStoreFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public CanCookStoreFragment() {
        // Required empty public constructor
    }

    public static CanCookStoreFragment newInstance() {
        CanCookStoreFragment fragment = new CanCookStoreFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_can_cook_store, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private class CanCookStoreTask extends AsyncTask<Void,Void,Void>{
        customAdapter adapter;
        @Override
        protected Void doInBackground(Void... params) {
            List<Ingredients> ingredientList = new ArrayList<Ingredients>();
            List<Foods> canCook = new ArrayList<Foods>();
            MobileServiceTable<Foods> mFoods = AzureServiceAdapter.getInstance().getClient().getTable(Foods.class);
            MobileServiceTable<HasIngredient> mHasIngredient = AzureServiceAdapter.getInstance().getClient().getTable(HasIngredient.class);
            try {
                List<Foods> mAllFoods = mFoods.execute().get();
                List<Foods> allFoods = mAllFoods;
                List<HasIngredient> mFoodHasIngredient = mHasIngredient.execute().get();
                List<HasIngredient> foodHasIngredient = mFoodHasIngredient;
                List<String> ingredientsInList = new ArrayList<String>();
                for (Ingredients i: ingredientList
                        ) {
                    ingredientsInList.add(i.getIngredient_name());
                }
                for (Foods f:allFoods
                        ) {
                    Boolean hasIngredients = false;
                    for (HasIngredient fh:foodHasIngredient
                            ) {
                        if(f.getFood_name().equals(fh.getFood_name())){

                            if(ingredientsInList.contains(fh.getIngredient_name())){
                                hasIngredients = true;
                                break;
                            }

                        }
                    }
                    if(hasIngredients){
                        canCook.add(f);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
