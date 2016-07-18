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

package com.ljd.news.presentation.mapper;

import com.ljd.news.domain.GuoNeiNews;
import com.ljd.news.domain.GuoNeiNewsResult;
import com.ljd.news.presentation.model.GuoNeiNewsModel;
import com.ljd.news.presentation.model.GuoNeiNewsResultModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static com.ljd.news.utils.Utils.checkNotNull;

public class GuoNeiNewsModelDataMapper {

    @Inject
    public GuoNeiNewsModelDataMapper() {
    }

    public GuoNeiNewsModel transform(GuoNeiNews guoNeiNews){
        checkNotNull(guoNeiNews,"guoNeiNewsEntity == null");
        GuoNeiNewsModel guoNeiNewsModel = new GuoNeiNewsModel();
        guoNeiNewsModel.setErrorCode(guoNeiNews.getErrorCode());
        guoNeiNewsModel.setReason(guoNeiNews.getReason());
        guoNeiNewsModel.setResult(transform(guoNeiNews.getResult()));
        return guoNeiNewsModel;
    }

    public List<GuoNeiNewsResultModel> transform(List<GuoNeiNewsResult> guoNeiNewsResults){
        List<GuoNeiNewsResultModel> guoNeiNewsResultModels = Collections.EMPTY_LIST;
        for (GuoNeiNewsResult guoNeiNewsResult : guoNeiNewsResults){
            guoNeiNewsResultModels.add(this.transform(guoNeiNewsResult));
        }
        return guoNeiNewsResultModels;
    }

    public GuoNeiNewsResultModel transform(GuoNeiNewsResult guoNeiNewsResult){
        checkNotNull(guoNeiNewsResult,"guoNeiNewsResultEntity == null");
        GuoNeiNewsResultModel guoNeiNewsResultModel = new GuoNeiNewsResultModel();
        guoNeiNewsResultModel.setCtime(guoNeiNewsResult.getCtime());
        guoNeiNewsResultModel.setDescription(guoNeiNewsResult.getDescription());
        guoNeiNewsResultModel.setPicUrl(guoNeiNewsResult.getPicUrl());
        guoNeiNewsResultModel.setTitle(guoNeiNewsResult.getTitle());
        guoNeiNewsResultModel.setUrl(guoNeiNewsResult.getUrl());
        return guoNeiNewsResultModel;
    }
}
