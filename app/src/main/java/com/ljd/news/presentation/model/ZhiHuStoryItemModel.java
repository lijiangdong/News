package com.ljd.news.presentation.model;

public class ZhiHuStoryItemModel {

    private String image;
    private Integer type;
    private Integer id;
    private String gaPrefix;
    private String title;
    private Boolean multipic;

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
     * The images
     */
    public void setImage(String image) {
        this.image = image;
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
     * The multipic
     */
    public Boolean getMultipic() {
        return multipic;
    }

    /**
     *
     * @param multipic
     * The multipic
     */
    public void setMultipic(Boolean multipic) {
        this.multipic = multipic;
    }
}
