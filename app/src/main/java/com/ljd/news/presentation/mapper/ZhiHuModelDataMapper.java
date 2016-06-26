package com.ljd.news.presentation.mapper;

import com.ljd.news.domain.ZhiHuStoryItem;
import com.ljd.news.presentation.internal.di.PerActivity;
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
}
