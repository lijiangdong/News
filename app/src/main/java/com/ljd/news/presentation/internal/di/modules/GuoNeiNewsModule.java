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

import com.ljd.news.domain.interactor.GetGuoNeiNews;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class GuoNeiNewsModule {

    @Provides @PerActivity @Named("guoNeiNewsList")
    UseCase provideGetGuoNeiNewsList(GetGuoNeiNews getGuoNeiNews){
        return getGuoNeiNews;
    }
}
