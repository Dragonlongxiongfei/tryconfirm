package com.chenyou.admin.domain;

import java.math.BigDecimal;

/**
 *
 */
public class SellerTaskVO {

    private String taskID; // 任务id
    private long uid; // 用户id
    private String userAccount; //

    private long goodsID; // 商品id
    private int shopType; // 试用平台(1淘宝、2天猫，3京东等)
    private String shopName; // 店铺名称
    private String goodsName;
    private BigDecimal goodsPrice;
    private int applyType;
    private String goodsUrl; // 申请商品的url

    private String goodsCollectionImg; // 宝贝收藏截图
    private String shopCollectionImg; // 店铺收藏截图
    private String confirmFourUrl; // 商铺4个随机商品链接
    private String tradeNo; // 实际订单号
    private String tradeImg; // 实际订单截图
    private BigDecimal tradeMoney; // 实际支付金额
    private String talkImg; // 与商户聊天截图
    private String goodsComment; // 本平台商品评价
    private String commentImg; // 评价截图
    private String giveUpReason; // 放弃原因
    private int taskFlow; // 整体流程：0申请试用；1审核试客；2下单付款；3收货好评；4平台返款；5完成任务
    private int taskStatus; // 任务状态：0未申请；1审核链接通过；2收藏宝贝、店铺；3浏览店铺，复制四个商品链接；
                            // 4下单付款；5收到商品好评并上传截图；6商家审核好评；7平台返款；8完成任务；99已中奖，待领取


    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(long goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsCollectionImg() {
        return goodsCollectionImg;
    }

    public void setGoodsCollectionImg(String goodsCollectionImg) {
        this.goodsCollectionImg = goodsCollectionImg;
    }

    public String getShopCollectionImg() {
        return shopCollectionImg;
    }

    public void setShopCollectionImg(String shopCollectionImg) {
        this.shopCollectionImg = shopCollectionImg;
    }

    public String getConfirmFourUrl() {
        return confirmFourUrl;
    }

    public void setConfirmFourUrl(String confirmFourUrl) {
        this.confirmFourUrl = confirmFourUrl;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeImg() {
        return tradeImg;
    }

    public void setTradeImg(String tradeImg) {
        this.tradeImg = tradeImg;
    }

    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getTalkImg() {
        return talkImg;
    }

    public void setTalkImg(String talkImg) {
        this.talkImg = talkImg;
    }

    public String getGoodsComment() {
        return goodsComment;
    }

    public void setGoodsComment(String goodsComment) {
        this.goodsComment = goodsComment;
    }

    public String getCommentImg() {
        return commentImg;
    }

    public void setCommentImg(String commentImg) {
        this.commentImg = commentImg;
    }

    public String getGiveUpReason() {
        return giveUpReason;
    }

    public void setGiveUpReason(String giveUpReason) {
        this.giveUpReason = giveUpReason;
    }

    public int getTaskFlow() {
        return taskFlow;
    }

    public void setTaskFlow(int task_Flow) {
        this.taskFlow = task_Flow;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public int getShopType() {
        return shopType;
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getApplyType() {
        return applyType;
    }

    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
