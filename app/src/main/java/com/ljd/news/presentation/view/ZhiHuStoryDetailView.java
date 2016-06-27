package com.ljd.news.presentation.view;

import com.ljd.news.domain.ZhiHuStoryDetail;

public interface ZhiHuStoryDetailView extends LoadDataView{

    void renderZhiHuStoryDetailByUrl(String url);

    void renderZhiHuStoryDetailByHtml(ZhiHuStoryDetail zhiHuStoryDetail);
}
