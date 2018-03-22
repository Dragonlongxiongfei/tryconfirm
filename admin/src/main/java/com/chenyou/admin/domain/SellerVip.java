package com.chenyou.admin.domain;

import java.math.BigDecimal;

/**
 * Created by amoslong on 2017/12/13.
 */
public class SellerVip {
    private long vipId;
    private String vipTitle;
    private String vipType;
    private BigDecimal vipPrice;
    private BigDecimal basicPrice;
    private BigDecimal discount;
    private String sendTime;
    private String addTime;
    private String icon;
    private String moreSpecical;
    private int svip;
    private int invalidFlag;

    public long getVipId() {
        return vipId;
    }

    public void setVipId(long vipId) {
        this.vipId = vipId;
    }

    public String getVipTitle() {
        return vipTitle;
    }

    public void setVipTitle(String vipTitle) {
        this.vipTitle = vipTitle;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSvip() {
        return svip;
    }

    public void setSvip(int svip) {
        this.svip = svip;
    }

    public int getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(int invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public String getMoreSpecical() {
        return moreSpecical;
    }

    public void setMoreSpecical(String moreSpecical) {
        this.moreSpecical = moreSpecical;
    }
}
