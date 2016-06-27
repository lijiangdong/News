package com.ljd.news.presentation.view;

import com.ljd.news.presentation.model.ZhiHuStoryDetailModel;

public interface ZhiHuStoryDetailView extends LoadDataView{

    void renderZhiHuStoryDetailByUrl(ZhiHuStoryDetailModel zhiHuStoryDetailModel);

    void renderZhiHuStoryDetailByHtml(ZhiHuStoryDetailModel zhiHuStoryDetailModel);
}
