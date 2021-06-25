package com.elhefny.askdoctor2.AllFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elhefny.askdoctor2.databinding.FragmentCovid19DetailsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Covid_19_Details_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Covid_19_Details_Fragment extends DialogFragment {
    private FragmentCovid19DetailsBinding fragmentCovid19DetailsBinding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private int mParam1;
    private int mParam2;
    private int mParam3;

    public Covid_19_Details_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param newInfected Parameter 1.
     * @param newDeath    Parameter 2.
     * @param newRecovery    Parameter 3.
     * @return A new instance of fragment Covid_19_Details_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Covid_19_Details_Fragment newInstance(int newInfected, int newDeath,int newRecovery) {
        Covid_19_Details_Fragment fragment = new Covid_19_Details_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, newInfected);
        args.putInt(ARG_PARAM2, newDeath);
        args.putInt(ARG_PARAM3, newRecovery);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCovid19DetailsBinding = FragmentCovid19DetailsBinding.inflate(inflater, container, false);
        View view = fragmentCovid19DetailsBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentCovid19DetailsBinding.fragmentCovid19DetailsNewInfected.setText(String.valueOf(mParam1));
        fragmentCovid19DetailsBinding.fragmentCovid19DetailsNewDeath.setText(String.valueOf(mParam2));
        fragmentCovid19DetailsBinding.fragmentCovid19DetailsNewRecovery.setText(String.valueOf(mParam3));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentCovid19DetailsBinding = null;
    }
}