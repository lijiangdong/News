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
