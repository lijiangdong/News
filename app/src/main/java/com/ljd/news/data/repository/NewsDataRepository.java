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

package com.ljd.news.data.repository;

import com.ljd.news.data.entity.mapper.NewsEntityDataMapper;
import com.ljd.news.data.net.RetrofitServiceFactory;
import com.ljd.news.domain.UpdateApk;
import com.ljd.news.domain.repository.NewsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import rx.Observable;

@Singleton
public class NewsDataRepository implements NewsRepository {

    private final NewsEntityDataMapper newsEntityDataMapper;
    private final RetrofitServiceFactory retrofitServiceFactory;

    @Inject
    public NewsDataRepository(RetrofitServiceFactory retrofitServiceFactory,
                              NewsEntityDataMapper newsEntityDataMapper) {
        this.retrofitServiceFactory = retrofitServiceFactory;
        this.newsEntityDataMapper = newsEntityDataMapper;
    }

    @Override
    public Observable<UpdateApk> checkApkUpdate() {
        return retrofitServiceFactory.getNewsService().checkUpdateApk()
                .map(updateApkEntity -> newsEntityDataMapper.transform(updateApkEntity));
    }

    @Override
    public Observable<ResponseBody> downloadNewsApk() {
        return retrofitServiceFactory.getNewsService().downloadApk();
    }
}
