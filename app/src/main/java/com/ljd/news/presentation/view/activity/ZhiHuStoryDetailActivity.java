package com.ljd.news.presentation.view.activity;

import android.os.Bundle;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.internal.di.components.DaggerZhiHuComponent;
import com.ljd.news.presentation.internal.di.components.ZhiHuComponent;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryFragment;

import butterknife.ButterKnife;

public class ZhiHuStoryDetailActivity extends BaseActivity implements HasComponent<ZhiHuComponent>{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_zhi_hu_story);
        if (savedInstanceState == null){
            addFragment(R.id.fragmentContainer,new ZhiHuStoryFragment());
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
