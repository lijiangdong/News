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
package com.ljd.news.utils;

import android.content.Context;
import android.widget.Toast;

import com.ljd.domain.Utils;

public class ToastUtils {

    private static Context mContext;

    private ToastUtils(){

    }

    public static void register(Context context){
        Utils.checkNotNull(context,"context == null");
        mContext = context.getApplicationContext();
    }

    public static void checkContextNotNull(){
        Utils.checkNotNull(mContext,"context == null");
    }

    public static void showToastShort(CharSequence text){
        checkContextNotNull();
        Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(CharSequence text){
        checkContextNotNull();
        Toast.makeText(mContext,text,Toast.LENGTH_LONG).show();
    }

    public static void showToastShort(int text){
        checkContextNotNull();
        Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(int text){
        checkContextNotNull();
        Toast.makeText(mContext,text,Toast.LENGTH_LONG).show();
    }
}
