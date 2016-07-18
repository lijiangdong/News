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

import com.ljd.news.domain.ZhiHuDaily;
import com.ljd.news.domain.interactor.GetZhiHuDailyByDate;
import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.exception.ErrorMessageFactory;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.mapper.ZhiHuModelDataMapper;
import com.ljd.news.presentation.model.ZhiHuStoryItemModel;
import com.ljd.news.presentation.view.ZhiHuStoryListView;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class ZhiHuStoryListPresenter implements Presenter<ZhiHuStoryListView> {

    private ZhiHuStoryListView viewListView;

    private final UseCase getZhiHuLastStoryListUseCase;
    private final UseCase getZhiHuStoryListByDateUseCase;
    private final ZhiHuModelDataMapper zhiHuModelDataMapper;

    @Inject
    public ZhiHuStoryListPresenter(@Named("zhiHuLastDaily") UseCase getZhiHuStoryListUseCase,
                                   @Named("zhiHuDailyByDate") UseCase getZhiHuStoryListByDateUseCase,
                                   ZhiHuModelDataMapper zhiHuModelDataMapper) {
        this.getZhiHuLastStoryListUseCase = getZhiHuStoryListUseCase;
        this.getZhiHuStoryListByDateUseCase = getZhiHuStoryListByDateUseCase;
        this.zhiHuModelDataMapper = zhiHuModelDataMapper;
    }

    @Override
    public void setView(ZhiHuStoryListView viewListView){
        this.viewListView = viewListView;
    }

    public void initialize() {
        this.loadZhiHuStoryList();
    }

    public void loadMoreStory(){
        this.viewListView.showLoading();
        this.getMoreStoryList();
    }

    private void loadZhiHuStoryList(){
        this.viewListView.showLoading();
        this.getZhiHuStoryList();
    }

    private void getZhiHuStoryList(){
        this.getZhiHuLastStoryListUseCase.execute(new ZhiHuLastStoryListSubscriber());
    }

    private void showZhiHuCollectionInView(ZhiHuDaily zhiHuDaily){
        Collection<ZhiHuStoryItemModel> zhiHuStoryItemModels = transformStoryItem(zhiHuDaily);
        this.viewListView.renderZhiHuStoryList(zhiHuStoryItemModels);
        setDate(zhiHuDaily.getDate());
    }

    private void getMoreStoryList(){
        this.getZhiHuStoryListByDateUseCase.execute(new ZhiHuStoryListByDateSubscriber());
    }

    private void showMoreZhiHuCollectionInView(ZhiHuDaily zhiHuDaily){
        Collection<ZhiHuStoryItemModel> zhiHuStoryItemModels = transformStoryItem(zhiHuDaily);
        this.viewListView.renderMoreStory(zhiHuStoryItemModels);
        setDate(zhiHuDaily.getDate());
    }

    private void setDate(String date){
        GetZhiHuDailyByDate getZhiHuDailyByDate = (GetZhiHuDailyByDate)getZhiHuStoryListByDateUseCase;
        getZhiHuDailyByDate.setDate(date);
    }

    private void showErrorMessage(Exception e){
        String errorMessage = ErrorMessageFactory.create(viewListView.context(), e);
        this.viewListView.showError(errorMessage);
    }

    private Collection<ZhiHuStoryItemModel> transformStoryItem(ZhiHuDaily zhiHuDaily){
        return this.zhiHuModelDataMapper.transform(zhiHuDaily.getStories());
    }

    @Override
    public void destroy() {
        this.getZhiHuLastStoryListUseCase.unSubscribe();
        this.getZhiHuStoryListByDateUseCase.unSubscribe();
        this.viewListView = null;
    }

    private final class ZhiHuLastStoryListSubscriber extends ResponseSubscriber<ZhiHuDaily>{

        @Override
        protected void onSuccess(ZhiHuDaily zhiHuDaily) {
            viewListView.hideLoading();
            showZhiHuCollectionInView(zhiHuDaily);
        }

        @Override
        protected void onFailure(Throwable e) {
            viewListView.hideLoading();
            showErrorMessage((Exception) e);
        }
    }

    private final class ZhiHuStoryListByDateSubscriber extends ResponseSubscriber<ZhiHuDaily>{
        @Override
        protected void onSuccess(ZhiHuDaily zhiHuDaily) {
            viewListView.hideLoading();
            showMoreZhiHuCollectionInView(zhiHuDaily);
        }

        @Override
        protected void onFailure(Throwable e) {
            viewListView.hideLoading();
            showErrorMessage((Exception) e);
        }
    }
}
