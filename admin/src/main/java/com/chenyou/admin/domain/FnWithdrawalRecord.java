package com.chenyou.admin.domain;

import org.jeecgframework.poi.excel.annotation.Excel;

import java.math.BigDecimal;

/**
 * Created by Shell Li on 2017/12/25.
 */
public class FnWithdrawalRecord {
    @Excel(name = "ID", orderNum = "1")
    private long id; // 流水号
    @Excel(name = "用户ID", orderNum = "2")
    private long uid; // 用户id(商家、试客)
    @Excel(name = "会员账号", orderNum = "3")
    private String userAccount; // 会员账号
    @Excel(name = "提现金额", orderNum = "4")
    private BigDecimal withdrawalMoney; // 提现金额
    @Excel(name = "到账金额", orderNum = "5")
    private BigDecimal receiveMoney; // 到账金额
    @Excel(name = "手续费用", orderNum = "6")
    private BigDecimal handlerMoney; // 手续费用
    @Excel(name = "提现时间", orderNum = "7")
    private String withdrawalTime; // 提现时间
    @Excel(name = "审核时间", orderNum = "8")
    private String checkTime; // 审核时间
    @Excel(name = "到账时间", orderNum = "9")
    private String receiveTime; // 到账时间
    @Excel(name = "到账时间", orderNum = "10", replace = {"支付宝_1", "微信_2", "银行_3"})
    private int withdrawalType; // 支付方式（1支付宝，2微信，3银行）
    @Excel(name = "收款账号", orderNum = "11")
    private String userReceiveAccount; // 会员收款账号
    @Excel(name = "收款账号昵称", width = 20d, orderNum = "12")
    private String userReceiveNickName; // 会员收款账号昵称
    @Excel(name = "收款账号的真实姓名", width = 25d, orderNum = "13")
    private String userReceiveRealName; // 会员收款账号的真实姓名
    @Excel(name = "账户总额", orderNum = "14")
    private BigDecimal xmoney; // 账户总额(会员在平台进入的总额)
    @Excel(name = "账户余额", orderNum = "15")
    private BigDecimal freeMoney; // 账户余额(可提现金额)
    @Excel(name = "已提现总和", orderNum = "16")
    private BigDecimal withdrawTotal; // 已提现总和
    @Excel(name = "收款账号订单流水号",width = 35d, orderNum = "17")
    private String receivePayNo; // 收款账号订单流水号

    private String checkUserID; // 审核人员id
    @Excel(name = "审核人员id", orderNum = "18")
    private String checkUserAccount; // 审核人员账号
    private int status; // 提现状态

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

    public BigDecimal getWithdrawalMoney() {
        return withdrawalMoney;
    }

    public void setWithdrawalMoney(BigDecimal withdrawalMoney) {
        this.withdrawalMoney = withdrawalMoney;
    }

    public BigDecimal getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public BigDecimal getHandlerMoney() {
        return handlerMoney;
    }

    public void setHandlerMoney(BigDecimal handlerMoney) {
        this.handlerMoney = handlerMoney;
    }

    public String getWithdrawalTime() {
        return withdrawalTime;
    }

    public void setWithdrawalTime(String withdrawalTime) {
        this.withdrawalTime = withdrawalTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public int getWithdrawalType() {
        return withdrawalType;
    }

    public void setWithdrawalType(int withdrawalType) {
        this.withdrawalType = withdrawalType;
    }

    public String getUserReceiveAccount() {
        return userReceiveAccount;
    }

    public void setUserReceiveAccount(String userReceiveAccount) {
        this.userReceiveAccount = userReceiveAccount;
    }

    public String getUserReceiveNickName() {
        return userReceiveNickName;
    }

    public void setUserReceiveNickName(String userReceiveNickName) {
        this.userReceiveNickName = userReceiveNickName;
    }

    public String getUserReceiveRealName() {
        return userReceiveRealName;
    }

    public void setUserReceiveRealName(String userReceiveRealName) {
        this.userReceiveRealName = userReceiveRealName;
    }

    public BigDecimal getXmoney() {
        return xmoney;
    }

    public void setXmoney(BigDecimal xmoney) {
        this.xmoney = xmoney;
    }

    public BigDecimal getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(BigDecimal freeMoney) {
        this.freeMoney = freeMoney;
    }

    public BigDecimal getWithdrawTotal() {
        return withdrawTotal;
    }

    public void setWithdrawTotal(BigDecimal withdrawTotal) {
        this.withdrawTotal = withdrawTotal;
    }

    public String getReceivePayNo() {
        return receivePayNo;
    }

    public void setReceivePayNo(String receivePayNo) {
        this.receivePayNo = receivePayNo;
    }

    public String getCheckUserID() {
        return checkUserID;
    }

    public void setCheckUserID(String checkUserID) {
        this.checkUserID = checkUserID;
    }

    public String getCheckUserAccount() {
        return checkUserAccount;
    }

    public void setCheckUserAccount(String checkUserAccount) {
        this.checkUserAccount = checkUserAccount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
