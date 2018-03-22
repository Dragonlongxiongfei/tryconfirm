package com.chenyou.admin.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Shell Li on 2017/11/29.
 */

/**
 * 试客实体
 */
public class SysUserBuyer {

    private long uid;
    private String userAccount;
    private String userPassword;
    private String pullMoneyPasswd; // 提现密码
    private int tbShopNum; // 淘宝店铺数量
    private int jdShopNum; // 京东店铺数量
    private int userType;
    private String mobilePhone;
    private String mail;
    private String userWeiXin; // 会员微信
    private String userQQ; //  会员QQ
    private String userAlipay; // 会员支付宝账号
    private char sex;
    private int marryFlag; // 婚否
    private String userDegree; // 学历
    private String schoolName; // 毕业院校
    private int birthFlag;  // 生育情况：0未孕；1备孕中；2已育
    private String birthday;
    private String constellation; // 会员星座
    private String receiverAddress; // 收货人地址
    private String receiverPhone; //收货人电话
    private String receiverName; // 收货人姓名
    private String userBankAddress; // 会员银行开户地址
    private String userBankName; // 会员银行开户姓名
    private String userBankAccount; // 会员银行账号
    private String userBankPhone; // 会员银行预留电话
    private int height; // 身高
    private int weight; // 体重
    private String clothingSize; // 衣服尺码
    private int shoseSize; // 鞋码
    private String clothingStyle; // 穿衣风格
    private boolean myopia; // 是否近视
    private int threeSize1; // 胸围
    private int threeSize2; // 腰围
    private int threeSize3; // 臀围
    private String job; // 职业
    private String monthIncome; // 会员月收入
    private String car; // 交通工具
    private String headImg; // 会员头像
    private int vipLevel; // vip等级
    private String vipEndTime; // vip结束时间
    private String user_icon; // 会员徽章(json格式：[1,2,3,4,5等等])
    private int userInfoNum; // 会员资料项数量
    private int infoFinishedNum; // 会员资料完成数量
    private String realName; // 真实姓名
    private BigDecimal xmoney;// 会员垫付总额
    private BigDecimal freeMoney; // 会员可提现总额
    private BigDecimal waitingPaybackMoney; // 会员待平台返还总额（已完成任务，待商家确认）
    private BigDecimal frozenMoney;// 会员被冻结总额
    private BigDecimal withdrawnMoney; // 会员已提现总额（平台打款成功）
    private int coinsNum; // 会员金币
    private int sendingCoins; // 会员待发放金币总额（增值服务）
    private int userTaskNum; // 会员试用的任务数量
    private int finishedTaskNum; // 完成试用的数量
    private String invitationCode; // 邀请码
    private long invitationUid; // 邀请人id
    private int invitationNum; // 邀请总人数
    private int invitationPrize; // 邀请奖励金
    private int invitationPrizeCoin; // 邀请奖励金币
    private String wwID; // 旺旺ID
    private String wwLevel; // 旺旺等级
    private String wwImg; // 旺旺等级截图
    private String wwRefuseReason; // 旺旺号拒绝原因
    private String jdAccount; // 京东账号
    private String jdImg; // 京东账号截图
    private String jdRefuseReason; //  京东账号拒绝原因
    private String idCard; // 身份证
    private String idCardImg; // 身份证截图
    private BigDecimal availableDeposit; // 商家可用押金
    private BigDecimal sellerFrozenDeposit; // 商家已冻结押金（可退还）
    private BigDecimal standMoney; // 担保金
    private long taobaoScore; // 淘气值
    private long jdScore; // 京东值
    private String lastLoginTime; // 上次登录时间
    private String registeTime; // 注册时间
    private String forbiddenTime; // 禁止登录的截至日期
    private String forbiddenReason; // 禁止原因

    List<BuyAccount> buyAccountList;

    private String updateUser;
    private String loginIp;
    private int invalidFlag;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPullMoneyPasswd() {
        return pullMoneyPasswd;
    }

