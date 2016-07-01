package com.ljd.news.domain.interactor;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.NewsRepository;

import javax.inject.Inject;

import rx.Observable;

public class CheckNewsUpdate extends UseCase{

    private final NewsRepository newsRepository;

    @Inject
    public CheckNewsUpdate(ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread,
                           NewsRepository newsRepository) {
        super(threadExecutor, postExecutionThread);
        this.newsRepository = newsRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.newsRepository.checkApkUpdate();
    }
}
