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

package com.ljd.news.presentation;

import android.app.Application;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;
import com.ljd.news.presentation.internal.di.components.ApplicationComponent;
import com.ljd.news.presentation.internal.di.components.DaggerApplicationComponent;
import com.ljd.news.presentation.internal.di.modules.ApplicationModule;
import com.ljd.news.utils.ToastUtils;

import java.io.IOException;

import javax.inject.Inject;

import cn.sharesdk.framework.ShareSDK;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public class NewsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Inject
    PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initToastUtils();
//        initLeakCanary();
        initTimber();
//        initStetho();
        initShare();
        initAndFix();
    }

    private void initAndFix(){
        patchManager.init(NewsConfig.VERSION_NAME);
        patchManager.loadPatch();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/hack.apatch";
        try {
            patchManager.addPatch(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        patchManager.addPatch();
//        Intent patchDownloadIntent = new Intent(this, PatchDownloadIntentService.class);
//        patchDownloadIntent.putExtra("url", "http://xxx/patch/app-release-fix-shine.apatch");
//        startService(patchDownloadIntent);
    }

    public PatchManager getPatchManager() {
        return patchManager;
    }

    private void initToastUtils(){
        ToastUtils.register(this);
    }

//    private void initLeakCanary(){
//        LeakCanary.install(this);
//    }

    private void initTimber(){
        if (NewsConfig.IS_DEBUG){
            Timber.plant(new DebugTree());
        }
    }
    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

//    private void initStetho(){
//        Stetho.initializeWithDefaults(this);
//    }

    private void initShare(){
        ShareSDK.initSDK(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
