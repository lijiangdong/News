package com.ljd.news.presentation.presenter;

import com.ljd.news.domain.ZhiHuDaily;
import com.ljd.news.domain.ZhiHuStoryItem;
import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.mapper.ZhiHuModelDataMapper;
import com.ljd.news.presentation.model.ZhiHuStoryItemModel;
import com.ljd.news.presentation.view.ZhiHuStoryListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class ZhiHuStoryListPresenter implements Presenter {

    private ZhiHuStoryListView viewListView;

    private final UseCase getZhiHuStoryListUseCase;
    private final ZhiHuModelDataMapper zhiHuModelDataMapper;

    @Inject
    public ZhiHuStoryListPresenter(@Named("zhiHuLastDaily") UseCase getZhiHuStoryListUseCase,
                                   ZhiHuModelDataMapper zhiHuModelDataMapper) {
        this.getZhiHuStoryListUseCase = getZhiHuStoryListUseCase;
        this.zhiHuModelDataMapper = zhiHuModelDataMapper;
    }

    public void setView(ZhiHuStoryListView viewListView){
        this.viewListView = viewListView;
    }

    public void initialize() {
        this.loadZhiHuStoryList();
    }

    private void loadZhiHuStoryList(){
        this.getZhiHuStoryList();
    }

    private void getZhiHuStoryList(){
        getZhiHuStoryListUseCase.execute(new ZhiHuStoryListSubscriber());
    }
    private void showZhiHuCollectionInView(List<ZhiHuStoryItem> zhiHuStoryItems){
        Collection<ZhiHuStoryItemModel> zhiHuStoryItemModels = zhiHuModelDataMapper.transform(zhiHuStoryItems);
        viewListView.renderZhiHuStoryList(zhiHuStoryItemModels);
    }

    @Override
    public void destroy() {
        this.getZhiHuStoryListUseCase.unSubscribe();
        this.viewListView = null;
    }

    private final class ZhiHuStoryListSubscriber extends ResponseSubscriber<ZhiHuDaily>{

        @Override
        protected void onSuccess(ZhiHuDaily zhiHuDaily) {
            showZhiHuCollectionInView(zhiHuDaily.getStories());
        }

        @Override
        protected void onFailure(Throwable e) {

        }
    }
}
