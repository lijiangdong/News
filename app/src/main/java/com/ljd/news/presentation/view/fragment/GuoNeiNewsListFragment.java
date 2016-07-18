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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.components.MainComponent;
import com.ljd.news.presentation.presenter.GuoNeiNewsListPresenter;
import com.ljd.news.presentation.view.GuoNeiNewsListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuoNeiNewsListFragment extends BaseFragment implements GuoNeiNewsListView {

    @BindView(R.id.guo_nei_news_recycler) RecyclerView recyclerView;
    @Inject GuoNeiNewsListPresenter presenter;

    public GuoNeiNewsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_guo_nei_news_list, container, false);
        ButterKnife.bind(this,layout);
        getComponent(MainComponent.class).inject(this);
        presenter.setView(this);
        presenter.initialize();
        return layout;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return getActivity();
    }
}
