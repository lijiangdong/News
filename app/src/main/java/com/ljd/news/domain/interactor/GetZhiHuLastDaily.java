package com.ljd.news.domain.interactor;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.ZhiHuRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetZhiHuLastDaily extends UseCase{

    private final ZhiHuRepository zhiHuRepository;

    @Inject
    protected GetZhiHuLastDaily(ZhiHuRepository zhiHuRepository,
                                ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.zhiHuRepository = zhiHuRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return zhiHuRepository.zhiHuLastDaily();
    }
}
