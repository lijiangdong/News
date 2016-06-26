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
