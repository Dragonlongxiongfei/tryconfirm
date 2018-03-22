package com.chenyou.admin.domain;

import java.math.BigDecimal;

/**
 * Created by Shell Li on 2017/12/28.
 */
public class GdTask {

    private String taskId; // 任务ID
    private long goodsId; // 商品ID
    private String goodsName; // 商品名称
    private int goodsParentClass; // 商品父分类
    private int goodsChildClass; // 商品子分类
    private long sellerUid; // 商家id
    private String sellerName; // 商品名称
    private BigDecimal goodsPrice; // 商品价格
    private String goodsImg; // 商品主图
    private int coinsReturn; // 商品赏金
    private int cashReturn; // 商品赏钱
    private long uid; // 试客id
    private String userAccount; // 试客账号
    private String buyAccount; // 试客买号
    private String goodsUrl; // 申请商品的url
    private String goodsCollectionImg; // 宝贝收藏截图
    private String shopCollectionImg; // 店铺收藏截图
    private String otherShopUrl; // 货比三家的链接
    private String firstDayBegin; // 找商品开始时间(第一天)
    private String firstDayEnd; // 找商品结束时间(第一天)
    private String secondDayBegin; // 第二天开始时间
    private String secondDayEnd; // 第二天结束时间
    private String thirdDayBegin; // 第三天开始时间
    private String thirdDayEnd; // 第三天开始时间
    private String prizeTime; // 中奖时间
    private String touchPrizeTime; // 领奖时间
    private String orderTime; // 下单时间
    private String orderConfirmTime; // 商家确认订单时间
    private String goodsComment; // 商品评价
    private String commentImg; // 商品晒图
    private String commentCallbackImg;  // 回传淘宝图文评价截图
    private String commentTime; // 提交评价时间
    private int commentPassFlag; // 评价是否通过
    private String commentConfirmTime; // 商家确认评价时间
    private String commentCopyTime; // 复制评价到淘宝时间
    private String taskApproveTime; //  商家审核通过时间（完成）
    private String confirmFourUrl; // 商铺4个随机商品链接
    private String tradeNo; // 实际订单号
    private String tradeImg; // 实际订单截图
    private BigDecimal tradeMoney; // 实际支付金额
    private String talkImg; // 与商户聊天截图
    private String giveUpReason; // 任务放弃原因
    private int taskStatus; // 1申请试用、2审核试客、3下单付款、4收货好评、5平台返款、6完成任务、7取消任务、8放弃任务
    private int prizeFlag; // 中奖状态：0待抽奖；1中奖；2未中奖；3任务进行中
    private String addTime;
    private int invalidFlag;

    private GdGoods gdGoods;

    public GdGoods getGdGoods() {
        return gdGoods;
    }

    public void setGdGoods(GdGoods gdGoods) {
        this.gdGoods = gdGoods;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsParentClass() {
        return goodsParentClass;
    }

    public void setGoodsParentClass(int goodsParentClass) {
        this.goodsParentClass = goodsParentClass;
    }

    public int getGoodsChildClass() {
        return goodsChildClass;
    }

    public void setGoodsChildClass(int goodsChildClass) {
        this.goodsChildClass = goodsChildClass;
    }

    public long getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(long sellerUid) {
        this.sellerUid = sellerUid;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getCoinsReturn() {
        return coinsReturn;
    }

    public void setCoinsReturn(int coinsReturn) {
        this.coinsReturn = coinsReturn;
    }

    public int getCashReturn() {
        return cashReturn;
    }

    public void setCashReturn(int cashReturn) {
        this.cashReturn = cashReturn;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getBuyAccount() {
        return buyAccount;
    }

    public void setBuyAccount(String buyAccount) {
        this.buyAccount = buyAccount;
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

    public String getOtherShopUrl() {
        return otherShopUrl;
    }

    public void setOtherShopUrl(String otherShopUrl) {
        this.otherShopUrl = otherShopUrl;
    }

    public String getFirstDayBegin() {
        return firstDayBegin;
    }

    public void setFirstDayBegin(String firstDayBegin) {
        this.firstDayBegin = firstDayBegin;
    }

    public String getFirstDayEnd() {
        return firstDayEnd;
    }

    public void setFirstDayEnd(String firstDayEnd) {
        this.firstDayEnd = firstDayEnd;
    }

    public String getSecondDayBegin() {
        return secondDayBegin;
    }

    public void setSecondDayBegin(String secondDayBegin) {
        this.secondDayBegin = secondDayBegin;
    }

    public String getSecondDayEnd() {
        return secondDayEnd;
    }

    public void setSecondDayEnd(String secondDayEnd) {
        this.secondDayEnd = secondDayEnd;
    }

    public String getThirdDayBegin() {
        return thirdDayBegin;
    }

    public void setThirdDayBegin(String thirdDayBegin) {
        this.thirdDayBegin = thirdDayBegin;
    }

    public String getThirdDayEnd() {
        return thirdDayEnd;
    }

    public void setThirdDayEnd(String thirdDayEnd) {
        this.thirdDayEnd = thirdDayEnd;
    }

    public String getPrizeTime() {
        return prizeTime;
    }

    public void setPrizeTime(String prizeTime) {
        this.prizeTime = prizeTime;
    }

    public String getTouchPrizeTime() {
        return touchPrizeTime;
    }

    public void setTouchPrizeTime(String touchPrizeTime) {
        this.touchPrizeTime = touchPrizeTime;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderConfirmTime() {
        return orderConfirmTime;
    }

    public void setOrderConfirmTime(String orderConfirmTime) {
        this.orderConfirmTime = orderConfirmTime;
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

    public String getCommentCallbackImg() {
        return commentCallbackImg;
    }

    public void setCommentCallbackImg(String commentCallbackImg) {
        this.commentCallbackImg = commentCallbackImg;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public int getCommentPassFlag() {
        return commentPassFlag;
    }

    public void setCommentPassFlag(int commentPassFlag) {
        this.commentPassFlag = commentPassFlag;
    }

    public String getCommentConfirmTime() {
        return commentConfirmTime;
    }

    public void setCommentConfirmTime(String commentConfirmTime) {
        this.commentConfirmTime = commentConfirmTime;
    }

    public String getCommentCopyTime() {
        return commentCopyTime;
    }

    public void setCommentCopyTime(String commentCopyTime) {
        this.commentCopyTime = commentCopyTime;
    }

    public String getTaskApproveTime() {
        return taskApproveTime;
    }

    public void setTaskApproveTime(String taskApproveTime) {
        this.taskApproveTime = taskApproveTime;
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

    public String getGiveUpReason() {
        return giveUpReason;
    }

    public void setGiveUpReason(String giveUpReason) {
        this.giveUpReason = giveUpReason;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getPrizeFlag() {
        return prizeFlag;
    }

    public void setPrizeFlag(int prizeFlag) {
        this.prizeFlag = prizeFlag;
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
