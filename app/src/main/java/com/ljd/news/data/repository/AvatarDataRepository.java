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

import com.ljd.news.data.entity.mapper.AvatarEntityDataMapper;
import com.ljd.news.data.net.RetrofitServiceFactory;
import com.ljd.news.domain.GuoNeiNews;
import com.ljd.news.domain.repository.AvatarRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class AvatarDataRepository implements AvatarRepository {

    private final AvatarEntityDataMapper avatarEntityDataMapper;
    private final RetrofitServiceFactory retrofitServiceFactory;

    @Inject
    public AvatarDataRepository(AvatarEntityDataMapper avatarEntityDataMapper, RetrofitServiceFactory retrofitServiceFactory) {
        this.avatarEntityDataMapper = avatarEntityDataMapper;
        this.retrofitServiceFactory = retrofitServiceFactory;
    }

    @Override
    public Observable<GuoNeiNews> getGuoNeiNews(String key, int page, int rows) {
        return retrofitServiceFactory.getGuoNeiNewsApi().getGuoNeiNews(key,page,rows)
                .map(guoNeiNewsEntity -> avatarEntityDataMapper.transform(guoNeiNewsEntity));
    }
}
