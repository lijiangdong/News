package com.ljd.news.presentation.exception;

import android.content.Context;

import com.ljd.news.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class ErrorMessageFactory {

    private ErrorMessageFactory() {

    }

    public static String create(Context context, Exception exception) {

        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof ConnectException) {
            message = context.getString(R.string.exception_message_no_connection);
        }else if (exception instanceof SocketTimeoutException){
            message = context.getString(R.string.exception_message_connect_timeout);
        }else if (exception instanceof RuntimeException){

        }

        return message;
    }
}
