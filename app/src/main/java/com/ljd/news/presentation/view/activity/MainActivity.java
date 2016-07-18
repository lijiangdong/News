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

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;

import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.internal.di.components.DaggerMainComponent;
import com.ljd.news.presentation.internal.di.components.MainComponent;
import com.ljd.news.presentation.internal.di.modules.ZhiHuModule;
import com.ljd.news.presentation.presenter.CheckNewsUpdatePresenter;
import com.ljd.news.presentation.view.CheckNewsUpdateView;
import com.ljd.news.presentation.view.adapter.MainViewPageAdapter;
import com.ljd.news.presentation.view.component.FloatingActionButton;
import com.ljd.news.presentation.view.component.FloatingActionMenu;
import com.ljd.news.presentation.view.fragment.BaseFragment;
import com.ljd.news.presentation.view.fragment.GuoNeiNewsListFragment;
import com.ljd.news.presentation.view.fragment.WorldNewsListFragment;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryListFragment;
import com.ljd.news.presentation.view.service.DownloadService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent>, BaseFragment.HandleFloatActionButton,CheckNewsUpdateView{

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
        this.getComponent().inject(this);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    private void initView(){
        this.setupToolbar();
        this.setupDrawerContent();
        this.setupViewPager();
        this.setupFloatButton();
        this.setupTabLayout();
    }

    private void setupToolbar(){
        this.toolbar.setTitle(this.getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupViewPager() {
        MainViewPageAdapter adapter = new MainViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ZhiHuStoryListFragment(), getString(R.string.title_zhi_hu));
        adapter.addFragment(new GuoNeiNewsListFragment(), getString(R.string.title_guo_nei_news));
        adapter.addFragment(new WorldNewsListFragment(), getString(R.string.title_world_news));
        this.viewPager.setOffscreenPageLimit(adapter.getCount());
        this.viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent() {
        this.navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            int id = item.getItemId();
            switch (id){
                case R.id.nav_share:
                    this.showShare();
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void setupFloatButton(){
        this.fab.hideMenu(false);
        new Handler().postDelayed(()->{
            fab.showMenu(true);
            fab.setMenuButtonShowAnimation(AnimationUtils.
                    loadAnimation(MainActivity.this, R.anim.show_from_bottom));
            fab.setMenuButtonHideAnimation(AnimationUtils.
                    loadAnimation(MainActivity.this, R.anim.hide_to_bottom));
        }, 300);

        final FloatingActionButton programFab1 = new FloatingActionButton(this);
        programFab1.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab1.setLabelText("sdsdsdsdsds");
        programFab1.setImageResource(R.drawable.ic_edit);
        fab.addMenuButton(programFab1);
        programFab1.setOnClickListener(v -> {
            programFab1.setLabelColors(ContextCompat.getColor(MainActivity.this, R.color.grey),
                    ContextCompat.getColor(MainActivity.this, R.color.light_grey),
                    ContextCompat.getColor(MainActivity.this, R.color.white_transparent));
            programFab1.setLabelTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
        });

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
    public MainComponent getComponent() {
        return DaggerMainComponent.builder()
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

    @Override
    public void updatePromptView(String versionName, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.version_update));
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.update),(dialog, which) -> {
            startService(DownloadService.getCallingIntent(this));
        });
        builder.setNegativeButton(getString(R.string.cancel),(dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("share");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        //oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("News下载地址");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://lijiangdong.com/app/release/apk/news.apk");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        // oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }
}
