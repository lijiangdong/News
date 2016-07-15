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

package com.ljd.news.data.entity.mapper;

import com.ljd.news.data.entity.UpdateApkEntity;
import com.ljd.news.domain.UpdateApk;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.ljd.news.utils.Utils.checkNotNull;

@Singleton
public class NewsEntityDataMapper {

    @Inject
    public NewsEntityDataMapper(){

    }

    public UpdateApk transform(UpdateApkEntity updateApkEntity){
        checkNotNull(updateApkEntity,"updateApkEntity == null");
        UpdateApk updateApk = new UpdateApk();
        updateApk.setMessage(updateApkEntity.getMessage());
        updateApk.setVersionCode(updateApkEntity.getVersionCode());
        updateApk.setVersionName(updateApkEntity.getVersionName());
        return updateApk;
    }
}
