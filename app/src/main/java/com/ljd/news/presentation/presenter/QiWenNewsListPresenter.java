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

import com.ljd.news.domain.GuoNeiNews;
import com.ljd.news.domain.interactor.GetQiWenNews;
import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.exception.ErrorMessageFactory;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.mapper.QiWenNewsModelDataMapper;
import com.ljd.news.presentation.model.QiWenNewsResultModel;
import com.ljd.news.presentation.view.QiWenNewsListView;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class QiWenNewsListPresenter implements Presenter<QiWenNewsListView> {

    private QiWenNewsListView qiWenNewsListView;
    private final UseCase getGuoNeiNewsListUseCase;
    private final QiWenNewsModelDataMapper qiWenNewsModelDataMapper;
    private int page = 1;

    @Inject
    public QiWenNewsListPresenter(@Named("guoNeiNewsList") UseCase getGuoNeiNewsList,
                                  QiWenNewsModelDataMapper qiWenNewsModelDataMapper) {
        this.getGuoNeiNewsListUseCase = getGuoNeiNewsList;
        this.qiWenNewsModelDataMapper = qiWenNewsModelDataMapper;
    }

    @Override
    public void destroy() {
        this.getGuoNeiNewsListUseCase.unSubscribe();
    }

    public void initialize(){
        this.loadGuoNeiNewsList();
    }

    private void loadGuoNeiNewsList(){
        this.qiWenNewsListView.showLoading();
        this.getGuoNeiNewsList();
    }

    private void getGuoNeiNewsList(){
        this.getGuoNeiNewsListUseCase.execute(new GuoNeiNewsListSubscriber());
    }

    @Override
    public void setView(QiWenNewsListView qiWenNewsListView) {
        this.qiWenNewsListView = qiWenNewsListView;
    }

    private void setPage(int page){
        GetQiWenNews getQiWenNews = (GetQiWenNews)getGuoNeiNewsListUseCase;
        getQiWenNews.setPage(page);
    }

    private void showGuoNeiNewsCollectionInView(GuoNeiNews guoNeiNews){
        this.qiWenNewsListView.renderGuoNeiNewsList(transformGuoNeiNewsList(guoNeiNews));
    }

    private Collection<QiWenNewsResultModel> transformGuoNeiNewsList(GuoNeiNews guoNeiNews){
        return this.qiWenNewsModelDataMapper.transform(guoNeiNews.getResult());
    }

    private void showErrorMessage(Exception e){
        String errorMessage = ErrorMessageFactory.create(qiWenNewsListView.context(),e);
        this.qiWenNewsListView.showError(errorMessage);
    }

    private final class GuoNeiNewsListSubscriber extends ResponseSubscriber<GuoNeiNews>{

        @Override
        protected void onSuccess(GuoNeiNews guoNeiNews) {
            qiWenNewsListView.hideLoading();
            setPage(++page);
            showGuoNeiNewsCollectionInView(guoNeiNews);
        }

        @Override
        protected void onFailure(Throwable e) {
            qiWenNewsListView.hideLoading();
            showErrorMessage((Exception)e);
        }
    }
}
