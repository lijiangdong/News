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
import com.ljd.news.domain.repository.ZhiHuRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetZhiHuLastDaily extends UseCase{

    private final ZhiHuRepository zhiHuRepository;

    @Inject
    protected GetZhiHuLastDaily(ZhiHuRepository zhiHuRepository,
                                ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.zhiHuRepository = zhiHuRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return zhiHuRepository.zhiHuLastDaily();
    }
}
