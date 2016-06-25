package com.ljd.news.domain.repository;

import com.ljd.news.domain.ZhiHuDaily;

import rx.Observable;

public interface ZhiHuRepository {

    Observable<ZhiHuDaily> zhiHuDaily();
}
