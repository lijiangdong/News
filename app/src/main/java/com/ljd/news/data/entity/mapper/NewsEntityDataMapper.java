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
