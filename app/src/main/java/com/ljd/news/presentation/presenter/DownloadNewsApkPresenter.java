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

package com.ljd.news.presentation.presenter;

import android.os.Environment;

import com.ljd.news.domain.interactor.ResponseSubscriber;
import com.ljd.news.domain.interactor.UseCase;
import com.ljd.news.presentation.internal.di.PerService;
import com.ljd.news.presentation.view.DownloadNewsApkView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.ResponseBody;

@PerService
public class DownloadNewsApkPresenter implements Presenter<DownloadNewsApkView> {

    private UseCase downloadApkUseCase;
    private DownloadNewsApkView downloadNewsApkView;

    @Inject
    public DownloadNewsApkPresenter(@Named("downloadNewsApk") UseCase downloadApkUseCase) {
        this.downloadApkUseCase = downloadApkUseCase;
    }

    @Override
    public void setView(DownloadNewsApkView downloadNewsApkView) {
        this.downloadNewsApkView = downloadNewsApkView;
    }

    @Override
    public void destroy() {

    }

    public void initialize(){
        this.download();
    }

    private void download(){
        this.downloadApkUseCase.execute(new DownloadApkSubscriber());
    }

    private void writeToSdcardAndInstall(ResponseBody responseBody){
        try {
            final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
            final String FILE_NAME = "news.apk";
            InputStream is = responseBody.byteStream();
            File file = new File(PATH,FILE_NAME);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
            fos.close();
            bis.close();
            is.close();
            this.downloadNewsApkView.installNewsApk(new File(PATH,FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final class DownloadApkSubscriber extends ResponseSubscriber<ResponseBody>{

        @Override
        protected void onSuccess(ResponseBody responseBody) {
            writeToSdcardAndInstall(responseBody);
            downloadNewsApkView.stopService();
        }

        @Override
        protected void onFailure(Throwable e) {
            downloadNewsApkView.stopService();
        }
    }
}
