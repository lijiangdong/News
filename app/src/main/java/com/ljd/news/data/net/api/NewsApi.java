package com.ljd.news.data.net.api;

import com.ljd.news.data.entity.UpdateApkEntity;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

public interface NewsApi {

    @GET("/app/release/update.json")
    Observable<UpdateApkEntity> checkUpdateApk();

    @GET("http://lijiangdong.com/app/release/apk/news.apk")
    Observable<ResponseBody> downloadApk();
}
