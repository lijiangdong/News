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
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.components.MainComponent;
import com.ljd.news.presentation.model.WeChatNewsResultModel;
import com.ljd.news.presentation.presenter.WeChatNewsListPresenter;
import com.ljd.news.presentation.view.WeChatNewsListView;
import com.ljd.news.presentation.view.activity.NewsDetailActivity;
import com.ljd.news.presentation.view.adapter.WeChatNewsAdapter;
import com.ljd.news.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeChatNewsListFragment extends BaseFragment implements WeChatNewsListView {

    @BindView(R.id.we_chat_news_recycler) RecyclerView recyclerView;
    @BindView(R.id.rl_progress) RelativeLayout progressView;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout refreshLayout;

    @Inject WeChatNewsListPresenter presenter;
    @Inject WeChatNewsAdapter adapter;

    private LinearLayoutManager linearLayoutManager;
    private Collection<WeChatNewsResultModel> weChatNewsResultModels = new ArrayList<>();
    private boolean isLoading;

    public WeChatNewsListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_we_chat_news_list, container, false);
        ButterKnife.bind(this,layout);
        getComponent(MainComponent.class).inject(this);
        this.setUpView();
        return layout;
    }

    private void setUpView(){
        this.setRecyclerView();
        this.setRefreshLayout();
    }

    private void setRecyclerView(){
        this.adapter.setOnItemClickListener(weChatNewsResultModel ->
                this.onClickRecycleViewItem(weChatNewsResultModel));
        this.linearLayoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.addOnScrollListener(new RecyclerViewScrollListener());
    }

    private void onClickRecycleViewItem(WeChatNewsResultModel weChatNewsResultModel){
        startActivity(NewsDetailActivity.getCallingIntent(getActivity(),
                weChatNewsResultModel.getUrl(),weChatNewsResultModel.getTitle()));
    }

    private void setRefreshLayout(){
        this.refreshLayout.setColorSchemeColors(Color.BLUE);
        this.refreshLayout.setOnRefreshListener(() -> {
            this.refreshLayout.setRefreshing(true);
            this.presenter.initialize();
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null){
            this.loadWeChatNewsList();
        }
    }

    private void loadWeChatNewsList(){
        this.presenter.initialize();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.recyclerView.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }

    @Override
    public void renderWeChatNewsList(Collection<WeChatNewsResultModel> weChatNewsResultModels) {
        this.weChatNewsResultModels.addAll(weChatNewsResultModels);
        this.adapter.setWeChatNewsList(this.weChatNewsResultModels);
    }

    @Override
    public void showLoading() {
        this.progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.isLoading = false;
        this.refreshLayout.setRefreshing(false);
        this.progressView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        ToastUtils.showToastLong(message);
    }

    @Override
    public Context context() {
        return getActivity();
    }

    private void loadMoreView(){
        this.isLoading = true;
        this.presenter.initialize();
    }

    private final class RecyclerViewScrollListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            this.handleLoadMoreData(dy);
        }

        private void handleLoadMoreData(int dy){
            int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
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
