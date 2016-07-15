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

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.internal.di.components.DaggerZhiHuComponent;
import com.ljd.news.presentation.internal.di.components.ZhiHuComponent;
import com.ljd.news.presentation.internal.di.modules.ZhiHuModule;
import com.ljd.news.presentation.presenter.CheckNewsUpdatePresenter;
import com.ljd.news.presentation.share.onekeyshare.OnekeyShare;
import com.ljd.news.presentation.share.onekeyshare.ShareContentCustomizeCallback;
import com.ljd.news.presentation.view.CheckNewsUpdateView;
import com.ljd.news.presentation.view.adapter.MainViewPageAdapter;
import com.ljd.news.presentation.view.component.FloatingActionMenu;
import com.ljd.news.presentation.view.dialog.UpdatePromptDialog;
import com.ljd.news.presentation.view.fragment.BaseFragment;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryListFragment;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class MainActivity extends BaseActivity implements HasComponent<ZhiHuComponent>,
        BaseFragment.HandleFloatActionButton,CheckNewsUpdateView{

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.fab) FloatingActionMenu fab;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Inject CheckNewsUpdatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.checkUpdate();
        this.initView();
    }

    private void checkUpdate(){
        getComponent().inject(this);
        presenter.setView(this);
        presenter.initialize();
    }

    private void initView(){
        this.setupToolbar();
        this.setupDrawerContent();
        this.setupViewPager();
        this.setupFloatButton();
        this.setupTabLayout();
    }

    private void setupToolbar(){
        toolbar.setTitle(this.getString(R.string.title_zhi_hu));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupViewPager() {
        MainViewPageAdapter adapter = new MainViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ZhiHuStoryListFragment(), "Category 1");
        adapter.addFragment(new ZhiHuStoryListFragment(), "Category 2");
        adapter.addFragment(new ZhiHuStoryListFragment(), "Category 3");
        this.viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent() {
        this.navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            int id = item.getItemId();
            switch (id){
                case R.id.nav_home:
                    break;
                case R.id.nav_share:
                    showShare();
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void setupFloatButton(){
        fab.hideMenu(false);
        new Handler().postDelayed(()->{
            fab.showMenu(true);
            fab.setMenuButtonShowAnimation(AnimationUtils.
                    loadAnimation(MainActivity.this, R.anim.show_from_bottom));
            fab.setMenuButtonHideAnimation(AnimationUtils.
                    loadAnimation(MainActivity.this, R.anim.hide_to_bottom));
        }, 300);
    }

    private void setupTabLayout(){
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public ZhiHuComponent getComponent() {
        return DaggerZhiHuComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .zhiHuModule(new ZhiHuModule())
                .build();
    }

    @Override
    public void hideFloatActionButton() {
        this.fab.hideMenu(true);
    }

    @Override
    public void showFloatActionButton() {
        this.fab.showMenu(true);
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare share = new OnekeyShare();

        //设置一个总开关，用于在分享前若需要授权，则禁用sso功能
        share.disableSSOWhenAuthorize();
        share.setText("text测试");
        // text是分享文本，所有平台都需要这个字段
        share.setTitle("title测试");
        // url仅在微信（包括好友和朋友圈）中使用
        share.setUrl("http://sharesdk.cn");
        share.setTitleUrl("http://sharesdk.cn");
        share.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        share.setImageUrl("http://www.mob.com/mob/images/index/20151109/sharesdk-logo.png");
        //设置用于分享过程中，根据不同平台自定义分享内容的回调
        share.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                //自定义qq实现，只分享图片
                if (QQ.NAME.equals(platform.getName())) {
                   /* paramsToShare.setShareType(Platform.SHARE_IMAGE);*/
                    paramsToShare.setText(null);
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                }
                //自定义实现qq空间，图文分享
                else if (QZone.NAME.equals(platform.getName())) {
                    //text是分享文本，所有平台都需要这个字段
                    paramsToShare.setText("qq空间测试文本");
                    //title标题
                    paramsToShare.setTitle("qq空间测试标题");
                    //titleUrl是标题的网络链接，仅在人人网和QQ空间使用，否则可以不提供
                    paramsToShare.setTitleUrl("http://sharesdk.cn");
                    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                    paramsToShare.setSiteUrl("http://sharesdk.cn");
                    paramsToShare.setImageUrl("http://www.mob.com/mob/images/index/20151109/sharesdk-logo.png");
                    // site是分享此内容的网站名称，仅在QQ空间使用
                    paramsToShare.setSite("qq空间专用");
                    // comment是我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
                    paramsToShare.setComment("qq空间测试评论文本");
                }
                //自定义实现微信，只分享图片
                else if (Wechat.NAME.equals(platform.getName())) {
                    paramsToShare.setShareType(Platform.SHARE_IMAGE);
                }
                //自定义实现微信朋友圈，图文分享
                else if (WechatMoments.NAME.equals(platform.getName())) {
                    paramsToShare.setText("WechatMoments测试文本");
                    paramsToShare.setTitle("WechatMoments测试title");
                    paramsToShare.setUrl("http://sharesdk.cn");
                }
                //自定义微博实现文本链接分享
                else if (SinaWeibo.NAME.equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText("http://www.baidu.com");
                    paramsToShare.setImageUrl(null);
                }

            }
        });
        //**********显示******************
        share.show(this);
    }

    @Override
    public void updatePromptView(String versionName, String message) {
        UpdatePromptDialog.show(this,message);
    }
}
