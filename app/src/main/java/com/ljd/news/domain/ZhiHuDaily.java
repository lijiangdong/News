package com.ljd.news.domain;

import java.util.ArrayList;
import java.util.List;

public class ZhiHuDaily {

    private String date;
    private List<ZhiHuStory> stories = new ArrayList<ZhiHuStory>();
    private List<ZhiHuTopStory> topStories = new ArrayList<ZhiHuTopStory>();

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
    public List<ZhiHuStory> getStories() {
        return stories;
    }

    /**
     *
     * @param stories
     * The stories
     */
    public void setStories(List<ZhiHuStory> stories) {
        this.stories = stories;
    }

    /**
     *
     * @return
     * The topStories
     */
    public List<ZhiHuTopStory> getTopStories() {
        return topStories;
    }


    public void setTopStories(List<ZhiHuTopStory> topStories) {
        this.topStories = topStories;
    }
}
