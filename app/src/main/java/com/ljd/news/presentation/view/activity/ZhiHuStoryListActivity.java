package com.ljd.news.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.internal.di.components.DaggerZhiHuComponent;
import com.ljd.news.presentation.internal.di.components.ZhiHuComponent;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryListFragment;

public class ZhiHuStoryListActivity extends BaseActivity implements HasComponent<ZhiHuComponent> {

    public static Intent getCallingIntent(Context context){
        return new Intent(context,ZhiHuStoryListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu_story_list);
        if (savedInstanceState == null){
            addFragment(R.id.fragmentContainer,new ZhiHuStoryListFragment());
        }
    }

    @Override
    public ZhiHuComponent getComponent() {
        return DaggerZhiHuComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }
}
