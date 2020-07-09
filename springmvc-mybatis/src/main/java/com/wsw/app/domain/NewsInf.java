package com.wsw.app.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * news_inf
 * @author 
 */
public class NewsInf implements Serializable {
    private Integer newsId;

    private String newsTitle;

    private String newsContent;

    private Map<String,Object> meta = new HashMap<>();

    @Override
    public String toString() {
        return "NewsInf{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", meta=" + meta +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Map getMeta() {
        return meta;
    }

    public void setMeta(Map meta) {
        this.meta = meta;
    }
}