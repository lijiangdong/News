/*
 * Copyright (C) 2026 News
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

package com.ljd.data.net;

public class RetrofitServiceFactory {

    private static WeChatNewsService weChatNewsService;
    private static final String WE_CHAT_BASE_URL = "";
    private RetrofitServiceFactory(){

    }

    public static WeChatNewsService getWeChatNewsService(){
        if (weChatNewsService == null){
            synchronized (RetrofitServiceFactory.class){
                if (weChatNewsService == null){
                    weChatNewsService = new NewsRetrofit(WE_CHAT_BASE_URL)
                            .getNewsRetrofit(WeChatNewsService.class);
                }
            }
        }

        return weChatNewsService;
    }
}
