package com.ljd.news.domain.interactor;

import com.ljd.news.domain.executor.PostExecutionThread;
import com.ljd.news.domain.executor.ThreadExecutor;
import com.ljd.news.domain.repository.ZhiHuRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetZhiHuDailyDetail extends UseCase {

    private final ZhiHuRepository zhiHuRepository;
    private final int storyId;

    @Inject
    public GetZhiHuDailyDetail(int storyId,
                                  ZhiHuRepository zhiHuRepository,
                                  ThreadExecutor threadExecutor,
                                  PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.zhiHuRepository = zhiHuRepository;
        this.storyId = storyId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.zhiHuRepository.zhiHuStoryDetail(this.storyId);
    }
}
