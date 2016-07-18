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

import com.ljd.news.data.net.api.GuoNeiNewsApi;
import com.ljd.news.data.net.api.NewsApi;
import com.ljd.news.data.net.api.WeChatNewsApi;
import com.ljd.news.data.net.api.ZhiHuApi;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RetrofitServiceFactory {

    private static final String WE_CHAT_BASE_URL = "";
    private static final String ZHI_HU_BASE_URL = "http://news-at.zhihu.com";
    private static final String NEWS_URL = "http://lijiangdong.com";
    private static final String AVATAR_BASE_URL = "http://api.avatardata.cn";

    private final NewsRetrofit newsRetrofit;

    @Inject
    public RetrofitServiceFactory(NewsRetrofit newsRetrofit){
        this.newsRetrofit = newsRetrofit;
    }

    public WeChatNewsApi getWeChatNewsService(){
        return newsRetrofit.getNewsRetrofit(WeChatNewsApi.class,WE_CHAT_BASE_URL);
    }

    public ZhiHuApi getZhiHuService(){
        return newsRetrofit.getNewsRetrofit(ZhiHuApi.class,ZHI_HU_BASE_URL);
    }

    public NewsApi getNewsService(){
        return newsRetrofit.getNewsRetrofit(NewsApi.class,NEWS_URL);
    }

    public GuoNeiNewsApi getGuoNeiNewsApi(){
        return newsRetrofit.getNewsRetrofit(GuoNeiNewsApi.class,AVATAR_BASE_URL);
    }
}
