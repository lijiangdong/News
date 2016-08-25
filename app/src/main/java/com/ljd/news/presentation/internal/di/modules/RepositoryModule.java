package com.ljd.news.presentation.internal.di.modules;

import com.ljd.news.data.repository.NewsDataRepository;
import com.ljd.news.data.repository.ZhiHuDataRepository;
import com.ljd.news.domain.repository.NewsRepository;
import com.ljd.news.domain.repository.ZhiHuRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides @Singleton
    ZhiHuRepository provideZhiHuRepository(ZhiHuDataRepository zhiHuDataRepository) {
        return zhiHuDataRepository;
    }

    @Provides @Singleton
    NewsRepository provideNewsRepository(NewsDataRepository newsDataRepository){
        return newsDataRepository;
    }
}
