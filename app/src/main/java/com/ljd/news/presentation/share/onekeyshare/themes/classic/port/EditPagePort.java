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

package com.ljd.news.presentation.share.onekeyshare.themes.classic.port;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ljd.news.presentation.share.onekeyshare.OnekeyShareThemeImpl;
import com.ljd.news.presentation.share.onekeyshare.themes.classic.EditPage;
import com.ljd.news.presentation.share.onekeyshare.themes.classic.XView;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.R;

import java.io.File;


/** 竖屏的编辑页 */
public class EditPagePort extends EditPage {
	private static final int DESIGN_SCREEN_HEIGHT= 1280;
	private static final int DESIGN_TITLE_HEIGHT = 96;
	private static final int DESIGN_BOTTOM_HEIGHT = 75;
	private static final int DESIGN_LEFT_PADDING = 40;
	private static final int DESIGN_THUMB_HEIGHT = 300;
	private static final int DESIGN_REMOVE_THUMB_HEIGHT = 70;

	public EditPagePort(OnekeyShareThemeImpl impl) {
		super(impl);
	}

	public void onCreate() {
		super.onCreate();

		int screenHeight = R.getScreenHeight(activity);
		float ratio = ((float) screenHeight) / DESIGN_SCREEN_HEIGHT;

		maxBodyHeight = 0;

		llPage = new LinearLayout(activity);
		llPage.setOrientation(LinearLayout.VERTICAL);
		activity.setContentView(llPage);

		rlTitle = new RelativeLayout(activity);
		rlTitle.setBackgroundColor(0xffe6e9ec);
		int titleHeight = (int) (DESIGN_TITLE_HEIGHT * ratio);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, titleHeight);
		llPage.addView(rlTitle, lp);
		initTitle(rlTitle, ratio);

		RelativeLayout rlBody = new RelativeLayout(activity);
		rlBody.setBackgroundColor(0xffffffff);
		lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		llPage.addView(rlBody, lp);
		initBody(rlBody, ratio);

		LinearLayout llShadow = new LinearLayout(activity);
		llShadow.setOrientation(LinearLayout.VERTICAL);
		rlBody.addView(llShadow, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		initShadow(llShadow, ratio);

		llBottom = new LinearLayout(activity);
		llBottom.setOrientation(LinearLayout.VERTICAL);
		lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		llPage.addView(llBottom, lp);
		initBottom(llBottom, ratio);
	}

	private void initTitle(RelativeLayout rlTitle, float ratio) {
		tvCancel = new TextView(activity);
		tvCancel.setTextColor(0xff3b3b3b);
		tvCancel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		tvCancel.setGravity(Gravity.CENTER);
		int resId = R.getStringRes(activity, "ssdk_oks_cancel");
		if (resId > 0) {
			tvCancel.setText(resId);
		}
		int padding = (int) (DESIGN_LEFT_PADDING * ratio);
		tvCancel.setPadding(padding, 0, padding, 0);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		rlTitle.addView(tvCancel, lp);
		tvCancel.setOnClickListener(this);

		TextView tvTitle = new TextView(activity);
		tvTitle.setTextColor(0xff3b3b3b);
		tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
		tvTitle.setGravity(Gravity.CENTER);
		resId = R.getStringRes(activity, "ssdk_oks_multi_share");
		if (resId > 0) {
			tvTitle.setText(resId);
		}
		lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		rlTitle.addView(tvTitle, lp);

		tvShare = new TextView(activity);
		tvShare.setTextColor(0xffff6d11);
		tvShare.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		tvShare.setGravity(Gravity.CENTER);
		resId = R.getStringRes(activity, "ssdk_oks_share");
		if (resId > 0) {
			tvShare.setText(resId);
		}
		tvShare.setPadding(padding, 0, padding, 0);
		lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rlTitle.addView(tvShare, lp);
		tvShare.setOnClickListener(this);
	}

