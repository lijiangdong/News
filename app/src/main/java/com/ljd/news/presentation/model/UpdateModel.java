package com.ljd.news.presentation.model;

public class UpdateModel {
    private Integer versionCode;
    private String versionName;
    private String message;

    /**
     *
     * @return
     * The versionCode
     */
    public Integer getVersionCode() {
        return versionCode;
    }

    /**
     *
     * @param versionCode
     * The versionCode
     */
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    /**
     *
     * @return
     * The versionName
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     *
     * @param versionName
     * The versionName
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
