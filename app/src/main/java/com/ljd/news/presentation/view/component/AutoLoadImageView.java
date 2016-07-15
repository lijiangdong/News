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

package com.ljd.news.presentation.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ljd.news.R;


public class AutoLoadImageView extends ImageView {

    private Context context;
    private int ratio;           //设置AutoLoadImageView宽和高的比例

    public AutoLoadImageView(Context context) {
        super(context);
        setContext(context);
    }

    public AutoLoadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setContext(context);
        setAttrs(attrs);
    }

    public AutoLoadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContext(context);
        setAttrs(attrs);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void setAttrs(AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoLoadImageView);
        ratio = typedArray.getInteger(R.styleable.AutoLoadImageView_ratio,0);
        typedArray.recycle();
    }

    public void loadUrl(String url){
        Glide.with(context).load(url).into(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (ratio == 0){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec * ratio);
        }

    }
}
