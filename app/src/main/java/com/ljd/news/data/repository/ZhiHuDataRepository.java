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

import com.ljd.news.data.entity.mapper.ZhiHuEntityDataMapper;
import com.ljd.news.data.net.RetrofitServiceFactory;
import com.ljd.news.domain.ZhiHuDaily;
import com.ljd.news.domain.ZhiHuStoryDetail;
import com.ljd.news.domain.repository.ZhiHuRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ZhiHuDataRepository implements ZhiHuRepository{

    private final ZhiHuEntityDataMapper zhiHuEntityDataMapper;
    private final RetrofitServiceFactory retrofitServiceFactory;

    @Inject
    public ZhiHuDataRepository(ZhiHuEntityDataMapper zhiHuEntityDataMapper,
                               RetrofitServiceFactory retrofitServiceFactory) {
        this.zhiHuEntityDataMapper = zhiHuEntityDataMapper;
        this.retrofitServiceFactory = retrofitServiceFactory;
    }

    @Override
    public Observable<ZhiHuDaily> zhiHuLastDaily() {
        return retrofitServiceFactory.getZhiHuService().getZhiHuLastDaily()
                .map(zhiHuDailyEntity -> zhiHuEntityDataMapper.transform(zhiHuDailyEntity));
    }

    @Override
    public Observable<ZhiHuStoryDetail> zhiHuStoryDetail(int storyId) {
        return retrofitServiceFactory.getZhiHuService().getZhiHuStory(storyId + "")
                .map(zhiHuStoryDetailEntity -> zhiHuEntityDataMapper.transform(zhiHuStoryDetailEntity));
    }

    @Override
    public Observable<ZhiHuDaily> zhiHuDailyByDate(String date) {
        return retrofitServiceFactory.getZhiHuService().getZhiHuDaily(date)
                .map(zhiHuDailyEntity -> zhiHuEntityDataMapper.transform(zhiHuDailyEntity));
    }
}
