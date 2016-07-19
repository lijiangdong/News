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
import com.ljd.news.domain.interactor.GetGuoNeiNews;
import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.exception.ErrorMessageFactory;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.mapper.GuoNeiNewsModelDataMapper;
import com.ljd.news.presentation.model.GuoNeiNewsResultModel;
import com.ljd.news.presentation.view.GuoNeiNewsListView;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class GuoNeiNewsListPresenter implements Presenter<GuoNeiNewsListView> {

    private GuoNeiNewsListView guoNeiNewsListView;
    private final UseCase getGuoNeiNewsListUseCase;
    private final GuoNeiNewsModelDataMapper guoNeiNewsModelDataMapper;
    private int page = 1;

    @Inject
    public GuoNeiNewsListPresenter(@Named("guoNeiNewsList") UseCase getGuoNeiNewsList,
                                   GuoNeiNewsModelDataMapper guoNeiNewsModelDataMapper) {
        this.getGuoNeiNewsListUseCase = getGuoNeiNewsList;
        this.guoNeiNewsModelDataMapper = guoNeiNewsModelDataMapper;
    }

    @Override
    public void destroy() {
        this.getGuoNeiNewsListUseCase.unSubscribe();
    }

    public void initialize(){
        this.loadGuoNeiNewsList();
    }

    private void loadGuoNeiNewsList(){
        this.guoNeiNewsListView.showLoading();
        this.getGuoNeiNewsList();
    }

    private void getGuoNeiNewsList(){
        this.getGuoNeiNewsListUseCase.execute(new GuoNeiNewsListSubscriber());
    }

    @Override
    public void setView(GuoNeiNewsListView guoNeiNewsListView) {
        this.guoNeiNewsListView = guoNeiNewsListView;
    }

    private void setPage(int page){
        GetGuoNeiNews getGuoNeiNews = (GetGuoNeiNews)getGuoNeiNewsListUseCase;
        getGuoNeiNews.setPage(page);
    }

    private void showGuoNeiNewsCollectionInView(GuoNeiNews guoNeiNews){
        this.guoNeiNewsListView.renderGuoNeiNewsList(transformGuoNeiNewsList(guoNeiNews));
    }

    private Collection<GuoNeiNewsResultModel> transformGuoNeiNewsList(GuoNeiNews guoNeiNews){
        return this.guoNeiNewsModelDataMapper.transform(guoNeiNews.getResult());
    }

    private void showErrorMessage(Exception e){
        String errorMessage = ErrorMessageFactory.create(guoNeiNewsListView.context(),e);
        this.guoNeiNewsListView.showError(errorMessage);
    }

    private final class GuoNeiNewsListSubscriber extends ResponseSubscriber<GuoNeiNews>{

        @Override
        protected void onSuccess(GuoNeiNews guoNeiNews) {
            guoNeiNewsListView.hideLoading();
            setPage(++page);
            showGuoNeiNewsCollectionInView(guoNeiNews);
        }

        @Override
        protected void onFailure(Throwable e) {
            guoNeiNewsListView.hideLoading();
            showErrorMessage((Exception)e);
        }
    }
}
