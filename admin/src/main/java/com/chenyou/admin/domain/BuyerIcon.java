package com.chenyou.admin.domain;

/**
 * Created by Shell Li on 2017/12/18.
 */
public class BuyerIcon {
    private long id;
    private String iconTitle;
    private String lightImg;
    private String darkImg;
    private String comment;
    private String addTime;
    private int invalidFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIconTitle() {
        return iconTitle;
    }

    public void setIconTitle(String iconTitle) {
        this.iconTitle = iconTitle;
    }

    public String getLightImg() {
        return lightImg;
    }

    public void setLightImg(String lightImg) {
        this.lightImg = lightImg;
    }

    public String getDarkImg() {
        return darkImg;
    }

    public void setDarkImg(String darkImg) {
        this.darkImg = darkImg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(int invalidFlag) {
        this.invalidFlag = invalidFlag;
    }
}
