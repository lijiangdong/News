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

package com.ljd.news.presentation;

import com.ljd.news.BuildConfig;

public class NewsConfig {
    public static final boolean IS_DEBUG = BuildConfig.DEBUG;
    public static final String APPLICATION_ID = BuildConfig.APPLICATION_ID;
    public static final String BUILD_TYPE = BuildConfig.BUILD_TYPE;
    public static final String FLAVOR = BuildConfig.FLAVOR;
    public static final int VERSION_CODE = BuildConfig.VERSION_CODE;
    public static final String VERSION_NAME = BuildConfig.VERSION_NAME;
}
