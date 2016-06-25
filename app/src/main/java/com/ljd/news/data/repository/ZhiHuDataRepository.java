package com.ljd.news.data.repository;

import com.ljd.news.data.entity.mapper.ZhiHuEntityDataMapper;
import com.ljd.news.data.net.RetrofitServiceFactory;
import com.ljd.news.domain.ZhiHuDaily;
import com.ljd.news.domain.repository.ZhiHuRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class ZhiHuDataRepository implements ZhiHuRepository{

    private final ZhiHuEntityDataMapper zhiHuEntityDataMapper;
    private final RetrofitServiceFactory retrofitServiceFactory;

    @Inject
    public ZhiHuDataRepository(ZhiHuEntityDataMapper zhiHuEntityDataMapper,
                               RetrofitServiceFactory retrofitServiceFactory) {
        this.zhiHuEntityDataMapper = zhiHuEntityDataMapper;
        this.retrofitServiceFactory = retrofitServiceFactory;
    }

    @Override
    public Observable<ZhiHuDaily> zhiHuDaily() {
        return retrofitServiceFactory.getZhiHuService().getZhiHuLastDaily()
                .map(zhiHuDailyEntity -> zhiHuEntityDataMapper.transform(zhiHuDailyEntity));
    }
}
