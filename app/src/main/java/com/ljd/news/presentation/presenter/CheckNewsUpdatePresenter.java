package com.ljd.news.presentation.presenter;

import com.ljd.news.domain.UpdateApk;
import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.NewsConfig;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.mapper.NewsModelDataMapper;
import com.ljd.news.presentation.model.UpdateModel;
import com.ljd.news.presentation.view.CheckNewsUpdateView;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class CheckNewsUpdatePresenter implements Presenter {

    private UseCase checkNewsUpdateUseCase;
    private NewsModelDataMapper newsModelDataMapper;
    private CheckNewsUpdateView checkNewsUpdateView;

    @Inject
    public CheckNewsUpdatePresenter(@Named("checkNewsUpdate") UseCase checkNewsUpdateUseCase,
                                    NewsModelDataMapper newsModelDataMapper) {
        this.checkNewsUpdateUseCase = checkNewsUpdateUseCase;
        this.newsModelDataMapper = newsModelDataMapper;
    }

    public void setView(CheckNewsUpdateView checkNewsUpdateView){
        this.checkNewsUpdateView = checkNewsUpdateView;
    }

    public void initialize(){
        this.checkNewsUpdateUseCase.execute(new UpdateNewsSubscriber());
    }

    private void showUpdatePromptDialog(UpdateApk updateApk){
        UpdateModel updateModel = this.newsModelDataMapper.transform(updateApk);
        if (NewsConfig.VERSION_CODE < updateModel.getVersionCode()){
            this.checkNewsUpdateView.updatePromptView(updateModel.getVersionName(),
                    updateModel.getMessage());
        }
    }

    @Override
    public void destroy() {
        this.checkNewsUpdateUseCase.unSubscribe();
    }

    private final class UpdateNewsSubscriber extends ResponseSubscriber<UpdateApk>{
        @Override
        protected void onSuccess(UpdateApk updateApk) {
            showUpdatePromptDialog(updateApk);
        }

        @Override
        protected void onFailure(Throwable e) {

        }
    }
}
