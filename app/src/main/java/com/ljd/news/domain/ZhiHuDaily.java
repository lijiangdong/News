package com.ljd.news.domain;

import java.util.ArrayList;
import java.util.List;

public class ZhiHuDaily {

    private String date;
    private List<ZhiHuStoryItem> stories = new ArrayList<ZhiHuStoryItem>();

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
    public List<ZhiHuStoryItem> getStories() {
        return stories;
    }

    /**
     *
     * @param stories
     * The stories
     */
    public void setStories(List<ZhiHuStoryItem> stories) {
        this.stories = stories;
    }

}
