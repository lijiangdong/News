package com.ljd.news.data.repository;

import com.ljd.news.data.entity.mapper.NewsEntityDataMapper;
import com.ljd.news.data.net.RetrofitServiceFactory;
import com.ljd.news.domain.UpdateApk;
import com.ljd.news.domain.repository.NewsRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class NewsDataRepository implements NewsRepository {

    private final NewsEntityDataMapper newsEntityDataMapper;
    private final RetrofitServiceFactory retrofitServiceFactory;

    @Inject
    public NewsDataRepository(RetrofitServiceFactory retrofitServiceFactory,
                              NewsEntityDataMapper newsEntityDataMapper) {
        this.retrofitServiceFactory = retrofitServiceFactory;
        this.newsEntityDataMapper = newsEntityDataMapper;
    }

    @Override
    public Observable<UpdateApk> checkApkUpdate() {
        return retrofitServiceFactory.getNewsService().checkUpdateApk()
                .map(updateApkEntity -> newsEntityDataMapper.transform(updateApkEntity));
    }
}
