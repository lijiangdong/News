package com.ljd.news.presentation.internal.di.modules;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.interactor.GetZhiHuDailyDetail;
import com.ljd.news.domain.interactor.GetZhiHuLastDaily;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.domain.repository.ZhiHuRepository;
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

    @Provides @PerActivity @Named("zhiHuLastDaily")
    UseCase provideGetZhiHuLastDailyUseCase(GetZhiHuLastDaily getZhiHuLastDaily){
        return getZhiHuLastDaily;
    }

    @Provides @PerActivity @Named("zhiHuDailyDetail")
    UseCase provideGetZhiHuStoryDetail(ZhiHuRepository zhiHuRepository,
                                       ThreadExecutor threadExecutor,
                                       PostExecutionThread postExecutionThread){
        return new GetZhiHuDailyDetail(this.storyId,zhiHuRepository,threadExecutor,postExecutionThread);
    }
}
