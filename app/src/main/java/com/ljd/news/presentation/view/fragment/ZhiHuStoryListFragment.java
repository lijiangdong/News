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

package com.ljd.news.presentation.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.components.MainComponent;
import com.ljd.news.presentation.model.ZhiHuStoryItemModel;
import com.ljd.news.presentation.presenter.ZhiHuStoryListPresenter;
import com.ljd.news.presentation.view.ZhiHuStoryListView;
import com.ljd.news.presentation.view.activity.MainActivity;
import com.ljd.news.presentation.view.activity.ZhiHuStoryDetailActivity;
import com.ljd.news.presentation.view.adapter.ZhiHuAdapter;
import com.ljd.news.utils.ToastUtils;

import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiHuStoryListFragment extends BaseFragment  implements ZhiHuStoryListView{


    @BindView(R.id.zhi_hu_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.rl_progress) RelativeLayout progressView;

    @Inject ZhiHuAdapter zhiHuAdapter;
    @Inject ZhiHuStoryListPresenter zhiHuStoryListPresenter;

    public ZhiHuStoryListFragment() {
        setRetainInstance(true);
    }

    private Collection<ZhiHuStoryItemModel> zhiHuStoryDataCollection = Collections.emptyList();
    private LinearLayoutManager linearLayoutManager;
    private boolean isLoading;
    private int previousVisibleItem;
    private HandleFloatActionButton handleFab;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            this.handleFab = (HandleFloatActionButton) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_zhi_hu_story_list, container, false);
        ButterKnife.bind(this,layout);
        setRecyclerView();
        return layout;
    }

    private void setRecyclerView(){
        this.zhiHuAdapter.setOnItemClickListener(zhiHuStoryItemModel ->
                this.onClickRecycleViewItem(zhiHuStoryItemModel));
        this.linearLayoutManager = new LinearLayoutManager(context());
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(zhiHuAdapter);
        this.recyclerView.addOnScrollListener(new RecyclerViewScrollListener());
    }

    private void onClickRecycleViewItem(ZhiHuStoryItemModel zhiHuStoryItemModel){
        if (navigator != null){
            navigator.navigateToActivity(ZhiHuStoryDetailActivity
                    .getCallingIntent(getActivity(),zhiHuStoryItemModel.getId()));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.zhiHuStoryListPresenter.setView(this);
        if (savedInstanceState == null){
            this.loadZhiHuStoryList();
        }
    }

    private void loadZhiHuStoryList(){
        this.zhiHuStoryListPresenter.initialize();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.recyclerView.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.zhiHuStoryListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.handleFab = null;
    }


    private void loadMoreView(){
        this.isLoading = true;
        this.zhiHuStoryListPresenter.loadMoreStory();
    }

    @Override
    public void renderZhiHuStoryList(Collection<ZhiHuStoryItemModel> zhiHuStoryItemModels) {
        if (zhiHuStoryItemModels != null){
            this.zhiHuStoryDataCollection = zhiHuStoryItemModels;
        }
        this.zhiHuAdapter.setStoryList(zhiHuStoryDataCollection);
    }

    @Override
    public void renderMoreStory(Collection<ZhiHuStoryItemModel> zhiHuStoryItemModels) {
        if (zhiHuStoryItemModels != null){
            this.zhiHuStoryDataCollection.addAll(zhiHuStoryItemModels);
        }
        this.zhiHuAdapter.setStoryList(zhiHuStoryDataCollection);
    }

    @Override
    public void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.isLoading = false;
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        ToastUtils.showToastLong(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private final class RecyclerViewScrollListener extends RecyclerView.OnScrollListener{

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
            this.handleFab(firstVisibleItem);
            this.handleLoadMoreData(firstVisibleItem,dy);
        }

        private void handleFab(int firstVisibleItem){
            if (handleFab == null){
                return;
            }

            if (firstVisibleItem > previousVisibleItem){
                handleFab.hideFloatActionButton();
            }else if (firstVisibleItem < previousVisibleItem){
                handleFab.showFloatActionButton();
            }

            previousVisibleItem = firstVisibleItem;
        }

        private void handleLoadMoreData(int firstVisibleItem,int dy){
            //手指向下滑动并且正在加载不做处理
            if (dy < 0 || isLoading){
                return;
            }

            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            if (visibleItemCount + firstVisibleItem >= totalItemCount){
                loadMoreView();
            }
        }
    }
}
