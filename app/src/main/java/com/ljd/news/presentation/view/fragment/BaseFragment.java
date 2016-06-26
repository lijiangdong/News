package com.ljd.news.presentation.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.view.activity.BaseActivity;

public abstract class BaseFragment extends Fragment {

    public interface Navigator {
        void navigateToActivity(final Class clazz);
        void navigateToActivity(final Class clazz, Bundle bundle);
        void navigateToActivity(final Intent intent);
    }

    protected Navigator navigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.navigator = (Navigator) context;
        }
    }

    public BaseFragment() {
        // Required empty public constructor
    }


    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
