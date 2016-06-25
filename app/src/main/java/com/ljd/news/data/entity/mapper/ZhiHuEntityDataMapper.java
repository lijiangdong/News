package com.ljd.news.data.entity.mapper;

import com.ljd.news.data.entity.ZhiHuDailyEntity;
import com.ljd.news.data.entity.ZhiHuStoryEntity;
import com.ljd.news.data.entity.ZhiHuTopStoryEntity;
import com.ljd.news.domain.ZhiHuDaily;
import com.ljd.news.domain.ZhiHuStory;
import com.ljd.news.domain.ZhiHuTopStory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import static com.ljd.news.utils.Utils.checkNotNull;

@Singleton
public class ZhiHuEntityDataMapper {

    @Inject
    public ZhiHuEntityDataMapper() {
    }

    public ZhiHuDaily transform(ZhiHuDailyEntity zhiHuDailyEntity){
        checkNotNull(zhiHuDailyEntity,"ZhiHuDailyEntity == null");
        ZhiHuDaily zhiHuDaily = new ZhiHuDaily();
        zhiHuDaily.setDate(zhiHuDailyEntity.getDate());
        zhiHuDaily.setStories(transformStories(zhiHuDailyEntity.getStories()));
        zhiHuDaily.setTopStories(transformTopStories(zhiHuDailyEntity.getTopStories()));
        return zhiHuDaily;
    }

    public List<ZhiHuStory> transformStories(List<ZhiHuStoryEntity> zhiHuStoryEntities){
        if (zhiHuStoryEntities == null){
            return null;
        }

        List<ZhiHuStory> zhiHuStories = new ArrayList<>();
        for (ZhiHuStoryEntity zhiHuStoryEntity : zhiHuStoryEntities){
            zhiHuStories.add(transform(zhiHuStoryEntity));
        }
        return zhiHuStories;
    }

    private ZhiHuStory transform(ZhiHuStoryEntity zhiHuStoryEntity){
        if (zhiHuStoryEntity == null){
            return null;
        }

        ZhiHuStory zhiHuStory =new ZhiHuStory();
        zhiHuStory.setGaPrefix(zhiHuStoryEntity.getGaPrefix());
        zhiHuStory.setId(zhiHuStoryEntity.getId());
        zhiHuStory.setImages(zhiHuStoryEntity.getImages());
        zhiHuStory.setTitle(zhiHuStoryEntity.getTitle());
        zhiHuStory.setMultipic(zhiHuStoryEntity.getMultipic());
        zhiHuStory.setType(zhiHuStoryEntity.getType());
        return zhiHuStory;
    }

    private List<ZhiHuTopStory> transformTopStories(List<ZhiHuTopStoryEntity> zhiHuTopStoryEntities){
        if (zhiHuTopStoryEntities == null){
            return null;
        }
        List<ZhiHuTopStory> zhiHuTopStories = new ArrayList<>();
        for (ZhiHuTopStoryEntity zhiHuTopStoryEntity : zhiHuTopStoryEntities){
            zhiHuTopStories.add(transform(zhiHuTopStoryEntity));
        }
        return zhiHuTopStories;
    }

    private ZhiHuTopStory transform(ZhiHuTopStoryEntity zhiHuTopStoryEntity){
        if (zhiHuTopStoryEntity == null){
            return null;
        }
        ZhiHuTopStory zhiHuTopStory = new ZhiHuTopStory();
        zhiHuTopStory.setType(zhiHuTopStoryEntity.getType());
        zhiHuTopStory.setId(zhiHuTopStoryEntity.getId());
        zhiHuTopStory.setTitle(zhiHuTopStoryEntity.getTitle());
        zhiHuTopStory.setGaPrefix(zhiHuTopStoryEntity.getGaPrefix());
        zhiHuTopStory.setImage(zhiHuTopStoryEntity.getImage());
        return zhiHuTopStory;
    }

}
