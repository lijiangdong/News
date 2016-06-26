package com.ljd.news.presentation.presenter;


import com.ljd.news.domain.ZhiHuStoryDetail;
import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.mapper.ZhiHuModelDataMapper;
import com.ljd.news.presentation.model.ZhiHuStoryDetailModel;
import com.ljd.news.presentation.view.ZhiHuStoryDetailView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class ZhiHuStoryDetailPresenter implements Presenter {

    private UseCase getZhiHuDailyDetailUseCase;
    private ZhiHuModelDataMapper zhiHuModelDataMapper;
    private ZhiHuStoryDetailView zhiHuStoryDetailView;


    @Inject
    public ZhiHuStoryDetailPresenter(@Named("zhiHuDailyDetail")UseCase getZhiHuDailyDetailUseCase,
                                     ZhiHuModelDataMapper zhiHuModelDataMapper) {
        this.getZhiHuDailyDetailUseCase = getZhiHuDailyDetailUseCase;
        this.zhiHuModelDataMapper = zhiHuModelDataMapper;
    }

    public void setView(ZhiHuStoryDetailView zhiHuStoryDetailView){
        this.zhiHuStoryDetailView = zhiHuStoryDetailView;
    }

    @Override
    public void destroy() {
        this.getZhiHuDailyDetailUseCase.unSubscribe();
    }

    public void initialize() {
        this.loadZhiHuStoryDetails();
    }

    private void loadZhiHuStoryDetails(){
        this.getZhiHuStoryDetails();
    }

    private void getZhiHuStoryDetails(){
        this.getZhiHuDailyDetailUseCase.execute(new ZhiHuStoryDetailsSubscriber());
    }

    private void showZhiHuDetails(ZhiHuStoryDetail zhiHuStoryDetail){
        ZhiHuStoryDetailModel zhiHuStoryDetailModel = this.zhiHuModelDataMapper.transform(zhiHuStoryDetail);
        this.zhiHuStoryDetailView.renderZhiHuStoryList(zhiHuStoryDetailModel);

    }

    private final class ZhiHuStoryDetailsSubscriber extends ResponseSubscriber<ZhiHuStoryDetail>{

        @Override
        protected void onSuccess(ZhiHuStoryDetail zhiHuStoryDetail) {
            showZhiHuDetails(zhiHuStoryDetail);
        }

        @Override
        protected void onFailure(Throwable e) {

        }
    }
}
