package com.ljd.news.presentation.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.ljd.news.R;
import com.ljd.news.domain.ZhiHuStoryDetail;
import com.ljd.news.presentation.internal.di.components.ZhiHuComponent;
import com.ljd.news.presentation.presenter.ZhiHuStoryDetailPresenter;
import com.ljd.news.presentation.view.ZhiHuStoryDetailView;
import com.ljd.news.utils.ToastUtils;
import com.ljd.news.utils.WebViewUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiHuStoryDetailFragment extends BaseFragment implements ZhiHuStoryDetailView{

    @BindView(R.id.zhi_hu_web_view) WebView webView;
    @BindView(R.id.rl_progress) RelativeLayout progressView;

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
        setWebView();
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }

    private void setWebView(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(context().getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoading();
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter.setView(this);
        if (savedInstanceState == null){
            this.loadZhiHuStoryDetails();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onResume();
    }

    private void loadZhiHuStoryDetails(){
        this.presenter.initialize();
    }

    @Override
    public void renderZhiHuStoryDetailByUrl(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void renderZhiHuStoryDetailByHtml(ZhiHuStoryDetail zhiHuStoryDetail) {
        String data = WebViewUtils.buildHtmlWithCss(zhiHuStoryDetail.getBody(),
                zhiHuStoryDetail.getCss());
        webView.loadDataWithBaseURL(WebViewUtils.BASE_URL,data,WebViewUtils.MIME_TYPE,
                WebViewUtils.ENCODING, WebViewUtils.FAIL_URL);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        this.presenter.destroy();
    }

    @Override
    public void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
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
}
