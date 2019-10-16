package com.zl.weilu.androidut.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.weilu.androidut.R;

import androidx.fragment.app.Fragment;


public class SampleFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

}