	private void initBody(RelativeLayout rlBody, float ratio) {
		svContent = new ScrollView(activity);
		rlBody.addView(svContent, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		LinearLayout llContent = new LinearLayout(activity);
		llContent.setOrientation(LinearLayout.VERTICAL);
		svContent.addView(llContent, new ScrollView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		etContent = new EditText(activity);
		int padding = (int) (DESIGN_LEFT_PADDING * ratio);
		etContent.setPadding(padding, padding, padding, padding);
		etContent.setBackgroundDrawable(null);
		etContent.setTextColor(0xff3b3b3b);
		etContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
		etContent.setText(sp.getText());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		llContent.addView(etContent, lp);
		etContent.addTextChangedListener(this);

		rlThumb = new RelativeLayout(activity);
		rlThumb.setBackgroundColor(0xff313131);
		int	thumbWidth = (int) (DESIGN_THUMB_HEIGHT * ratio);
		int	xWidth = (int) (DESIGN_REMOVE_THUMB_HEIGHT * ratio);
		lp = new LinearLayout.LayoutParams(thumbWidth, thumbWidth);
		lp.leftMargin = lp.rightMargin = lp.bottomMargin = lp.topMargin = padding;
		llContent.addView(rlThumb, lp);

		aivThumb = new AsyncImageView(activity) {
			public void onImageGot(String url, Bitmap bm) {
				thumb = bm;
				super.onImageGot(url, bm);
			}
		};
		aivThumb.setScaleToCropCenter(true);
		RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(thumbWidth, thumbWidth);
		rlThumb.addView(aivThumb, rllp);
		aivThumb.setOnClickListener(this);
		initThumb(aivThumb);

		xvRemove = new XView(activity);
		xvRemove.setRatio(ratio);
		rllp = new RelativeLayout.LayoutParams(xWidth, xWidth);
		rllp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		rllp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rlThumb.addView(xvRemove, rllp);
		xvRemove.setOnClickListener(this);
	}

	private void initBottom(LinearLayout llBottom, float ratio) {
		LinearLayout llAt = new LinearLayout(activity);
		llAt.setPadding(0, 0, 0, 5);
		llAt.setBackgroundColor(0xffffffff);
		int bottomHeight = (int) (DESIGN_BOTTOM_HEIGHT * ratio);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, bottomHeight);
		llBottom.addView(llAt, lp);

		tvAt = new TextView(activity);
		tvAt.setTextColor(0xff3b3b3b);
		tvAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
		tvAt.setGravity(Gravity.BOTTOM);
		tvAt.setText("@");
		int padding = (int) (DESIGN_LEFT_PADDING * ratio);
		tvAt.setPadding(padding, 0, padding, 0);
		lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		llAt.addView(tvAt, lp);
		tvAt.setOnClickListener(this);
		if (isShowAtUserLayout(platform.getName())) {
			tvAt.setVisibility(View.VISIBLE);
		} else {
			tvAt.setVisibility(View.INVISIBLE);
		}

		tvTextCouter = new TextView(activity);
		tvTextCouter.setTextColor(0xff3b3b3b);
		tvTextCouter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 21);
		tvTextCouter.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
		onTextChanged(etContent.getText(), 0, 0, 0);
		tvTextCouter.setPadding(padding, 0, padding, 0);
		lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		lp.weight = 1;
		llAt.addView(tvTextCouter, lp);

		View v = new View(activity);
		v.setBackgroundColor(0xffcccccc);
		int px_1 = ratio > 1 ? ((int) ratio) : 1;
		lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, px_1);
		llBottom.addView(v, lp);
	}

	private void initShadow(LinearLayout llShadow, float ratio) {
		int px_1 = ratio > 1 ? ((int) ratio) : 1;
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, px_1);

		View v = new View(activity);
		v.setBackgroundColor(0x29000000);
		llShadow.addView(v, lp);

		v = new View(activity);
		v.setBackgroundColor(0x14000000);
		llShadow.addView(v, lp);

		v = new View(activity);
		v.setBackgroundColor(0x07000000);
		llShadow.addView(v, lp);
	}

	private void initThumb(AsyncImageView aivThumb) {
		String imageUrl = sp.getImageUrl();
		String imagePath = sp.getImagePath();
		String[] imageArray = sp.getImageArray();

		Bitmap pic = null;
		rlThumb.setVisibility(View.VISIBLE);
		if(!TextUtils.isEmpty(imagePath) && new File(imagePath).exists()) {
			try {
				pic = BitmapHelper.getBitmap(imagePath);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		if (pic != null) {
			thumb = pic;
			aivThumb.setBitmap(pic);
		} else if (imageArray != null && imageArray.length > 0) {
			if (!TextUtils.isEmpty(imageArray[0]) && new File(imageArray[0]).exists()) {
				try {
					pic = BitmapHelper.getBitmap(imagePath);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}

		if (pic != null) {
			thumb = pic;
			aivThumb.setBitmap(pic);
		} else if (pic == null && !TextUtils.isEmpty(imageUrl)) {
			aivThumb.execute(imageUrl, 0);
		} else {
			rlThumb.setVisibility(View.GONE);
		}
	}

}
