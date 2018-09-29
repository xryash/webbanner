package com.woopig.entity;


import java.io.Serializable;

public class Banner implements Serializable {

    private String id;

    private String imgSrc;

    private Integer  width;

    private Integer  height;

    private String targetUrl;

    private String langId;


    public Banner() {
    }

    public Banner(String id, String imgSrc, int width, int height, String targetUrl, String langId) {
        this.id = id;
        this.imgSrc = imgSrc;
        this.width = width;
        this.height = height;
        this.targetUrl = targetUrl;
        this.langId = langId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }


    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", targetUrl='" + targetUrl + '\'' +
                ", langId='" + langId + '\'' +
                '}';
    }
}
