package com.ljd.news.presentation.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.view.activity.BaseActivity;

public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }

    protected Navigator navigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.navigator = (Navigator) context;
        }
    }

    @Override
    public void onDetach() {
        this.navigator = null;
        super.onDetach();
    }

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    public interface Navigator {
        void navigateToActivity(final Intent intent);
    }

    public interface HandleFloatActionButton{
        void hideFloatActionButton();
        void showFloatActionButton();
    }
}
