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

package com.ljd.news.utils;

import java.util.List;

public class WebViewUtils {
    private WebViewUtils() {
    }

    public static final String BASE_URL = "file:///android_asset/";
    public static final String MIME_TYPE = "text/html";
    public static final String ENCODING = "utf-8";
    public static final String FAIL_URL = "http//:daily.zhihu.com/";

    private static final String CSS_LINK_PATTERN = " <link href=\"%s\" type=\"text/css\" rel=\"stylesheet\" />";


    private static final String DIV_IMAGE_PLACE_HOLDER = "class=\"img-place-holder\"";
    private static final String DIV_IMAGE_PLACE_HOLDER_IGNORED = "class=\"img-place-holder-ignored\"";

    public static String buildHtmlWithCss(String html, List<String> cssUrls) {
        StringBuilder result = new StringBuilder();
        for (String cssUrl : cssUrls) {
            result.append(String.format(CSS_LINK_PATTERN, cssUrl));
        }
        result.append(html.replace(DIV_IMAGE_PLACE_HOLDER, DIV_IMAGE_PLACE_HOLDER_IGNORED));
        return result.toString();
    }

    public static String buildHtml(String content, boolean isNightMode) {
        StringBuilder modifiedHtml = new StringBuilder();
        modifiedHtml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.0//EN\" \"http://www.wapforum.org/DTD/xhtml-mobile10.dtd\">"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + "<head>" + "<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=utf-8\"/>"
                + "<meta http-equiv=\"Cache-control\" content=\"public\" />" + "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0\" />"
                + "<link rel=\"stylesheet\" href=\"file:///android_asset/news.css\" type=\"text/css\">"
                + "<script src=\"file:///android_asset/jquery.min.js\"></script>" + "<script src=\"file:///android_asset/info.js\"></script>");
        modifiedHtml.append("<body ");
        if (isNightMode) {
            modifiedHtml.append("class=\'night\'");
        }
        modifiedHtml.append(">");
        modifiedHtml.append(content);
        modifiedHtml.append("</body></html>");
        return modifiedHtml.toString();
    }
}
