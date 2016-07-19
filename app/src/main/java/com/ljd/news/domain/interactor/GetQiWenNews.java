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

import com.ljd.news.data.repository.AvatarDataRepository;
import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import rx.Observable;

public class GetQiWenNews extends UseCase {

    private final AvatarDataRepository avatarDataRepository;
    private final String APP_KEY = "5ff96710e9a54966a264e3837ab5bfc2";
    private final int RAWS = 10;

    private int page = 1;

    @Inject
    public GetQiWenNews(ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread,
                        AvatarDataRepository avatarDataRepository) {
        super(threadExecutor, postExecutionThread);
        this.avatarDataRepository = avatarDataRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return avatarDataRepository.getGuoNeiNews(APP_KEY,page,RAWS);
    }

    public void setPage(int page) {
        this.page = page;
    }
}
