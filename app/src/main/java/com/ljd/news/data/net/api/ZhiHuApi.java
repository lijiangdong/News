/*
 * Copyright (C) 2016 News
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * author  LiJiangdong
 * email   ljd2038@gmail.com
 */

package com.ljd.news.data.net.api;

import com.ljd.news.data.entity.ZhiHuDailyEntity;
import com.ljd.news.data.entity.ZhiHuStoryEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ZhiHuApi {

    @GET("/api/4/news/latest")
    Observable<ZhiHuDailyEntity> getZhiHuLastDaily();

    @GET("/api/4/news/before/{date}")
    Observable<ZhiHuStoryEntity> getZhiHuDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhiHuStoryEntity> getZhihuStory(@Path("id") String id);
}
