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

import com.ljd.news.data.entity.ZhiHuDailyEntity;
import com.ljd.news.data.entity.ZhiHuStoryDetailEntity;
import com.ljd.news.data.entity.ZhiHuStoryItemEntity;
import com.ljd.news.domain.ZhiHuDaily;
import com.ljd.news.domain.ZhiHuStoryDetail;
import com.ljd.news.domain.ZhiHuStoryItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.ljd.news.utils.Utils.checkNotNull;

@Singleton
public class ZhiHuEntityDataMapper {

    @Inject
    public ZhiHuEntityDataMapper() {
    }

    public ZhiHuDaily transform(ZhiHuDailyEntity zhiHuDailyEntity){
        checkNotNull(zhiHuDailyEntity,"ZhiHuDailyEntity == null");
        ZhiHuDaily zhiHuDaily = new ZhiHuDaily();
        zhiHuDaily.setDate(zhiHuDailyEntity.getDate());
        zhiHuDaily.setStories(transform(zhiHuDailyEntity.getStories()));
        return zhiHuDaily;
    }

    public List<ZhiHuStoryItem> transform(List<ZhiHuStoryItemEntity> zhiHuStoryEntities){
        if (zhiHuStoryEntities == null){
            return null;
        }

        List<ZhiHuStoryItem> zhiHuStories = new ArrayList<>();
        for (ZhiHuStoryItemEntity zhiHuStoryItemEntity : zhiHuStoryEntities){
            zhiHuStories.add(transform(zhiHuStoryItemEntity));
        }
        return zhiHuStories;
    }

    private ZhiHuStoryItem transform(ZhiHuStoryItemEntity zhiHuStoryItemEntity){
        if (zhiHuStoryItemEntity == null){
            return null;
        }

        ZhiHuStoryItem zhiHuStoryItem =new ZhiHuStoryItem();
        zhiHuStoryItem.setGaPrefix(zhiHuStoryItemEntity.getGaPrefix());
        zhiHuStoryItem.setId(zhiHuStoryItemEntity.getId());
        zhiHuStoryItem.setImages(zhiHuStoryItemEntity.getImages());
        zhiHuStoryItem.setTitle(zhiHuStoryItemEntity.getTitle());
        zhiHuStoryItem.setMultipic(zhiHuStoryItemEntity.getMultipic());
        zhiHuStoryItem.setType(zhiHuStoryItemEntity.getType());
        return zhiHuStoryItem;
    }

    public ZhiHuStoryDetail transform(ZhiHuStoryDetailEntity zhiHuStoryDetailEntity){
        checkNotNull(zhiHuStoryDetailEntity,"zhiHuStoryDetailEntity == null");
        ZhiHuStoryDetail zhiHuStoryDetail = new ZhiHuStoryDetail();
        zhiHuStoryDetail.setTitle(zhiHuStoryDetailEntity.getTitle());
        zhiHuStoryDetail.setBody(zhiHuStoryDetailEntity.getBody());
        zhiHuStoryDetail.setCss(zhiHuStoryDetailEntity.getCss());
        zhiHuStoryDetail.setShareUrl(zhiHuStoryDetailEntity.getShareUrl());
        zhiHuStoryDetail.setImage(zhiHuStoryDetailEntity.getImage());
        return zhiHuStoryDetail;
    }
}
