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

import com.ljd.news.domain.ZhiHuStoryDetail;
import com.ljd.news.domain.ZhiHuStoryItem;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.model.ZhiHuStoryDetailModel;
import com.ljd.news.presentation.model.ZhiHuStoryItemModel;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import static com.ljd.news.utils.Utils.checkNotNull;

@PerActivity
public class ZhiHuModelDataMapper {

    @Inject
    public ZhiHuModelDataMapper() {
    }

    public Collection<ZhiHuStoryItemModel> transform (Collection<ZhiHuStoryItem> zhiHuStoryItems){
        checkNotNull(zhiHuStoryItems,"zhiHuStoryItems == null");
        Collection<ZhiHuStoryItemModel> zhiHuStoryItemModels = new ArrayList<>();
        for (ZhiHuStoryItem zhiHuStoryItem : zhiHuStoryItems){
            zhiHuStoryItemModels.add(transform(zhiHuStoryItem));
        }
        return zhiHuStoryItemModels;
    }

    public ZhiHuStoryItemModel transform(ZhiHuStoryItem zhiHuStoryItem){
        checkNotNull(zhiHuStoryItem,"zhiHuStoryItem == null");
        ZhiHuStoryItemModel zhiHuStoryItemModel = new ZhiHuStoryItemModel();
        zhiHuStoryItemModel.setGaPrefix(zhiHuStoryItem.getGaPrefix());
        zhiHuStoryItemModel.setTitle(zhiHuStoryItem.getTitle());
        zhiHuStoryItemModel.setId(zhiHuStoryItem.getId());
        zhiHuStoryItemModel.setImage(zhiHuStoryItem.getImages().get(0));
        zhiHuStoryItemModel.setMultipic(zhiHuStoryItem.getMultipic());
        zhiHuStoryItemModel.setType(zhiHuStoryItem.getType());
        return zhiHuStoryItemModel;
    }

    public ZhiHuStoryDetailModel transform(ZhiHuStoryDetail zhiHuStoryDetail){
        checkNotNull(zhiHuStoryDetail,"zhiHuStoryDetail == null");
        ZhiHuStoryDetailModel zhiHuStoryDetailModel = new ZhiHuStoryDetailModel();
        zhiHuStoryDetailModel.setImage(zhiHuStoryDetail.getImage());
        zhiHuStoryDetailModel.setShareUrl(zhiHuStoryDetail.getShareUrl());
        zhiHuStoryDetailModel.setCss(zhiHuStoryDetail.getCss());
        zhiHuStoryDetailModel.setTitle(zhiHuStoryDetail.getTitle());
        zhiHuStoryDetailModel.setBody(zhiHuStoryDetail.getBody());

        return zhiHuStoryDetailModel;
    }
}
