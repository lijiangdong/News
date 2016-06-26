package com.ljd.news.presentation.internal.di.components;

import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.internal.di.modules.ActivityModule;
import com.ljd.news.presentation.internal.di.modules.ZhiHuModule;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryDetailFragment;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, ZhiHuModule.class})
public interface ZhiHuComponent {

    void inject(ZhiHuStoryListFragment fragment);

    void inject(ZhiHuStoryDetailFragment fragment);
}
