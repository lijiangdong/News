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

import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.internal.di.modules.ActivityModule;
import com.ljd.news.presentation.internal.di.modules.GuoNeiNewsModule;
import com.ljd.news.presentation.internal.di.modules.ZhiHuModule;
import com.ljd.news.presentation.view.activity.MainActivity;
import com.ljd.news.presentation.view.fragment.QiWenNewsListFragment;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryDetailFragment;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, ZhiHuModule.class, GuoNeiNewsModule.class})
public interface MainComponent {

    void inject(ZhiHuStoryListFragment fragment);

    void inject(ZhiHuStoryDetailFragment fragment);

    void inject(MainActivity activity);

    void inject(QiWenNewsListFragment fragment);
}