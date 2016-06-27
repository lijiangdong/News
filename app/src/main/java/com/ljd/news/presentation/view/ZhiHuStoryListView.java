package com.ljd.news.presentation.view;

import com.ljd.news.presentation.model.ZhiHuStoryItemModel;

import java.util.Collection;

public interface ZhiHuStoryListView extends LoadDataView{

    void renderZhiHuStoryList(Collection<ZhiHuStoryItemModel> zhiHuStoryItemModels);
}
