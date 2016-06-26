package com.ljd.news.presentation.navigatioin;

import android.content.Context;
import android.content.Intent;

import com.ljd.news.presentation.view.activity.ZhiHuStoryListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {

    @Inject
    public Navigator(){

    }

    public void navigateToZhiHuStoryListActivity(Context context){
        if (context != null){
            Intent intentToLaunch = ZhiHuStoryListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
