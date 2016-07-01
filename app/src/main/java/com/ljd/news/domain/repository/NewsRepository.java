package com.ljd.news.domain.repository;

import com.ljd.news.domain.UpdateApk;

import rx.Observable;

public interface NewsRepository {
    Observable<UpdateApk> checkApkUpdate();
}
