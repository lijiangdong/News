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

package com.ljd.news.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ZhiHuStoryDetailEntity {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("image_source")
    @Expose
    private String imageSource;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("js")
    @Expose
    private List<Object> js = new ArrayList<Object>();
    @SerializedName("ga_prefix")
    @Expose
    private String gaPrefix;
    @SerializedName("images")
    @Expose
    private List<String> images = new ArrayList<String>();
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("css")
    @Expose
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
     * The imageSource
     */
    public String getImageSource() {
        return imageSource;
    }

    /**
     *
     * @param imageSource
     * The image_source
     */
    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
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
     * The js
     */
    public List<Object> getJs() {
        return js;
    }

    /**
     *
     * @param js
     * The js
     */
    public void setJs(List<Object> js) {
        this.js = js;
    }

    /**
     *
     * @return
     * The gaPrefix
     */
    public String getGaPrefix() {
        return gaPrefix;
    }

    /**
     *
     * @param gaPrefix
     * The ga_prefix
     */
    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    /**
     *
     * @return
     * The images
     */
    public List<String> getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(List<String> images) {
        this.images = images;
    }

    /**
     *
     * @return
     * The type
     */
    public Integer getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
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
