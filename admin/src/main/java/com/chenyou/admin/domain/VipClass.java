package com.chenyou.admin.domain;

/**
 * Created by Shell Li on 2017/12/11.
 */
public class VipClass {

    private long vipId;
    private String vipTitle;
    private int vipType;
    private float vipPrice;
    private float basicPrice;
    private float discount;
    private int effectiveTime;
    private String vipIcon; // 会员图标
    private int twiceFlag; // 每天两次中奖
    private int threeTimeFlag; // 每天三次中奖
    private int increaseTenFlag; // 提升10%中奖率
    private int increaseTwentyFlag; // 提升20%中奖率
    private int goodsVipOnly;  // 会员专属
    private int birthdayPrize; // 生日福利
    private int bigBirthdayPrize; // 生日大礼包
    private int priorityPass; // 优先审核
    private int customService; // 专属客服

    private String addTime;
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

    public int getVipType() {
        return vipType;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }

    public float getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(float vipPrice) {
        this.vipPrice = vipPrice;
    }

    public float getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(float basicPrice) {
        this.basicPrice = basicPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(int effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getVipIcon() {
        return vipIcon;
    }

    public void setVipIcon(String vipIcon) {
        this.vipIcon = vipIcon;
    }

    public int getTwiceFlag() {
        return twiceFlag;
    }

    public void setTwiceFlag(int twiceFlag) {
        this.twiceFlag = twiceFlag;
    }

    public int getThreeTimeFlag() {
        return threeTimeFlag;
    }

    public void setThreeTimeFlag(int threeTimeFlag) {
        this.threeTimeFlag = threeTimeFlag;
    }

    public int getIncreaseTenFlag() {
        return increaseTenFlag;
    }

    public void setIncreaseTenFlag(int increaseTenFlag) {
        this.increaseTenFlag = increaseTenFlag;
    }

    public int getIncreaseTwentyFlag() {
        return increaseTwentyFlag;
    }

    public void setIncreaseTwentyFlag(int increaseTwentyFlag) {
        this.increaseTwentyFlag = increaseTwentyFlag;
    }

    public int getGoodsVipOnly() {
        return goodsVipOnly;
    }

    public void setGoodsVipOnly(int goodsVipOnly) {
        this.goodsVipOnly = goodsVipOnly;
    }

    public int getBirthdayPrize() {
        return birthdayPrize;
    }

    public void setBirthdayPrize(int birthdayPrize) {
        this.birthdayPrize = birthdayPrize;
    }

    public int getBigBirthdayPrize() {
        return bigBirthdayPrize;
    }

    public void setBigBirthdayPrize(int bigBirthdayPrize) {
        this.bigBirthdayPrize = bigBirthdayPrize;
    }

    public int getPriorityPass() {
        return priorityPass;
    }

    public void setPriorityPass(int priorityPass) {
        this.priorityPass = priorityPass;
    }

    public int getCustomService() {
        return customService;
    }

    public void setCustomService(int customService) {
        this.customService = customService;
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
