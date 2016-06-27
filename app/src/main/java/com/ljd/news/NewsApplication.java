/*
 * Copyright (C) 2016 News
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * author  LiJiangdong
 * email   ljd2038@gmail.com
 */

package com.ljd.news;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.ljd.news.presentation.internal.di.components.ApplicationComponent;
import com.ljd.news.presentation.internal.di.components.DaggerApplicationComponent;
import com.ljd.news.presentation.internal.di.modules.ApplicationModule;
import com.ljd.news.utils.ToastUtils;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public class NewsApplication extends Application {

    private ApplicationComponent applicationComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        initToastUtils();
        initLeakCanary();
        initTimber();
        initializeInjector();
        initStetho();
    }

    public static Context getContext(){
        return context;
    }
    private void initToastUtils(){
        ToastUtils.register(this);
    }

    private void initLeakCanary(){
        LeakCanary.install(this);
    }

    private void initTimber(){
        if (NewsConfig.IS_DEBUG){
            Timber.plant(new DebugTree());
        }
    }
    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initStetho(){
        Stetho.initializeWithDefaults(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
