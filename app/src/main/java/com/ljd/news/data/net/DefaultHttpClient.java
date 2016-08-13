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

package com.ljd.news.data.net;

import android.content.Context;

import com.ljd.news.utils.NetWorkUtils;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Singleton
public class DefaultHttpClient implements HttpClient{

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024; // 10 MiB
    private static final String CACHE_FILE_NAME = "HttpResponseCache";      //缓存文件名

    private Cache cache;
    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR;

    @Inject
    public DefaultHttpClient(Context context){

        final File baseDir = context.getCacheDir();
        final File cacheDir = new File(baseDir, CACHE_FILE_NAME);
        Timber.e(cacheDir.getAbsolutePath());
        cache = new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);

        this.REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
            Request request = chain.request();
            if(!NetWorkUtils.isNetWorkAvailable(context)){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetWorkAvailable(context)) {
                int maxAge = 60;                  //在线缓存一分钟
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();

            } else {
                int maxStale = 60 * 60 * 24 * 4 * 7;     //离线缓存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };
    }

    @Override
    public OkHttpClient getOKHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(new StethoInterceptor())
                .cache(cache)
                .build();
    }
}
