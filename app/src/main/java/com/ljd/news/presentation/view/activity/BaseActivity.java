package com.ljd.news.presentation.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ljd.news.presentation.NewsApplication;
import com.ljd.news.presentation.internal.di.components.ApplicationComponent;
import com.ljd.news.presentation.internal.di.modules.ActivityModule;
import com.ljd.news.presentation.view.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity implements BaseFragment.Navigator{

    @Override
    public void navigateToActivity(Intent intent) {
        startActivity(intent);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void replaceFragment(int containerViewId,Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId,fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((NewsApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}
