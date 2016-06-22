/*
 * Copyright (C) 2026 News
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

package com.ljd.news.internal.di.modules;

import android.content.Context;

import com.ljd.data.excutor.JobExecutor;
import com.ljd.domain.executor.PostExecutionThread;
import com.ljd.domain.executor.ThreadExecutor;
import com.ljd.news.NewsApplication;
import com.ljd.news.UIThread;

import javax.inject.Singleton;

import dagger.Provides;

public class ApplicationModule {
    private final NewsApplication application;

    public ApplicationModule(NewsApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
//
//    @Provides @Singleton UserRepository provideUserRepository(UserDataRepository userDataRepository) {
//        return userDataRepository;
//    }
}
