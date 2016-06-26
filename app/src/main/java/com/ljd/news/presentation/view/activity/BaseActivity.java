package com.ljd.news.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ljd.news.NewsApplication;
import com.ljd.news.presentation.internal.di.components.ApplicationComponent;
import com.ljd.news.presentation.internal.di.modules.ActivityModule;
import com.ljd.news.presentation.view.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity implements BaseFragment.Navigator{


    @Override
    public void navigateToActivity(Class clazz) {
        turnToActivity(clazz);
    }

    @Override
    public void navigateToActivity(Class clazz, Bundle bundle) {
        turnToActivity(clazz,bundle);
    }

    @Override
    public void navigateToActivity(Intent intent) {
        turnToActivity(intent);
    }

    protected void turnToActivity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }

    protected void turnToActivity(Class clazz,Bundle bundle){
        Intent intent = new Intent(this,clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void turnToActivity(Intent intent){
        startActivity(intent);
    }


    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((NewsApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
