package com.ljd.news.domain.repository;

import com.ljd.news.domain.ZhiHuDaily;
import com.ljd.news.domain.ZhiHuStoryDetail;

import rx.Observable;

public interface ZhiHuRepository {

    Observable<ZhiHuDaily> zhiHuLastDaily();

    Observable<ZhiHuStoryDetail> zhiHuDailyDetail(int storyId);
}
