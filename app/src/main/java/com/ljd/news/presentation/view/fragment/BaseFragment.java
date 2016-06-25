package com.ljd.news.presentation.view.fragment;


import android.support.v4.app.Fragment;

import com.ljd.news.presentation.internal.di.HasComponent;

public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
