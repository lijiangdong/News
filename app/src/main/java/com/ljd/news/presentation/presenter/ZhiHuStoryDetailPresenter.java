package com.ljd.news.presentation.presenter;


import android.text.TextUtils;

import com.ljd.news.domain.ZhiHuStoryDetail;
import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.exception.ErrorMessageFactory;
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
    public ZhiHuStoryDetailPresenter(@Named("zhiHuStoryDetail")UseCase getZhiHuDailyDetailUseCase,
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
        zhiHuStoryDetailView.showLoading();
        this.getZhiHuStoryDetails();
    }

    private void getZhiHuStoryDetails(){
        this.getZhiHuDailyDetailUseCase.execute(new ZhiHuStoryDetailsSubscriber());
    }

    private void showZhiHuDetails(ZhiHuStoryDetail zhiHuStoryDetail){
        ZhiHuStoryDetailModel zhiHuStoryDetailModel = this.zhiHuModelDataMapper.transform(zhiHuStoryDetail);
        if (TextUtils.isEmpty(zhiHuStoryDetail.getBody())){
            this.zhiHuStoryDetailView.renderZhiHuStoryDetailByUrl(zhiHuStoryDetailModel);
        }else {
            this.zhiHuStoryDetailView.hideLoading();
            this.zhiHuStoryDetailView.renderZhiHuStoryDetailByHtml(zhiHuStoryDetailModel);
        }
    }

    private void showErrorMessage(Exception e){
        String errorMessage = ErrorMessageFactory.create(zhiHuStoryDetailView.context(), e);
        zhiHuStoryDetailView.showError(errorMessage);
    }

    private final class ZhiHuStoryDetailsSubscriber extends ResponseSubscriber<ZhiHuStoryDetail>{

        @Override
        protected void onSuccess(ZhiHuStoryDetail zhiHuStoryDetail) {
            showZhiHuDetails(zhiHuStoryDetail);
        }

        @Override
        protected void onFailure(Throwable e) {
            zhiHuStoryDetailView.hideLoading();
            showErrorMessage((Exception) e);
        }
    }


}
