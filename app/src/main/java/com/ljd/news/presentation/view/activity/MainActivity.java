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
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.ljd.news.presentation.presenter.DownloadNewsApkPresenter;
import com.ljd.news.presentation.view.CheckNewsUpdateView;
import com.ljd.news.presentation.view.DownloadNewsApkView;
import com.ljd.news.presentation.view.adapter.MainViewPageAdapter;
import com.ljd.news.presentation.view.component.FloatingActionMenu;
import com.ljd.news.presentation.view.fragment.BaseFragment;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryListFragment;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<ZhiHuComponent>,
        BaseFragment.HandleFloatActionButton,CheckNewsUpdateView,DownloadNewsApkView{

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.fab) FloatingActionMenu fab;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Inject CheckNewsUpdatePresenter presenter;
    @Inject DownloadNewsApkPresenter downloadNewsApkPresenter;

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
        downloadNewsApkPresenter.setView(this);
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


    @Override
    public void updatePromptView(String versionName, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.version_update));
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.update),(dialog, which) -> {
            downloadNewsApkPresenter.initialize();
        });
        builder.setNegativeButton(getString(R.string.cancel),(dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }

    @Override
    public void installNewsApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

    @Override
    public Context context() {
        return this;
    }
}
