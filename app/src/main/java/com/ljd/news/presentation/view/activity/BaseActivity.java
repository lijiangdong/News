package com.ljd.news.presentation.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ljd.news.NewsApplication;
import com.ljd.news.presentation.internal.di.components.ApplicationComponent;
import com.ljd.news.presentation.internal.di.modules.ActivityModule;
import com.ljd.news.presentation.navigatioin.Navigator;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator = new Navigator();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getApplicationComponent().inject(this);
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
