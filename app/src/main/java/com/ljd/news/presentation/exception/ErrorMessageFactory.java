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

package com.ljd.news.presentation.exception;

import android.content.Context;

import com.ljd.news.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

public class ErrorMessageFactory {

    private ErrorMessageFactory() {

    }

    public static String create(Context context, Exception exception) {

        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof ConnectException) {
            message = context.getString(R.string.exception_message_no_connection);
        }else if (exception instanceof SocketTimeoutException){
            message = context.getString(R.string.exception_message_connect_timeout);
        }else if (exception instanceof HttpException){
            message = httpException(exception,context,message);
        }

        return message;
    }

    private static String httpException(Exception e,Context context,String message){

        HttpException httpException = (HttpException)e;

        if (httpException.code() == 504){
            message = context.getString(R.string.exception_message_not_net_connect);
        }

        return message;
    }
}
