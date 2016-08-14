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

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;
import com.ljd.news.data.excutor.JobExecutor;
import com.ljd.news.data.net.DefaultHttpClient;
import com.ljd.news.data.net.HttpClient;
import com.ljd.news.data.repository.NewsDataRepository;
import com.ljd.news.data.repository.ZhiHuDataRepository;
import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.NewsRepository;
import com.ljd.news.domain.repository.ZhiHuRepository;
import com.ljd.news.presentation.NewsApplication;
import com.ljd.news.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private final NewsApplication application;

    public ApplicationModule(NewsApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    HttpClient provideHttpClient(DefaultHttpClient defaultHttpClient){
        return defaultHttpClient;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    ZhiHuRepository provideZhiHuRepository(ZhiHuDataRepository zhiHuDataRepository) {
        return zhiHuDataRepository;
    }

    @Provides @Singleton
    NewsRepository provideNewsRepository(NewsDataRepository newsDataRepository){
        return newsDataRepository;
    }

    @Provides @Singleton
    PatchManager providePackageManager(){
        return new PatchManager(application.getApplicationContext());
    }

}
