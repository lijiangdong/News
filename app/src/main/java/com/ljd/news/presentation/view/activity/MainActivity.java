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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.alipay.euler.andfix.patch.PatchManager;
import com.ljd.news.R;
import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.internal.di.components.DaggerMainComponent;
import com.ljd.news.presentation.internal.di.components.MainComponent;
import com.ljd.news.presentation.internal.di.modules.ZhiHuModule;
import com.ljd.news.presentation.presenter.CheckNewsUpdatePresenter;
import com.ljd.news.presentation.view.CheckNewsUpdateView;
import com.ljd.news.presentation.view.adapter.MainViewPageAdapter;
import com.ljd.news.presentation.view.fragment.BaseFragment;
import com.ljd.news.presentation.view.fragment.WeChatNewsListFragment;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryListFragment;
import com.ljd.news.presentation.view.service.DownloadService;
import com.ljd.news.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends BaseActivity implements HasComponent<MainComponent>,
        BaseFragment.HandleFloatActionButton,CheckNewsUpdateView{

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.viewpager) ViewPager viewPager;
//    @BindView(R.id.fab) FloatingActionMenu fab;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Inject CheckNewsUpdatePresenter presenter;
    @Inject PatchManager patchManager;

    private boolean isUpdate;
    private AlertDialog updateDialog;

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
//        this.setupFloatButton();
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
        adapter.addFragment(new WeChatNewsListFragment(), getString(R.string.title_we_chat_news));
        this.viewPager.setOffscreenPageLimit(adapter.getCount());
        this.viewPager.setAdapter(adapter);
    }

/*    private void setupFloatButton(){
        this.fab.setVisibility(View.GONE);
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

    }*/

    private void setupTabLayout(){
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    private void setupDrawerContent() {
        this.navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            int id = item.getItemId();
            switch (id){
                case R.id.nav_zhi_hu:
                    this.navigateToZhiHu();
                    break;
                case R.id.nav_we_chat:
                    this.navigateToWeChat();
                    break;
                case R.id.nav_share:
                    this.navigateToShowShare();
                    break;
                case R.id.nav_feedback:
                    this.navigateToFeedback();
                    break;
                case R.id.nav_update:
                    this.navigateToUpdate();
                    break;
                case R.id.clear_patch:
                    patchManager.removeAllPatch();
                    break;
            }
            this.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void navigateToZhiHu(){
        this.viewPager.setCurrentItem(0);
    }

    private void navigateToWeChat(){
        this.viewPager.setCurrentItem(1);
    }

    private void navigateToShowShare() {
//        crash();
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

    private void navigateToFeedback(){
//        crash();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "ljd2038@gmail.com", null));
        startActivity(Intent.createChooser(intent, "选择邮件客户端:"));
    }

    private void navigateToUpdate(){
//        crash();
        if (isUpdate && updateDialog != null){
            this.updateDialog.show();
        }else {
            ToastUtils.showToastLong(getString(R.string.not_find_update));
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
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
//        this.fab.hideMenu(true);
    }

    @Override
    public void showFloatActionButton() {
//        this.fab.showMenu(true);
    }

    @Override
    public void updatePromptView(String versionName, String message) {
        this.isUpdate = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.version_update));
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.update),(dialog, which) -> {
            startService(DownloadService.getCallingIntent(MainActivity.this));
        });
        builder.setNegativeButton(getString(R.string.cancel),(dialog, which) -> {
            dialog.dismiss();
        });
        this.updateDialog = builder.create();
        this.updateDialog.show();
    }

    private void crash(){
        String str = null;
        str.equals("aa");
    }
}
