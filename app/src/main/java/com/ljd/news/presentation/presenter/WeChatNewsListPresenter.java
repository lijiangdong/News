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

import com.ljd.news.domain.WeChatNews;
import com.ljd.news.domain.interactor.GetWeChatNews;
import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.exception.ErrorMessageFactory;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.mapper.WeChatNewsModelDataMapper;
import com.ljd.news.presentation.model.WeChatNewsResultModel;
import com.ljd.news.presentation.view.WeChatNewsListView;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class WeChatNewsListPresenter implements Presenter<WeChatNewsListView> {

    private WeChatNewsListView weChatNewsListView;
    private final UseCase getGuoNeiNewsListUseCase;
    private final WeChatNewsModelDataMapper weChatNewsModelDataMapper;
    private int page = 1;

    @Inject
    public WeChatNewsListPresenter(@Named("guoNeiNewsList") UseCase getGuoNeiNewsList,
                                   WeChatNewsModelDataMapper weChatNewsModelDataMapper) {
        this.getGuoNeiNewsListUseCase = getGuoNeiNewsList;
        this.weChatNewsModelDataMapper = weChatNewsModelDataMapper;
    }

    @Override
    public void destroy() {
        this.getGuoNeiNewsListUseCase.unSubscribe();
    }

    public void initialize(){
        this.loadGuoNeiNewsList();
    }

    private void loadGuoNeiNewsList(){
        this.weChatNewsListView.showLoading();
        this.getGuoNeiNewsList();
    }

    private void getGuoNeiNewsList(){
        this.getGuoNeiNewsListUseCase.execute(new GuoNeiNewsListSubscriber());
    }

    @Override
    public void setView(WeChatNewsListView weChatNewsListView) {
        this.weChatNewsListView = weChatNewsListView;
    }

    private void setPage(int page){
        GetWeChatNews getWeChatNews = (GetWeChatNews)getGuoNeiNewsListUseCase;
        getWeChatNews.setPage(page);
    }

    private void showGuoNeiNewsCollectionInView(WeChatNews weChatNews){
        this.weChatNewsListView.renderGuoNeiNewsList(transformGuoNeiNewsList(weChatNews));
    }

    private Collection<WeChatNewsResultModel> transformGuoNeiNewsList(WeChatNews weChatNews){
        return this.weChatNewsModelDataMapper.transform(weChatNews.getResult());
    }

    private void showErrorMessage(Exception e){
        String errorMessage = ErrorMessageFactory.create(weChatNewsListView.context(),e);
        this.weChatNewsListView.showError(errorMessage);
    }

    private final class GuoNeiNewsListSubscriber extends ResponseSubscriber<WeChatNews>{

        @Override
        protected void onSuccess(WeChatNews weChatNews) {
            weChatNewsListView.hideLoading();
            setPage(++page);
            showGuoNeiNewsCollectionInView(weChatNews);
        }

        @Override
        protected void onFailure(Throwable e) {
            weChatNewsListView.hideLoading();
            showErrorMessage((Exception)e);
        }
    }
}
