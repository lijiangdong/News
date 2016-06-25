package com.ljd.news.domain.interactor;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import rx.Observable;

public class GetZhiHuLastDaily extends UseCase{

    @Inject
    protected GetZhiHuLastDaily(ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }
}
