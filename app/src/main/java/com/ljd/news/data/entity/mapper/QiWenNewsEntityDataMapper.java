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

import com.ljd.news.data.entity.QiWenNewsEntity;
import com.ljd.news.data.entity.QiWenNewsResultEntity;
import com.ljd.news.domain.GuoNeiNews;
import com.ljd.news.domain.GuoNeiNewsResult;

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

    public GuoNeiNews transform(QiWenNewsEntity qiWenNewsEntity){
        checkNotNull(qiWenNewsEntity,"qiWenNewsEntity == null");
        GuoNeiNews guoNeiNews = new GuoNeiNews();
        guoNeiNews.setErrorCode(qiWenNewsEntity.getErrorCode());
        guoNeiNews.setReason(qiWenNewsEntity.getReason());
        guoNeiNews.setResult(transform(qiWenNewsEntity.getResult()));
        return guoNeiNews;
    }

    public List<GuoNeiNewsResult> transform(List<QiWenNewsResultEntity> guoNeiNewsResultEntities){
        List<GuoNeiNewsResult> guoNeiNewsResults = new ArrayList<>();
        for (QiWenNewsResultEntity qiWenNewsResultEntity : guoNeiNewsResultEntities){
            guoNeiNewsResults.add(this.transform(qiWenNewsResultEntity));
        }
        return guoNeiNewsResults;
    }

    public GuoNeiNewsResult transform(QiWenNewsResultEntity qiWenNewsResultEntity){
        checkNotNull(qiWenNewsResultEntity,"qiWenNewsResultEntity == null");
        GuoNeiNewsResult guoNeiNewsResult = new GuoNeiNewsResult();
        guoNeiNewsResult.setCtime(qiWenNewsResultEntity.getCtime());
        guoNeiNewsResult.setDescription(qiWenNewsResultEntity.getDescription());
        guoNeiNewsResult.setPicUrl(qiWenNewsResultEntity.getPicUrl());
        guoNeiNewsResult.setTitle(qiWenNewsResultEntity.getTitle());
        guoNeiNewsResult.setUrl(qiWenNewsResultEntity.getUrl());
        return guoNeiNewsResult;
    }
}
