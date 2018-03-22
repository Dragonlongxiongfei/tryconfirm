package com.chenyou.admin.domain;

/**
 * Created by Shell Li on 2017/12/16.
 */
public class Coup {
    private long id;
    private String coupTitle;
    private float coupMoney;
    private int coupNum;
    private int coupUsed;
    private int coupCondition;
    private int coupLock;
    private String coupDesc;
    private long coupTimeLimit;
    private String addTime;
    private int invalidFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoupTitle() {
        return coupTitle;
    }

    public void setCoupTitle(String coupTitle) {
        this.coupTitle = coupTitle;
    }

    public float getCoupMoney() {
        return coupMoney;
    }

    public void setCoupMoney(float coupMoney) {
        this.coupMoney = coupMoney;
    }

    public int getCoupNum() {
        return coupNum;
    }

    public void setCoupNum(int coupNum) {
        this.coupNum = coupNum;
    }

    public int getCoupUsed() {
        return coupUsed;
    }

    public void setCoupUsed(int coupUsed) {
        this.coupUsed = coupUsed;
    }

    public int getCoupCondition() {
        return coupCondition;
    }

    public void setCoupCondition(int coupCondition) {
        this.coupCondition = coupCondition;
    }

    public int getCoupLock() {
        return coupLock;
    }

    public void setCoupLock(int coupLock) {
        this.coupLock = coupLock;
    }

    public String getCoupDesc() {
        return coupDesc;
    }

    public void setCoupDesc(String coupDesc) {
        this.coupDesc = coupDesc;
    }

    public long getCoupTimeLimit() {
        return coupTimeLimit;
    }

    public void setCoupTimeLimit(long coupTimeLimit) {
        this.coupTimeLimit = coupTimeLimit;
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
