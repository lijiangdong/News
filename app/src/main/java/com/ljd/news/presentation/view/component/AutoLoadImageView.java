package com.ljd.news.presentation.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AutoLoadImageView extends ImageView {

    private Context context;

    public AutoLoadImageView(Context context) {
        super(context);
        this.context = context;
    }

    public AutoLoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public AutoLoadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void loadUrl(String url){
        Picasso.with(context).load(url).into(this);
    }
}
