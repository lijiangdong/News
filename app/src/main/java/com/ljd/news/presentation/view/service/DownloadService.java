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

package com.ljd.news.presentation.view.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ljd.news.presentation.NewsApplication;
import com.ljd.news.presentation.internal.di.HasComponent;
import com.ljd.news.presentation.internal.di.components.DaggerNewsComponent;
import com.ljd.news.presentation.internal.di.components.NewsComponent;
import com.ljd.news.presentation.internal.di.modules.NewsModule;
import com.ljd.news.presentation.presenter.DownloadNewsApkPresenter;
import com.ljd.news.presentation.view.DownloadNewsApkView;

import java.io.File;

import javax.inject.Inject;

public class DownloadService extends Service implements DownloadNewsApkView,HasComponent<NewsComponent> {

    public DownloadService(){

    }

    @Inject
    DownloadNewsApkPresenter downloadNewsApkPresenter;

    public static Intent getCallingIntent(Context context){
        return new Intent(context, DownloadService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.getComponent().inject(this);
        this.downloadNewsApkPresenter.setView(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.downloadNewsApkPresenter.initialize();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
    public void stopService() {
        this.stopSelf();
    }

    @Override
    public NewsComponent getComponent() {
        return DaggerNewsComponent.builder()
                .applicationComponent(((NewsApplication)getApplication()).getApplicationComponent())
                .newsModule(new NewsModule())
                .build();
    }
}
