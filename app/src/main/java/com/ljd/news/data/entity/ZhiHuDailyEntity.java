package com.ljd.news.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ZhiHuDailyEntity {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("stories")
    @Expose
    private List<ZhiHuStoryItemEntity> stories = new ArrayList<ZhiHuStoryItemEntity>();

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The stories
     */
    public List<ZhiHuStoryItemEntity> getStories() {
        return stories;
    }

    /**
     *
     * @param stories
     * The stories
     */
    public void setStories(List<ZhiHuStoryItemEntity> stories) {
        this.stories = stories;
    }


}
