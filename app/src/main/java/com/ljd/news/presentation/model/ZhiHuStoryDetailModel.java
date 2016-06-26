package com.ljd.news.presentation.model;

import java.util.ArrayList;
import java.util.List;

public class ZhiHuStoryDetailModel {

    private String body;
    private String title;
    private String image;
    private String shareUrl;
    private List<String> css = new ArrayList<String>();

    /**
     *
     * @return
     * The body
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The shareUrl
     */
    public String getShareUrl() {
        return shareUrl;
    }

    /**
     *
     * @param shareUrl
     * The share_url
     */
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    /**
     *
     * @return
     * The css
     */
    public List<String> getCss() {
        return css;
    }

    /**
     *
     * @param css
     * The css
     */
    public void setCss(List<String> css) {
        this.css = css;
    }

}
