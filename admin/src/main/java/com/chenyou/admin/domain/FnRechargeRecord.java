package com.chenyou.admin.domain;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.math.BigDecimal;

/**
 * Created by Shell Li on 2017/12/25.
 * 充值记录表
 */
@ExcelTarget("fnRechargeRecord")
public class FnRechargeRecord {
    @Excel(name = "ID", orderNum = "1", mergeVertical = false)
    private String id; // 流水号
    @Excel(name = "会员ID", orderNum = "2", mergeVertical = false)
    private long uid; // 会员id(商家、试客)
    @Excel(name = "用户账号", orderNum = "3", mergeVertical = false)
    private String userAccount; // 会员账号
    @Excel(name = "充值类型", orderNum = "4", replace = {"vip充值_1", "金币充值_2", "活动充值_3"})
    private int charType; // 充值类型（1vip；2金币；3活动）
    @Excel(name = "充值金额", orderNum = "5", mergeVertical = false)
    private BigDecimal charMoney; // 充值金额
    @Excel(name = "支付来源类型", orderNum = "6",  width = 20D, replace = {"支付宝_1", "微信_2", "银联_3"})
    private int payType; // 支付来源类型（1支付宝、2微信、3银联）
    @Excel(name = "支付来源账号", orderNum = "7", width = 25D, mergeVertical = false)
    private String payAccount; // 支付来源账号
    @Excel(name = "来源流水号", orderNum = "8",  width = 25D, mergeVertical = false)
    private String payNo; // 来源流水号
    @Excel(name = "到账时间", orderNum = "9", mergeVertical = false)
    private String payFinishTime; // 到账时间
    @Excel(name = "备注", orderNum = "10", mergeVertical = false)
    private String comment; // 备注说明（api接口回调中）
    @Excel(name = "添加时间", orderNum = "11", mergeVertical = false)
    private String addTime;
    private int invalidFlag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getCharType() {
        return charType;
    }

    public void setCharType(int charType) {
        this.charType = charType;
    }

    public BigDecimal getCharMoney() {
        return charMoney;
    }

    public void setCharMoney(BigDecimal charMoney) {
        this.charMoney = charMoney;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayFinishTime() {
        return payFinishTime;
    }

    public void setPayFinishTime(String payFinishTime) {
        this.payFinishTime = payFinishTime;
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
