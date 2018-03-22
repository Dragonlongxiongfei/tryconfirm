package com.chenyou.admin.domain;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;

/**
 * Created by Shell Li on 2017/12/25.
 * 会员资金明细（会员账款进出明细）
 */
public class FnIORecord {

    @Excel(name = "ID", orderNum = "1", mergeVertical = false)
    private long id;
    @Excel(name = "会员ID", orderNum = "2", mergeVertical = false)
    private long uid; // 会员id
    @Excel(name = "会员账号", orderNum = "3", mergeVertical = false)
    private String userAccount; // 会员账号
    @Excel(name = "资金类型", orderNum = "4", replace = {"平台进账_1", "平台出账_2"}, mergeVertical = false)
    private int moneyType; // 资金类型（1进账， 2出账）
    @Excel(name = "金额", orderNum = "5", mergeVertical = false)
    private BigDecimal money; // 资金金额（正整数）
    @Excel(name = "备注", orderNum = "6", mergeVertical = false)
    private String comment; // 备注
    private String referenceID; // 相关数据库表id（任务表，提现表，充值表）
    private BigDecimal freeMoney; // 账户结余金额

    private String addTime;
    private int invalidFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(int moneyType) {
        this.moneyType = moneyType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    public BigDecimal getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(BigDecimal freeMoney) {
        this.freeMoney = freeMoney;
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
