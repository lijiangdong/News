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
import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.internal.di.components.DaggerMainComponent;
import com.ljd.news.presentation.internal.di.components.MainComponent;
import com.ljd.news.presentation.internal.di.modules.ZhiHuModule;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryDetailFragment;

import butterknife.ButterKnife;

public class ZhiHuStoryDetailActivity extends BaseActivity implements HasComponent<MainComponent>{

    private static final String INTENT_EXTRA_PARAM_STORY_ID = "com.ljd.news.presentation.view.activity.INTENT_PARAM_STORY_ID";
    private static final String INSTANCE_STATE_PARAM_STORY_ID = "com.ljd.news.presentation.view.activity.STATE_PARAM_STORY_ID";

    private int storyId;

    public static Intent getCallingIntent(Context context, int userId) {
        Intent callingIntent = new Intent(context, ZhiHuStoryDetailActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_STORY_ID, userId);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu_story);
        ButterKnife.bind(this);
        initActivity(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null){
            outState.putInt(INSTANCE_STATE_PARAM_STORY_ID,this.storyId);
        }
        super.onSaveInstanceState(outState);
    }

    private void initActivity(Bundle savedInstanceState){
        if (savedInstanceState == null){
            this.storyId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_STORY_ID,-1);
            addFragment(R.id.fragmentContainer,new ZhiHuStoryDetailFragment());
        }else {
            this.storyId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_STORY_ID);
        }
    }

    @Override
    public MainComponent getComponent() {
        return DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .zhiHuModule(new ZhiHuModule(this.storyId))
                .build();
    }

}
