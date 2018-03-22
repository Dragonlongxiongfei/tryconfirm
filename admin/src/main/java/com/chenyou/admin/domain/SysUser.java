package com.chenyou.admin.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SysUser {
    private long uid;

    private String userAccount;

    private String userPassword;

    private String pullMoneyPasswd;

    private Short tbShopNum;

    private Short jdShopNum;

    private Byte userType;

    private String mobilePhone;

    private String mail;

    private String userWeixin;

    private String userQq;

    private String userAlipayAccount;

    private String sex;

    private Byte marryFlag;

    private String userDegree;

    private String schoolName;

    private Boolean birthFlag;

    private Date birthday;

    private String constellation;

    private String receiverAddress;

    private String receiverPhone;

    private String receiverName;

    private String userBankAddress;

    private String userBankName;

    private String userBankAccount;

    private Short height;

    private Short weight;

    private String clothingSize;

    private Short shoseSize;

    private String clothingStyle;

    private Boolean myopia;

    private Integer threeSize1;

    private Integer threeSize2;

    private Integer threeSize3;

    private String job;

    private String monthIncome;

    private String car;

    private String headImg;

    private Byte vipLevel;

    private Date vipEndTime;

    private String userIcon;

    private Short userInfoNum;

    private Short infoFinishedNum;

    private String realName;

    private Long xmoney;

    private BigDecimal freeMoney;

    private Long waitingPaybackMoney;

    private Long frozenMoney;

    private BigDecimal withdrawnMoney;

    private Short coinsNum;

    private Short sendingCoins;

    private Short userTaskNum;

    private Short finishedTaskNum;

    private String invitationCode;

    private Short invitationUid;

    private Short invitationNum;

    private Short invitationPrize;

    private Short invitationPrizeCoin;

    private String wwId;

    private Float wwLevel;

    private String wwImg;

    private String wwRefuseReason;

    private String idCard;

    private String idCardImg;

    private BigDecimal availableDeposit;

    private BigDecimal sellerFrozenDeposit;

    private Long standMoney;

    private Short taobaoScore;

    private Short jdScore;

    private Date lastLoginTime;

    private Date registeTime;

    private Date forbiddenTime;

    private String updateUser;

    private Byte invalidFlag;

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
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getPullMoneyPasswd() {
        return pullMoneyPasswd;
    }

    public void setPullMoneyPasswd(String pullMoneyPasswd) {
        this.pullMoneyPasswd = pullMoneyPasswd == null ? null : pullMoneyPasswd.trim();
    }

    public Short getTbShopNum() {
        return tbShopNum;
    }

    public void setTbShopNum(Short tbShopNum) {
        this.tbShopNum = tbShopNum;
    }

    public Short getJdShopNum() {
        return jdShopNum;
    }

    public void setJdShopNum(Short jdShopNum) {
        this.jdShopNum = jdShopNum;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getUserWeixin() {
        return userWeixin;
    }

    public void setUserWeixin(String userWeixin) {
        this.userWeixin = userWeixin == null ? null : userWeixin.trim();
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq == null ? null : userQq.trim();
    }

    public String getUserAlipayAccount() {
        return userAlipayAccount;
    }

    public void setUserAlipayAccount(String userAlipayAccount) {
        this.userAlipayAccount = userAlipayAccount == null ? null : userAlipayAccount.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Byte getMarryFlag() {
        return marryFlag;
    }

    public void setMarryFlag(Byte marryFlag) {
        this.marryFlag = marryFlag;
    }

    public String getUserDegree() {
        return userDegree;
    }

    public void setUserDegree(String userDegree) {
        this.userDegree = userDegree == null ? null : userDegree.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Boolean getBirthFlag() {
        return birthFlag;
    }

    public void setBirthFlag(Boolean birthFlag) {
        this.birthFlag = birthFlag;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation == null ? null : constellation.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getUserBankAddress() {
        return userBankAddress;
    }

    public void setUserBankAddress(String userBankAddress) {
        this.userBankAddress = userBankAddress == null ? null : userBankAddress.trim();
    }

    public String getUserBankName() {
        return userBankName;
    }

    public void setUserBankName(String userBankName) {
        this.userBankName = userBankName == null ? null : userBankName.trim();
    }

    public String getUserBankAccount() {
        return userBankAccount;
    }

    public void setUserBankAccount(String userBankAccount) {
        this.userBankAccount = userBankAccount == null ? null : userBankAccount.trim();
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public String getClothingSize() {
        return clothingSize;
    }

    public void setClothingSize(String clothingSize) {
        this.clothingSize = clothingSize == null ? null : clothingSize.trim();
    }

    public Short getShoseSize() {
        return shoseSize;
    }

    public void setShoseSize(Short shoseSize) {
        this.shoseSize = shoseSize;
    }

    public String getClothingStyle() {
        return clothingStyle;
    }

    public void setClothingStyle(String clothingStyle) {
        this.clothingStyle = clothingStyle == null ? null : clothingStyle.trim();
    }

    public Boolean getMyopia() {
        return myopia;
    }

    public void setMyopia(Boolean myopia) {
        this.myopia = myopia;
    }

    public Integer getThreeSize1() {
        return threeSize1;
    }

    public void setThreeSize1(Integer threeSize1) {
        this.threeSize1 = threeSize1;
    }

    public Integer getThreeSize2() {
        return threeSize2;
    }

    public void setThreeSize2(Integer threeSize2) {
        this.threeSize2 = threeSize2;
    }

    public Integer getThreeSize3() {
        return threeSize3;
    }

    public void setThreeSize3(Integer threeSize3) {
        this.threeSize3 = threeSize3;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome == null ? null : monthIncome.trim();
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car == null ? null : car.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Byte getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Byte vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Date getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(Date vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public Short getUserInfoNum() {
        return userInfoNum;
    }

    public void setUserInfoNum(Short userInfoNum) {
        this.userInfoNum = userInfoNum;
    }

    public Short getInfoFinishedNum() {
        return infoFinishedNum;
    }

    public void setInfoFinishedNum(Short infoFinishedNum) {
        this.infoFinishedNum = infoFinishedNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Long getXmoney() {
        return xmoney;
    }

    public void setXmoney(Long xmoney) {
        this.xmoney = xmoney;
    }

    public BigDecimal getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(BigDecimal freeMoney) {
        this.freeMoney = freeMoney;
    }

    public Long getWaitingPaybackMoney() {
        return waitingPaybackMoney;
    }

    public void setWaitingPaybackMoney(Long waitingPaybackMoney) {
        this.waitingPaybackMoney = waitingPaybackMoney;
    }

    public Long getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(Long frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public BigDecimal getWithdrawnMoney() {
        return withdrawnMoney;
    }

    public void setWithdrawnMoney(BigDecimal withdrawnMoney) {
        this.withdrawnMoney = withdrawnMoney;
    }

    public Short getCoinsNum() {
        return coinsNum;
    }

    public void setCoinsNum(Short coinsNum) {
        this.coinsNum = coinsNum;
    }

    public Short getSendingCoins() {
        return sendingCoins;
    }

    public void setSendingCoins(Short sendingCoins) {
        this.sendingCoins = sendingCoins;
    }

    public Short getUserTaskNum() {
        return userTaskNum;
    }

    public void setUserTaskNum(Short userTaskNum) {
        this.userTaskNum = userTaskNum;
    }

    public Short getFinishedTaskNum() {
        return finishedTaskNum;
    }

    public void setFinishedTaskNum(Short finishedTaskNum) {
        this.finishedTaskNum = finishedTaskNum;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    public Short getInvitationUid() {
        return invitationUid;
    }

    public void setInvitationUid(Short invitationUid) {
        this.invitationUid = invitationUid;
    }

    public Short getInvitationNum() {
        return invitationNum;
    }

    public void setInvitationNum(Short invitationNum) {
        this.invitationNum = invitationNum;
    }

    public Short getInvitationPrize() {
        return invitationPrize;
    }

    public void setInvitationPrize(Short invitationPrize) {
        this.invitationPrize = invitationPrize;
    }

    public Short getInvitationPrizeCoin() {
        return invitationPrizeCoin;
    }

    public void setInvitationPrizeCoin(Short invitationPrizeCoin) {
        this.invitationPrizeCoin = invitationPrizeCoin;
    }

    public String getWwId() {
        return wwId;
    }

    public void setWwId(String wwId) {
        this.wwId = wwId == null ? null : wwId.trim();
    }

    public Float getWwLevel() {
        return wwLevel;
    }

    public void setWwLevel(Float wwLevel) {
        this.wwLevel = wwLevel;
    }

    public String getWwImg() {
        return wwImg;
    }

    public void setWwImg(String wwImg) {
        this.wwImg = wwImg == null ? null : wwImg.trim();
    }

    public String getWwRefuseReason() {
        return wwRefuseReason;
    }

    public void setWwRefuseReason(String wwRefuseReason) {
        this.wwRefuseReason = wwRefuseReason == null ? null : wwRefuseReason.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg == null ? null : idCardImg.trim();
    }

    public BigDecimal getAvailableDeposit() {
        return availableDeposit;
    }

    public void setAvailableDeposit(BigDecimal availableDeposit) {
        this.availableDeposit = availableDeposit;
    }

    public BigDecimal getSellerFrozenDeposit() {
        return sellerFrozenDeposit;
    }

    public void setSellerFrozenDeposit(BigDecimal sellerFrozenDeposit) {
        this.sellerFrozenDeposit = sellerFrozenDeposit;
    }

    public Long getStandMoney() {
        return standMoney;
    }

    public void setStandMoney(Long standMoney) {
        this.standMoney = standMoney;
    }

    public Short getTaobaoScore() {
        return taobaoScore;
    }

    public void setTaobaoScore(Short taobaoScore) {
        this.taobaoScore = taobaoScore;
    }

    public Short getJdScore() {
        return jdScore;
    }

    public void setJdScore(Short jdScore) {
        this.jdScore = jdScore;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getRegisteTime() {
        return registeTime;
    }

    public void setRegisteTime(Date registeTime) {
        this.registeTime = registeTime;
    }

    public Date getForbiddenTime() {
        return forbiddenTime;
    }

    public void setForbiddenTime(Date forbiddenTime) {
        this.forbiddenTime = forbiddenTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Byte getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(Byte invalidFlag) {
        this.invalidFlag = invalidFlag;
    }
}