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


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.ljd.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsDetailFragment extends Fragment {

    private final static String LOAD_URL = "com.ljd.news.presentation.view.fragment.LOAD_URL";

    public static NewsDetailFragment newInstance(String url) {

        Bundle bundle = new Bundle();
        bundle.putString(LOAD_URL,url);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public NewsDetailFragment() {
    }

    @BindView(R.id.web_view_container) FrameLayout webViewContainer;

    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_news_deatil, container, false);
        ButterKnife.bind(this,layout);
        this.initWebView();
        return layout;
    }

    private void initWebView(){
        webView = new WebView(getActivity());
        webView.loadUrl(getArguments().getString(LOAD_URL));
        webViewContainer.addView(webView);
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webView.destroy();
        webViewContainer.removeAllViews();
        webView = null;
    }
}
