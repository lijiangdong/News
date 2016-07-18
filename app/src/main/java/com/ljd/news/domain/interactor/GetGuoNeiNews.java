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

public class GetGuoNeiNews extends UseCase {

    private final AvatarDataRepository avatarDataRepository;
    private final String APP_KEY = "5effb2f8f356499e83bc00aba02e17e7";
    private final int RAWS = 10;

    private int page;

    @Inject
    public GetGuoNeiNews(ThreadExecutor threadExecutor,
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
