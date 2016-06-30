/*
 * Copyright (C) 2016 News
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * author  LiJiangdong
 * email   ljd2038@gmail.com
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
import com.ljd.news.presentation.view.adapter.MainViewPageAdapter;
import com.ljd.news.presentation.view.component.FloatingActionMenu;
import com.ljd.news.presentation.view.fragment.BaseFragment;
import com.ljd.news.presentation.view.fragment.ZhiHuStoryListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<ZhiHuComponent>,
        BaseFragment.HandleFloatActionButton{

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.fab) FloatingActionMenu fab;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.initView();
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
}
