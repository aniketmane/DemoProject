package com.example.aniket.testdemo.models;

/**
 * ThumbnailImageModel.java .
 * @author Aniket Mane
 * @version 1.0
 */
public class ThumbnailImageModel {

    private String urlSource;
    private int height;
    private int width;

    // get the image source url
    public String getUrlSource() {
        return urlSource;
    }

    /**
     * @param String urlSource
     * @return void
     * Set the url to local object
     */
    public void setUrlSource(String urlSource) {
        this.urlSource = urlSource;
    }

    // get the height for image
    public int getHeight() {
        return height;
    }

    /**
     * @param int height
     * @return void
     * Set the height to local object
     */
    public void setHeight(int height) {
        this.height = height;
    }

    // get the width for image
    public int getWidth() {
        return width;
    }

    /**
     * @param int width
     * @return void
     * Set the width to local object
     */
    public void setWidth(int width) {
        this.width = width;
    }


}
