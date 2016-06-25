package com.ljd.news.presentation.internal.di.components;

import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.internal.di.modules.ActivityModule;
import com.ljd.news.presentation.internal.di.modules.ZhiHuModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, ZhiHuModule.class})
public interface ZhiHuComponent {

}
