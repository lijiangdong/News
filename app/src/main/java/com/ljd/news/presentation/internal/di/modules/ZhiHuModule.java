/*
 * Copyright (C) 2016 Li Jiangdong Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ljd.news.presentation.internal.di.modules;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.interactor.CheckNewsUpdate;
import com.ljd.news.domain.interactor.GetZhiHuDailyByDate;
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

    @Provides @PerActivity @Named("zhiHuStoryDetail")
    UseCase provideGetZhiHuStoryDetail(ZhiHuRepository zhiHuRepository,
                                       ThreadExecutor threadExecutor,
                                       PostExecutionThread postExecutionThread){
        return new GetZhiHuDailyDetail(this.storyId,zhiHuRepository,threadExecutor,postExecutionThread);
    }

    @Provides @PerActivity @Named("zhiHuDailyByDate")
    UseCase provideGetZhiHuDailyByDate(GetZhiHuDailyByDate getZhiHuDailyByDate){
        return getZhiHuDailyByDate;
    }

    @Provides @PerActivity @Named("checkNewsUpdate")
    UseCase provideUpdateNewsApk(CheckNewsUpdate checkNewsUpdate){
        return checkNewsUpdate;
    }

}
