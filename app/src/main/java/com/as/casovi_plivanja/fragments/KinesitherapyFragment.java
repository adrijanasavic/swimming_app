package com.as.casovi_plivanja.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.as.casovi_plivanja.R;

public class KinesitherapyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kinesy, container, false);

        requireActivity().setTitle(getString(R.string.kin));

        return rootView;
    }
}