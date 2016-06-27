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

package com.ljd.news.data.net;


import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Singleton
public class NewsRetrofit {

    private final HttpClient httpClient;

    @Inject
    public NewsRetrofit(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public <T> T getNewsRetrofit(Class<T> clazz, String baseUrl) {

        if (TextUtils.isEmpty(baseUrl)) {
            throw new NullPointerException("BaseUrl cannot be null");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(this.httpClient.getOKHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(clazz);
    }
}