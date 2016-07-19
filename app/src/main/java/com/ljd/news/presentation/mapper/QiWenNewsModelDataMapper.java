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

import com.ljd.news.domain.GuoNeiNewsResult;
import com.ljd.news.presentation.model.QiWenNewsResultModel;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import static com.ljd.news.utils.Utils.checkNotNull;

public class QiWenNewsModelDataMapper {

    @Inject
    public QiWenNewsModelDataMapper() {
    }



    public Collection<QiWenNewsResultModel> transform(Collection<GuoNeiNewsResult> guoNeiNewsResults){
        Collection<QiWenNewsResultModel> qiWenNewsResultModels = new ArrayList<>();
        for (GuoNeiNewsResult guoNeiNewsResult : guoNeiNewsResults){
            qiWenNewsResultModels.add(this.transform(guoNeiNewsResult));
        }
        return qiWenNewsResultModels;
    }

    public QiWenNewsResultModel transform(GuoNeiNewsResult guoNeiNewsResult){
        checkNotNull(guoNeiNewsResult,"guoNeiNewsResultEntity == null");
        QiWenNewsResultModel qiWenNewsResultModel = new QiWenNewsResultModel();
        qiWenNewsResultModel.setCtime(guoNeiNewsResult.getCtime());
        qiWenNewsResultModel.setDescription(guoNeiNewsResult.getDescription());
        qiWenNewsResultModel.setPicUrl(guoNeiNewsResult.getPicUrl());
        qiWenNewsResultModel.setTitle(guoNeiNewsResult.getTitle());
        qiWenNewsResultModel.setUrl(guoNeiNewsResult.getUrl());
        return qiWenNewsResultModel;
    }
}
