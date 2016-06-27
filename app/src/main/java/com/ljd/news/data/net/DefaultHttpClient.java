package com.ljd.news.data.net;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ljd.news.utils.NetWorkUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Singleton
public class DefaultHttpClient implements HttpClient{

    private static final int CONNECT_TIMEOUT = 10 * 1000;           //网络连接超时时间10秒
    private static final int CACHE_SIZE = 10 * 1024 * 1024;         // 10 MiB
    private static final int MAX_SIZE_NET = 60;                     //在线缓存在1分钟
    private static final int MAX_SIZE_DISK = 60 * 60 * 24 * 28;     //离线缓存保存4周

    private File httpCacheDirectory;
    private Cache cache;
    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR;

    @Inject
    public DefaultHttpClient(Context context){
        this.httpCacheDirectory = new File(context.getCacheDir(), "netCache");
        this.cache = new Cache(httpCacheDirectory, CACHE_SIZE);
        this.REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtils.isNetWorkAvailable(context)) {
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + MAX_SIZE_NET)
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + MAX_SIZE_DISK)
                        .build();
            }
        };
    }

    @Override
    public OkHttpClient getOKHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new StethoInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();
    }
}
