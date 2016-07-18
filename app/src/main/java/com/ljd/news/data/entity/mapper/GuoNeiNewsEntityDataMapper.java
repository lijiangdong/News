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

import com.ljd.news.data.entity.GuoNeiNewsEntity;
import com.ljd.news.data.entity.GuoNeiNewsResultEntity;
import com.ljd.news.domain.GuoNeiNews;
import com.ljd.news.domain.GuoNeiNewsResult;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.ljd.news.utils.Utils.checkNotNull;

@Singleton
public class GuoNeiNewsEntityDataMapper {

    @Inject
    public GuoNeiNewsEntityDataMapper() {
    }

    public GuoNeiNews transform(GuoNeiNewsEntity guoNeiNewsEntity){
        checkNotNull(guoNeiNewsEntity,"guoNeiNewsEntity == null");
        GuoNeiNews guoNeiNews = new GuoNeiNews();
        guoNeiNews.setErrorCode(guoNeiNewsEntity.getErrorCode());
        guoNeiNews.setReason(guoNeiNewsEntity.getReason());
        guoNeiNews.setResult(transform(guoNeiNewsEntity.getResult()));
        return guoNeiNews;
    }

    public List<GuoNeiNewsResult> transform(List<GuoNeiNewsResultEntity> guoNeiNewsResultEntities){
        List<GuoNeiNewsResult> guoNeiNewsResults = Collections.EMPTY_LIST;
        for (GuoNeiNewsResultEntity guoNeiNewsResultEntity : guoNeiNewsResultEntities){
            guoNeiNewsResults.add(this.transform(guoNeiNewsResultEntity));
        }
        return guoNeiNewsResults;
    }

    public GuoNeiNewsResult transform(GuoNeiNewsResultEntity guoNeiNewsResultEntity){
        checkNotNull(guoNeiNewsResultEntity,"guoNeiNewsResultEntity == null");
        GuoNeiNewsResult guoNeiNewsResult = new GuoNeiNewsResult();
        guoNeiNewsResult.setCtime(guoNeiNewsResultEntity.getCtime());
        guoNeiNewsResult.setDescription(guoNeiNewsResultEntity.getDescription());
        guoNeiNewsResult.setPicUrl(guoNeiNewsResultEntity.getPicUrl());
        guoNeiNewsResult.setTitle(guoNeiNewsResultEntity.getTitle());
        guoNeiNewsResult.setUrl(guoNeiNewsResultEntity.getUrl());
        return guoNeiNewsResult;
    }
}
