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

import com.ljd.news.domain.WeChatNewsResult;
import com.ljd.news.presentation.model.WeChatNewsResultModel;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import static com.ljd.news.utils.Utils.checkNotNull;

public class WeChatNewsModelDataMapper {

    @Inject
    public WeChatNewsModelDataMapper() {
    }



    public Collection<WeChatNewsResultModel> transform(Collection<WeChatNewsResult> weChatNewsResults){
        Collection<WeChatNewsResultModel> weChatNewsResultModels = new ArrayList<>();
        for (WeChatNewsResult weChatNewsResult : weChatNewsResults){
            weChatNewsResultModels.add(this.transform(weChatNewsResult));
        }
        return weChatNewsResultModels;
    }

    public WeChatNewsResultModel transform(WeChatNewsResult weChatNewsResult){
        checkNotNull(weChatNewsResult,"guoNeiNewsResultEntity == null");
        WeChatNewsResultModel weChatNewsResultModel = new WeChatNewsResultModel();
        weChatNewsResultModel.setHottime(weChatNewsResult.getHottime());
        weChatNewsResultModel.setDescription(weChatNewsResult.getDescription());
        weChatNewsResultModel.setPicUrl(weChatNewsResult.getPicUrl());
        weChatNewsResultModel.setTitle(weChatNewsResult.getTitle());
        weChatNewsResultModel.setUrl(weChatNewsResult.getUrl());
        return weChatNewsResultModel;
    }
}
