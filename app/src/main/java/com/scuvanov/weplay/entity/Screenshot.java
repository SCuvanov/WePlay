package com.scuvanov.weplay.entity;

public class Screenshot {

    private String url, cloudinary_id;
    private int width, height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCloudinary_id() {
        return cloudinary_id;
    }

    public void setCloudinary_id(String cloudinary_id) {
        this.cloudinary_id = cloudinary_id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
