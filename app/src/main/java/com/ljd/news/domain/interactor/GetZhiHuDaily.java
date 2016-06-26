package com.ljd.news.domain.interactor;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.ZhiHuRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetZhiHuDaily extends UseCase {

    private final ZhiHuRepository zhiHuRepository;

    @Inject
    protected GetZhiHuDaily(ZhiHuRepository zhiHuRepository,
                                ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.zhiHuRepository = zhiHuRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.zhiHuRepository.zhiHuDaily();
    }
}
