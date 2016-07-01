package com.ljd.news.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateApkEntity {

    @SerializedName("versionCode")
    @Expose
    private Integer versionCode;
    @SerializedName("versionName")
    @Expose
    private String versionName;
    @SerializedName("message")
    @Expose
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
