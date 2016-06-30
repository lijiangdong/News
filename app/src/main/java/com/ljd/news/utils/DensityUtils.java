package com.ljd.news.utils;

import android.content.Context;

public final class DensityUtils {

    private DensityUtils() {
    }

    public static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }

}
