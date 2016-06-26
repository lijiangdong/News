package com.ljd.news.presentation.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.components.ZhiHuComponent;
import com.ljd.news.presentation.model.ZhiHuStoryItemModel;
import com.ljd.news.presentation.presenter.ZhiHuStoryListPresenter;
import com.ljd.news.presentation.view.ZhiHuStoryListView;
import com.ljd.news.presentation.view.activity.ZhiHuStoryDetailActivity;
import com.ljd.news.presentation.view.adapter.ZhiHuAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiHuStoryListFragment extends BaseFragment  implements ZhiHuStoryListView{


    @BindView(R.id.zhi_hu_recycler_view) RecyclerView recyclerView;

    @Inject ZhiHuAdapter zhiHuAdapter;
    @Inject ZhiHuStoryListPresenter zhiHuStoryListPresenter;

    public ZhiHuStoryListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ZhiHuComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_zhi_hu_story_list, container, false);
        ButterKnife.bind(this,layout);
        setRecyclerView();
        return layout;
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

    private void setRecyclerView(){
        this.zhiHuAdapter.setOnItemClickListener(zhiHuStoryItemModel -> {
            if (navigator != null){
                navigator.navigateToActivity(ZhiHuStoryDetailActivity
                        .getCallingIntent(getActivity(),zhiHuStoryItemModel.getId()));
            }

        });
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(zhiHuAdapter);
    }

    @Override
    public void renderZhiHuStoryList(Collection<ZhiHuStoryItemModel> zhiHuStoryItemModels) {
        if (zhiHuStoryItemModels != null){
            this.zhiHuAdapter.setStoryList(zhiHuStoryItemModels);
        }
    }

}
