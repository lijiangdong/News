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

package com.ljd.news.presentation.share.onekeyshare.themes.classic;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

/** 在At好友页面中，下拉刷新列表头部的旋转箭头 */
public class RotateImageView extends ImageView {
	private float rotation;

	public RotateImageView(Context context) {
		super(context);
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
		invalidate();
	}

	protected void onDraw(Canvas canvas) {
		canvas.rotate(rotation, getWidth() / 2, getHeight() / 2);
		super.onDraw(canvas);
	}

}
