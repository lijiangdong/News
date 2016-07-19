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

package com.ljd.news.data.entity.mapper;

import com.ljd.news.data.entity.WeChatNewsEntity;
import com.ljd.news.data.entity.WeChatNewsResultEntity;
import com.ljd.news.domain.WeChatNews;
import com.ljd.news.domain.WeChatNewsResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.ljd.news.utils.Utils.checkNotNull;

@Singleton
public class QiWenNewsEntityDataMapper {

    @Inject
    public QiWenNewsEntityDataMapper() {
    }

    public WeChatNews transform(WeChatNewsEntity weChatNewsEntity){
        checkNotNull(weChatNewsEntity,"weChatNewsEntity == null");
        WeChatNews weChatNews = new WeChatNews();
        weChatNews.setErrorCode(weChatNewsEntity.getErrorCode());
        weChatNews.setReason(weChatNewsEntity.getReason());
        weChatNews.setResult(transform(weChatNewsEntity.getResult()));
        return weChatNews;
    }

    public List<WeChatNewsResult> transform(List<WeChatNewsResultEntity> guoNeiNewsResultEntities){
        List<WeChatNewsResult> weChatNewsResults = new ArrayList<>();
        for (WeChatNewsResultEntity weChatNewsResultEntity : guoNeiNewsResultEntities){
            weChatNewsResults.add(this.transform(weChatNewsResultEntity));
        }
        return weChatNewsResults;
    }

    public WeChatNewsResult transform(WeChatNewsResultEntity weChatNewsResultEntity){
        checkNotNull(weChatNewsResultEntity,"weChatNewsResultEntity == null");
        WeChatNewsResult weChatNewsResult = new WeChatNewsResult();
        weChatNewsResult.setHottime(weChatNewsResultEntity.getHottime());
        weChatNewsResult.setDescription(weChatNewsResultEntity.getDescription());
        weChatNewsResult.setPicUrl(weChatNewsResultEntity.getPicUrl());
        weChatNewsResult.setTitle(weChatNewsResultEntity.getTitle());
        weChatNewsResult.setUrl(weChatNewsResultEntity.getUrl());
        return weChatNewsResult;
    }
}
