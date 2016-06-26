package com.ljd.news.presentation.presenter;


import com.ljd.news.presentation.internal.di.PerActivity;

import javax.inject.Inject;

@PerActivity
public class ZhiHuStoryDetailPresenter implements Presenter {

    @Inject
    public ZhiHuStoryDetailPresenter() {
    }

    @Override
    public void destroy() {

    }

}
