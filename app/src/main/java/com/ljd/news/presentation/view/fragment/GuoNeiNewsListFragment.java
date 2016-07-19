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

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.components.MainComponent;
import com.ljd.news.presentation.model.GuoNeiNewsResultModel;
import com.ljd.news.presentation.presenter.GuoNeiNewsListPresenter;
import com.ljd.news.presentation.view.GuoNeiNewsListView;
import com.ljd.news.presentation.view.adapter.GuoNeiNewsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuoNeiNewsListFragment extends BaseFragment implements GuoNeiNewsListView {

    @BindView(R.id.guo_nei_news_recycler) RecyclerView recyclerView;
    @Inject GuoNeiNewsListPresenter presenter;
    @Inject GuoNeiNewsAdapter adapter;

    private LinearLayoutManager linearLayoutManager;
    private boolean isLoading;

    public GuoNeiNewsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_guo_nei_news_list, container, false);
        ButterKnife.bind(this,layout);
        getComponent(MainComponent.class).inject(this);
        setRecyclerView();
        return layout;
    }

    private void setRecyclerView(){
        this.adapter.setOnItemClickListener(guoNeiNewsResultModel ->
                this.onClickRecycleViewItem(guoNeiNewsResultModel));
        this.linearLayoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.addOnScrollListener(new RecyclerViewScrollListener());
    }

    private void onClickRecycleViewItem(GuoNeiNewsResultModel guoNeiNewsResultModel){

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null){
            this.loadGuoNeiNewsList();
        }
    }

    private void loadGuoNeiNewsList(){
        this.presenter.initialize();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        this.isLoading = false;
    }

    @Override
    public void showError(String message) {

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
