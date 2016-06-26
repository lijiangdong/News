package com.ljd.news.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.internal.di.components.DaggerZhiHuComponent;
import com.ljd.news.presentation.internal.di.components.ZhiHuComponent;
import com.ljd.news.presentation.internal.di.modules.ZhiHuModule;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryFragment;

import butterknife.ButterKnife;

public class ZhiHuStoryDetailActivity extends BaseActivity implements HasComponent<ZhiHuComponent>{

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
        ButterKnife.bind(this);
        setContentView(R.layout.activity_zhi_hu_story);
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
            addFragment(R.id.fragmentContainer,new ZhiHuStoryFragment());
        }else {
            this.storyId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_STORY_ID);
        }
    }

    @Override
    public ZhiHuComponent getComponent() {
        return DaggerZhiHuComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .zhiHuModule(new ZhiHuModule(this.storyId))
                .build();
    }

}