    public void setPullMoneyPasswd(String pullMoneyPasswd) {
        this.pullMoneyPasswd = pullMoneyPasswd;
    }

    public int getTbShopNum() {
        return tbShopNum;
    }

    public void setTbShopNum(int tbShopNum) {
        this.tbShopNum = tbShopNum;
    }

    public int getJdShopNum() {
        return jdShopNum;
    }

    public void setJdShopNum(int jdShopNum) {
        this.jdShopNum = jdShopNum;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserWeiXin() {
        return userWeiXin;
    }

    public void setUserWeiXin(String userWeiXin) {
        this.userWeiXin = userWeiXin;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    public String getUserAlipay() {
        return userAlipay;
    }

    public void setUserAlipay(String userAlipay) {
        this.userAlipay = userAlipay;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getMarryFlag() {
        return marryFlag;
    }

    public void setMarryFlag(int marryFlag) {
        this.marryFlag = marryFlag;
    }

    public String getUserDegree() {
        return userDegree;
    }

    public void setUserDegree(String userDegree) {
        this.userDegree = userDegree;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getBirthFlag() {
        return birthFlag;
    }

    public void setBirthFlag(int birthFlag) {
        this.birthFlag = birthFlag;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getUserBankAddress() {
        return userBankAddress;
    }

    public void setUserBankAddress(String userBankAddress) {
        this.userBankAddress = userBankAddress;
    }

    public String getUserBankName() {
        return userBankName;
    }

    public void setUserBankName(String userBankName) {
        this.userBankName = userBankName;
    }

    public String getUserBankAccount() {
        return userBankAccount;
    }

    public void setUserBankAccount(String userBankAccount) {
        this.userBankAccount = userBankAccount;
    }

    public String getUserBankPhone() {
        return userBankPhone;
    }

    public void setUserBankPhone(String userBankPhone) {
        this.userBankPhone = userBankPhone;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getClothingSize() {
        return clothingSize;
    }

    public void setClothingSize(String clothingSize) {
        this.clothingSize = clothingSize;
    }

    public int getShoseSize() {
        return shoseSize;
    }

    public void setShoseSize(int shoseSize) {
        this.shoseSize = shoseSize;
    }

    public String getClothingStyle() {
        return clothingStyle;
    }

    public void setClothingStyle(String clothingStyle) {
        this.clothingStyle = clothingStyle;
    }

    public boolean isMyopia() {
        return myopia;
    }

    public void setMyopia(boolean myopia) {
        this.myopia = myopia;
    }

    public int getThreeSize1() {
        return threeSize1;
    }

    public void setThreeSize1(int threeSize1) {
        this.threeSize1 = threeSize1;
    }

    public int getThreeSize2() {
        return threeSize2;
    }

    public void setThreeSize2(int threeSize2) {
        this.threeSize2 = threeSize2;
    }

    public int getThreeSize3() {
        return threeSize3;
    }

    public void setThreeSize3(int threeSize3) {
        this.threeSize3 = threeSize3;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(String vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public int getUserInfoNum() {
        return userInfoNum;
    }

    public void setUserInfoNum(int userInfoNum) {
        this.userInfoNum = userInfoNum;
    }

    public int getInfoFinishedNum() {
        return infoFinishedNum;
    }

    public void setInfoFinishedNum(int infoFinishedNum) {
        this.infoFinishedNum = infoFinishedNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public BigDecimal getWaitingPaybackMoney() {
        return waitingPaybackMoney;
    }

    public void setWaitingPaybackMoney(BigDecimal waitingPaybackMoney) {
        this.waitingPaybackMoney = waitingPaybackMoney;
    }

    public BigDecimal getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public BigDecimal getWithdrawnMoney() {
        return withdrawnMoney;
    }

    public void setWithdrawnMoney(BigDecimal withdrawnMoney) {
        this.withdrawnMoney = withdrawnMoney;
    }

    public int getCoinsNum() {
        return coinsNum;
    }

    public void setCoinsNum(int coinsNum) {
        this.coinsNum = coinsNum;
    }

    public int getSendingCoins() {
        return sendingCoins;
    }

    public void setSendingCoins(int sendingCoins) {
        this.sendingCoins = sendingCoins;
    }

    public int getUserTaskNum() {
        return userTaskNum;
    }

    public void setUserTaskNum(int userTaskNum) {
        this.userTaskNum = userTaskNum;
    }

    public int getFinishedTaskNum() {
        return finishedTaskNum;
    }

    public void setFinishedTaskNum(int finishedTaskNum) {
        this.finishedTaskNum = finishedTaskNum;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public long getInvitationUid() {
        return invitationUid;
    }

    public void setInvitationUid(long invitationUid) {
        this.invitationUid = invitationUid;
    }

    public int getInvitationNum() {
        return invitationNum;
    }

    public void setInvitationNum(int invitationNum) {
        this.invitationNum = invitationNum;
    }

    public int getInvitationPrize() {
        return invitationPrize;
    }

    public void setInvitationPrize(int invitationPrize) {
        this.invitationPrize = invitationPrize;
    }

    public int getInvitationPrizeCoin() {
        return invitationPrizeCoin;
    }

    public void setInvitationPrizeCoin(int invitationPrizeCoin) {
        this.invitationPrizeCoin = invitationPrizeCoin;
    }

    public String getWwID() {
        return wwID;
    }

    public void setWwID(String wwID) {
        this.wwID = wwID;
    }

    public String getWwLevel() {
        return wwLevel;
    }

    public void setWwLevel(String wwLevel) {
        this.wwLevel = wwLevel;
    }

    public String getWwImg() {
        return wwImg;
    }

    public void setWwImg(String wwImg) {
        this.wwImg = wwImg;
    }

    public String getWwRefuseReason() {
        return wwRefuseReason;
    }

    public void setWwRefuseReason(String wwRefuseReason) {
        this.wwRefuseReason = wwRefuseReason;
    }

    public String getJdAccount() {
        return jdAccount;
    }

    public void setJdAccount(String jdAccount) {
        this.jdAccount = jdAccount;
    }

    public String getJdImg() {
        return jdImg;
    }

    public void setJdImg(String jdImg) {
        this.jdImg = jdImg;
    }

    public String getJdRefuseReason() {
        return jdRefuseReason;
    }

    public void setJdRefuseReason(String jdRefuseReason) {
        this.jdRefuseReason = jdRefuseReason;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardImg() {
        return idCardImg;
    }

    public void setIdCardImg(String idCardImg) {
        this.idCardImg = idCardImg;
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

    public BigDecimal getStandMoney() {
        return standMoney;
    }

    public void setStandMoney(BigDecimal standMoney) {
        this.standMoney = standMoney;
    }

    public long getTaobaoScore() {
        return taobaoScore;
    }

    public void setTaobaoScore(long taobaoScore) {
        this.taobaoScore = taobaoScore;
    }

    public long getJdScore() {
        return jdScore;
    }

    public void setJdScore(long jdScore) {
        this.jdScore = jdScore;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRegisteTime() {
        return registeTime;
    }

    public void setRegisteTime(String registeTime) {
        this.registeTime = registeTime;
    }

    public String getForbiddenTime() {
        return forbiddenTime;
    }

    public void setForbiddenTime(String forbiddenTime) {
        this.forbiddenTime = forbiddenTime;
    }

    public String getForbiddenReason() {
        return forbiddenReason;
    }

    public void setForbiddenReason(String forbiddenReason) {
        this.forbiddenReason = forbiddenReason;
    }

    public List<BuyAccount> getBuyAccountList() {
        return buyAccountList;
    }

    public void setBuyAccountList(List<BuyAccount> buyAccountList) {
        this.buyAccountList = buyAccountList;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public int getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(int invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
