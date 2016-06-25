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
    private List<ZhiHuStoryEntity> stories = new ArrayList<ZhiHuStoryEntity>();
    @SerializedName("top_stories")
    @Expose
    private List<ZhiHuTopStoryEntity> topStories = new ArrayList<ZhiHuTopStoryEntity>();

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
    public List<ZhiHuStoryEntity> getStories() {
        return stories;
    }

    /**
     *
     * @param stories
     * The stories
     */
    public void setStories(List<ZhiHuStoryEntity> stories) {
        this.stories = stories;
    }

    /**
     *
     * @return
     * The topStories
     */
    public List<ZhiHuTopStoryEntity> getTopStories() {
        return topStories;
    }

    /**
     *
     * @param topStories
     * The top_stories
     */
    public void setTopStories(List<ZhiHuTopStoryEntity> topStories) {
        this.topStories = topStories;
    }
}
