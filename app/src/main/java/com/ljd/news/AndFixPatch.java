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

package com.ljd.news;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;
import static com.ljd.news.utils.Utils.checkNotNull;

public class AndFixPatch extends PatchManager {

    private static AndFixPatch andFixPatch;
    private AndFixPatch(Context context) {
        super(context);
    }

    public static AndFixPatch getInstance(Context context){
        checkNotNull(context);
        if (andFixPatch == null){
            synchronized (AndFixPatch.class){
                if (andFixPatch == null) {
                    andFixPatch = new AndFixPatch(context.getApplicationContext());
                }
            }
        }
        return andFixPatch;
    }
}
