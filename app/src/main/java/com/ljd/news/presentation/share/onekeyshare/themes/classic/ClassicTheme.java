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
import android.content.res.Configuration;

import com.ljd.news.presentation.share.onekeyshare.OnekeyShareThemeImpl;
import com.ljd.news.presentation.share.onekeyshare.themes.classic.land.EditPageLand;
import com.ljd.news.presentation.share.onekeyshare.themes.classic.land.PlatformPageLand;
import com.ljd.news.presentation.share.onekeyshare.themes.classic.port.EditPagePort;
import com.ljd.news.presentation.share.onekeyshare.themes.classic.port.PlatformPagePort;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;

/** 九宫格经典主题样式的实现类*/
public class ClassicTheme extends OnekeyShareThemeImpl {

	/** 展示平台列表*/
	protected void showPlatformPage(Context context) {
		PlatformPage page;
		int orientation = context.getResources().getConfiguration().orientation;
		if (orientation == Configuration.ORIENTATION_PORTRAIT) {
			page = new PlatformPagePort(this);
		} else {
			page = new PlatformPageLand(this);
		}
		page.show(context, null);
	}

	/** 展示编辑界面*/
	protected void showEditPage(Context context, Platform platform, ShareParams sp) {
		EditPage page;
		int orientation = context.getResources().getConfiguration().orientation;
		if (orientation == Configuration.ORIENTATION_PORTRAIT) {
			page = new EditPagePort(this);
		} else {
			page = new EditPageLand(this);
		}
		page.setPlatform(platform);
		page.setShareParams(sp);
		page.show(context, null);
	}

}
