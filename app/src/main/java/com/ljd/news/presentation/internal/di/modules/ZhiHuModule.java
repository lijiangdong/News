package com.ljd.news.presentation.internal.di.modules;

import com.ljd.news.domain.interactor.GetZhiHuDaily;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ZhiHuModule {

    private int storyId = -1;

    public ZhiHuModule(){

    }

    public ZhiHuModule(int storyId){
        this.storyId = storyId;
    }

    @Provides
    @PerActivity
    @Named("zhiHuLastDaily")
    UseCase provideGetZhiHuLastDailyUseCase(GetZhiHuDaily getZhiHuDaily){
        return getZhiHuDaily;
    }
}
