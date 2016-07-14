package com.ljd.news.presentation.mapper;

import com.ljd.news.domain.UpdateApk;
import com.ljd.news.presentation.internal.di.PerActivity;
import com.ljd.news.presentation.model.UpdateModel;

import javax.inject.Inject;

import static com.ljd.news.utils.Utils.checkNotNull;

@PerActivity
public class NewsModelDataMapper {

    @Inject
    public NewsModelDataMapper() {
    }

    public UpdateModel transform(UpdateApk updateApk){
        checkNotNull(updateApk,"updateApk == null");
        UpdateModel updateModel = new UpdateModel();
        updateModel.setVersionCode(updateApk.getVersionCode());
        updateModel.setVersionName(updateApk.getVersionName());
        updateModel.setMessage(updateApk.getMessage());
        return updateModel;
    }
}
