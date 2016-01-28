package com.example.aniket.testdemo.models;

/**
 * SearchResultModel.java .
 * @author Aniket Mane
 * @version 1.0
 */
public class SearchResultModel {

    private long pageId;
    private int ns;
    private int index;
    private String title;
    private ThumbnailImageModel imageModel=new ThumbnailImageModel();

    /**
     * @param none
     * @return ThumbnailImageModel
     * get ThumbnailImageMode object
     */
    public ThumbnailImageModel getImageModel() {
        return imageModel;
    }

    /**
     * @param ThumbnailImageModel imageModel
     * @return void
     * set the local ThumnailImageModel object
     */
    public void setImageModel(ThumbnailImageModel imageModel) {
        this.imageModel = imageModel;
    }

    /**
     * @param  none
     * @return long pageId
     * get long pageId
     * */
    public long getPageId() {
        return pageId;
    }

    /**
     * @param long pageId
     * @return void
     * set the local long pageId
     */
    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    /**
     * @param  none
     * @return int ns
     * get int ns value
     * */
    public int getNs() {
        return ns;
    }

    /**
     * @param int ns
     * @return void
     * set the local ns object
     */
    public void setNs(int ns) {
        this.ns = ns;
    }

    /**
     * @param  none
     * @return int index
     * get local object index
     * */
    public int getIndex() {
        return index;
    }

    /**
     * @param int  index
     * @return void
     * set the local object index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @param  none
     * @return long pageId
     * get long String title
     * */
    public String getTitle() {
        return title;
    }

    /**
     * @param String title
     * @return void
     * set the local title String
     */
    public void setTitle(String title) {
        this.title = title;
    }


}
