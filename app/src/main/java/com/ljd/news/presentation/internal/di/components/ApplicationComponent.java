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

package com.ljd.news.presentation.internal.di.components;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;
import com.ljd.news.data.repository.AvatarDataRepository;
import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.NewsRepository;
import com.ljd.news.domain.repository.ZhiHuRepository;
import com.ljd.news.presentation.NewsApplication;
import com.ljd.news.presentation.internal.di.modules.ApplicationModule;
import com.ljd.news.presentation.internal.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    void inject(NewsApplication newsApplication);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ZhiHuRepository zhiHuRepository();
    NewsRepository newsRepository();
    AvatarDataRepository avatarDataRepository();
    PatchManager patchManager();
}
