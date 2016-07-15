/*
 * Copyright (C) 2016 Li Jiangdong Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class CheckNewsUpdatePresenter implements Presenter<CheckNewsUpdateView> {

    private UseCase checkNewsUpdateUseCase;
    private NewsModelDataMapper newsModelDataMapper;
    private CheckNewsUpdateView checkNewsUpdateView;

    @Inject
    public CheckNewsUpdatePresenter(@Named("checkNewsUpdate") UseCase checkNewsUpdateUseCase,
                                    NewsModelDataMapper newsModelDataMapper) {
        this.checkNewsUpdateUseCase = checkNewsUpdateUseCase;
        this.newsModelDataMapper = newsModelDataMapper;
    }

    @Override
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
