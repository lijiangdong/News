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

public class WeChatNewsEntity {

    @SerializedName("result")
    @Expose
    private List<WeChatNewsResultEntity> result = new ArrayList<WeChatNewsResultEntity>();
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("reason")
    @Expose
    private String reason;

    /**
     *
     * @return
     * The result
     */
    public List<WeChatNewsResultEntity> getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(List<WeChatNewsResultEntity> result) {
        this.result = result;
    }

    /**
     *
     * @return
     * The errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     *
     * @param errorCode
     * The error_code
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     *
     * @return
     * The reason
     */
    public String getReason() {
        return reason;
    }

    /**
     *
     * @param reason
     * The reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}
