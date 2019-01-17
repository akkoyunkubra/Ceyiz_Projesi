package com.ceyizuygulamasi.kubra.ceyizprojesi.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ceyizuygulamasi.kubra.ceyizprojesi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaylasimlarFragment extends Fragment {


    public PaylasimlarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paylasimlar, container, false);
    }

}
