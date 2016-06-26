package com.ljd.news.presentation.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljd.news.R;

import butterknife.ButterKnife;

public class ZhiHuStoryFragment extends Fragment {


    public ZhiHuStoryFragment() {
        this.setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_zhi_hu_story, container, false);
        ButterKnife.bind(this,layout);
        return layout;
    }
}
