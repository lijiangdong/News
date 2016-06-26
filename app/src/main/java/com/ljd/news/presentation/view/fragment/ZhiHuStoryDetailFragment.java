package com.ljd.news.presentation.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.components.ZhiHuComponent;
import com.ljd.news.presentation.model.ZhiHuStoryDetailModel;
import com.ljd.news.presentation.presenter.ZhiHuStoryDetailPresenter;
import com.ljd.news.presentation.view.ZhiHuStoryDetailView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiHuStoryDetailFragment extends BaseFragment implements ZhiHuStoryDetailView{

    @BindView(R.id.zhi_hu_web_view)
    WebView webView;

    @Inject ZhiHuStoryDetailPresenter presenter;

    public ZhiHuStoryDetailFragment() {
        this.setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(ZhiHuComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_zhi_hu_story, container, false);
        ButterKnife.bind(this,layout);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null){
            this.loadZhiHuStoryDetails();
        }
    }

    private void loadZhiHuStoryDetails(){
        this.presenter.initialize();
    }

    @Override
    public void renderZhiHuStoryList(ZhiHuStoryDetailModel zhiHuStoryDetailModel) {
        webView.loadUrl(zhiHuStoryDetailModel.getShareUrl());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
    }
}
