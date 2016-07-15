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

import com.ljd.news.presentation.share.onekeyshare.OnekeyShareThemeImpl;
import com.ljd.news.presentation.share.onekeyshare.themes.classic.PlatformPage;
import com.ljd.news.presentation.share.onekeyshare.themes.classic.PlatformPageAdapter;

import java.util.ArrayList;


/** 竖屏的九宫格页面 */
public class PlatformPagePort extends PlatformPage {

	public PlatformPagePort(OnekeyShareThemeImpl impl) {
		super(impl);
	}

	public void onCreate() {
		requestPortraitOrientation();
		super.onCreate();
	}

	protected PlatformPageAdapter newAdapter(ArrayList<Object> cells) {
		return new PlatformPageAdapterPort(this, cells);
	}

}
