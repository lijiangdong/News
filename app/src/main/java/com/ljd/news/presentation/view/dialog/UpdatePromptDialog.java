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

package com.ljd.news.presentation.view.dialog;

import android.app.AlertDialog;
import android.content.Context;

public class UpdatePromptDialog {
    private static  AlertDialog.Builder builder;

    public static void show(Context context,String message){
        builder = new AlertDialog.Builder(context);
        builder.setTitle("版本更新");
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setPositiveButton("更新",(dialog, which) -> {
                    //TODO:下载更新操作
                });
        builder.setNegativeButton("取消",(dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }
}
