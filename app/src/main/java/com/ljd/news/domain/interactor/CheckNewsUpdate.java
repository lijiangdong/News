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

package com.ljd.news.domain.interactor;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.NewsRepository;

import javax.inject.Inject;

import rx.Observable;

public class CheckNewsUpdate extends UseCase{

    private final NewsRepository newsRepository;

    @Inject
    public CheckNewsUpdate(ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread,
                           NewsRepository newsRepository) {
        super(threadExecutor, postExecutionThread);
        this.newsRepository = newsRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.newsRepository.checkApkUpdate();
    }
}
