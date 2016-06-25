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

import com.ljd.news.data.net.api.WeChatNewsApi;
import com.ljd.news.data.net.api.ZhiHuApi;

import javax.inject.Inject;

public class RetrofitServiceFactory {

    private static final String WE_CHAT_BASE_URL = "";
    private static final String ZHI_HU_BASE_URL = "http://news-at.zhihu.com";

    @Inject
    public static NewsRetrofit newsRetrofit;

    private RetrofitServiceFactory(){

    }

    public static WeChatNewsApi getWeChatNewsService(){
        return newsRetrofit.getNewsRetrofit(WeChatNewsApi.class,WE_CHAT_BASE_URL);
    }

    public static ZhiHuApi getZhiHuService(){
        return newsRetrofit.getNewsRetrofit(ZhiHuApi.class,ZHI_HU_BASE_URL);
    }
}
