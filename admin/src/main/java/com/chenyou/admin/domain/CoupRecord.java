package com.chenyou.admin.domain;

/**
 * Created by Shell Li on 2017/12/21.
 */
public class CoupRecord {
    private long id;
    private long uid;
    private long coupID;
    private String coupTitle;
    private float coupMoney;
    private int coupCondition;
    private int coupLock;
    private String coupGetTime;
    private String coupEndTime;
    private long userdGoodsId;
    private String comment;
    private int status;

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

    public long getCoupID() {
        return coupID;
    }

    public void setCoupID(long coupID) {
        this.coupID = coupID;
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

    public String getCoupGetTime() {
        return coupGetTime;
    }

    public void setCoupGetTime(String coupGetTime) {
        this.coupGetTime = coupGetTime;
    }

    public String getCoupEndTime() {
        return coupEndTime;
    }

    public void setCoupEndTime(String coupEndTime) {
        this.coupEndTime = coupEndTime;
    }

    public long getUserdGoodsId() {
        return userdGoodsId;
    }

    public void setUserdGoodsId(long userdGoodsId) {
        this.userdGoodsId = userdGoodsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
