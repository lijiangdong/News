/*
 * Copyright (C) 2026 News
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

package com.ljd.data.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by ljd on 6/21/16.
 */
public abstract class ResponseSubscriber<T> extends Subscriber<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException){
            connectTimeOutException();
        }else if (e instanceof ConnectException){
            connectNetException();
        }else if (e instanceof RuntimeException){
            runtimeException();
        }
        onFailure(e);
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFailure(Throwable e);

    private void connectTimeOutException(){
        //TODO:连接服务器超时处理
    }

    private void connectNetException(){
        //TODO:连接网络异常
    }

    private void runtimeException(){
        //TODO:运行时异常
    }

}
