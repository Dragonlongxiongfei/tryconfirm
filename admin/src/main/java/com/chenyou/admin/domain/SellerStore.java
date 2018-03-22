package com.chenyou.admin.domain;

import java.math.BigDecimal;

/**
 * Created by amoslong on 2017/12/12.
 *
 * 店铺实体
 */
public class SellerStore {
    private int storeId;
    private int uId;
    private String storeUrl;
    private String storeName;
    private String xinyu;
    private String jindongXinyu;
    private String platform;
    private String goodUrl;
    private BigDecimal goodPrice;
    private String categoryId;
    private String storeQQ;
    private String storeWeixin;
    private String storePhone;
    private String addTime;
    private int invalidFlag;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getXinyu() {
        return xinyu;
    }

    public void setXinyu(String xinyu) {
        this.xinyu = xinyu;
    }

    public String getJindongXinyu() {
        return jindongXinyu;
    }

    public void setJindongXinyu(String jindongXinyu) {
        this.jindongXinyu = jindongXinyu;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGoodUrl() {
        return goodUrl;
    }

    public void setGoodUrl(String goodUrl) {
        this.goodUrl = goodUrl;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStoreQQ() {
        return storeQQ;
    }

    public void setStoreQQ(String storeQQ) {
        this.storeQQ = storeQQ;
    }

    public String getStoreWeixin() {
        return storeWeixin;
    }

    public void setStoreWeixin(String storeWeixin) {
        this.storeWeixin = storeWeixin;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
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
