package com.ljd.news.presentation.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljd.news.R;

import butterknife.ButterKnife;

public class ZhiHuStoryListFragment extends BaseFragment  {


    public ZhiHuStoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_zhi_hu_story_list, container, false);
        ButterKnife.bind(this,layout);
        return layout;
    }
}
