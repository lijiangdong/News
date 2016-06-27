package com.ljd.news.utils;

public final class Utils {

    private Utils() {
    }

    public static <T> T checkNotNull(T object, String message){
        if (object == null){
            throw new NullPointerException(message);
        }
        return object;
    }
}
