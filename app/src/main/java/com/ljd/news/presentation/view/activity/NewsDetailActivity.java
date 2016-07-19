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

package com.ljd.news.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ljd.news.R;
import com.ljd.news.presentation.view.fragment.NewsDetailFragment;

public class NewsDetailActivity extends BaseActivity {

    private static final String URL = "com.ljd.news.presentation.view.activity.URL";

    public static Intent getCallingIntent(Context context,String url){
        Intent intent = new Intent();
        intent.setClass(context,NewsDetailActivity.class);
        intent.putExtra(URL,url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        if (savedInstanceState == null){
            addFragment(R.id.fragment_container,
                    NewsDetailFragment.newInstance(getIntent().getStringExtra(URL)));
        }
    }
}
