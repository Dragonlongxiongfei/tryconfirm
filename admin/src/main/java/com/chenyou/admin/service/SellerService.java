package com.chenyou.admin.service;


import com.chenyou.admin.Utils.RedisUtils;
import com.chenyou.admin.Utils.SerialNumberUtil;
import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.cache.CacheKeys;
import com.chenyou.admin.dao.FnIORecordDao;
import com.chenyou.admin.dao.FnRechargeRecordDao;
import com.chenyou.admin.dao.SellerDao;
import com.chenyou.admin.dao.SellerStoreDao;
import com.chenyou.admin.domain.FnIORecord;
import com.chenyou.admin.domain.FnRechargeRecord;
import com.chenyou.admin.domain.SysUserSeller;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by amoslong on 2017/12/11.
 */
@Service
public class SellerService {

    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SellerStoreDao sellerStoreDao;

    @Autowired
    private FnIORecordDao fnIORecordDao;

    @Autowired
    private FnRechargeRecordDao fnRechargeRecordDao;


    public String searchBuyers(HttpServletRequest request, int page, int size){
        String result = "";

        Map<String, Object> searchParam = new HashMap<>();
        Map<String, String[]> requestMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry: requestMap.entrySet()){
            String key = entry.getKey();
            String[] value  = entry.getValue();
            if ("page".equals(key) || "size".equals(key))
                continue;
            if (value.length == 1){
                searchParam.put(key, value[0]);
            } else {
                searchParam.put(key, value);
            }
        }
        searchParam.put("page", page * size);
        searchParam.put("size", size);

        List<SysUserSeller> sellers = sellerDao.findBuyerPagable(searchParam);
        int total = sellerDao.getAllCount(searchParam);
        if (sellers != null && sellers.size() > 0) {
            result = ToolUtil.tableFormat(sellers, total);
        }

        return result;
    }

    public SysUserSeller findOneBuyer(long uid){
        return sellerDao.findOne(uid);
    }

    @Transactional
    public void saveSeller(String uid,String updateUser,String price,String xuanze,String vipEndTime,String payNo,String payAccount,String payType,String payFinishTime){
        SysUserSeller sysUserSeller = sellerDao.findOne(Long.parseLong(uid));
        FnRechargeRecord fnRechargeRecord = new FnRechargeRecord();
        FnIORecord fnIORecord = new FnIORecord();
        BigDecimal moneyChar = new BigDecimal(price);
        String pay_sn = SerialNumberUtil.next(12L,1L);
        sysUserSeller.setSpendTotal(moneyChar.add(sysUserSeller.getSpendTotal()));

        sysUserSeller.setUpdateUser(updateUser);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date1);
        if(xuanze.equals("充值金币")){
            fnIORecord.setMoneyType(1);//出账
            fnRechargeRecord.setCharType(2);
            sysUserSeller.setCoinsNum(Integer.parseInt(price));
        }
        if(xuanze.equals("充值1年VIP")){
            rightNow.add(Calendar.DAY_OF_YEAR,1);
            rightNow.add(Calendar.YEAR,1);
            String str=sdf.format(rightNow.getTime());
            sysUserSeller.setVipEndTime(str);
            fnIORecord.setMoneyType(2);//出账
            fnRechargeRecord.setCharType(1);
            sysUserSeller.setVipLevel(2);
        }
        if(xuanze.equals("充值2年VIP")){
            rightNow.add(Calendar.DAY_OF_YEAR,2);
            rightNow.add(Calendar.YEAR,2);
            String str=sdf.format(rightNow.getTime());
            sysUserSeller.setVipEndTime(str);
            fnIORecord.setMoneyType(2);//出账
            fnRechargeRecord.setCharType(1);
            sysUserSeller.setVipLevel(2);
        }
        if(xuanze.equals("充值3年VIP")){
            rightNow.add(Calendar.DAY_OF_YEAR,3);
            rightNow.add(Calendar.YEAR,3);
            String str=sdf.format(rightNow.getTime());
            sysUserSeller.setVipEndTime(str);
            fnIORecord.setMoneyType(2);//出账
            fnRechargeRecord.setCharType(1);
            sysUserSeller.setVipLevel(2);
        }else{
            sysUserSeller.setAvailableDeposit(moneyChar.add(sysUserSeller.getAvailableDeposit()));
            BigDecimal freeMoney = sysUserSeller.getFreeMoney();
            sysUserSeller.setFreeMoney(moneyChar.add(freeMoney));
            fnIORecord.setFreeMoney(moneyChar.add(freeMoney));
            fnIORecord.setMoneyType(1);//进账
            fnRechargeRecord.setCharType(3);
        }
        sellerDao.updateSeller(sysUserSeller);
//        sysUserSeller = sellerDao.findOne(sysUserSeller.getUid());
        String date = DateFormatUtils.format(new Date(),"yyyy-MM-dd hh:mm:ss");
        fnRechargeRecord.setId(pay_sn);
        fnRechargeRecord.setUid(sysUserSeller.getUid());
        fnRechargeRecord.setUserAccount(sysUserSeller.getUserAccount());

        fnRechargeRecord.setCharMoney(moneyChar);
        fnRechargeRecord.setPayType(Integer.parseInt(payType));
        fnRechargeRecord.setPayAccount(payAccount);
        fnRechargeRecord.setPayNo(payNo);
        fnRechargeRecord.setPayFinishTime(payFinishTime);
        fnRechargeRecord.setComment(xuanze);
        fnRechargeRecord.setAddTime(date);
        fnRechargeRecordDao.insertSelective(fnRechargeRecord);

        fnIORecord.setUid(sysUserSeller.getUid());
        fnIORecord.setUserAccount(sysUserSeller.getUserAccount());
        fnIORecord.setAddTime(date);
        fnIORecord.setMoney(moneyChar);
        fnIORecord.setComment(xuanze);

        fnIORecordDao.insertSelective(fnIORecord);
    }

    @Transactional
    public void saveSeller(SysUserSeller sysUserSeller){
        sellerDao.updateSeller(sysUserSeller);
    }
    @Transactional
    public void delete(String uid){
        sellerDao.deleteByPrimaryKey(uid);
        sellerStoreDao.deleteByUid(uid);
        String key = String.format(CacheKeys.SELLER_STORE_LIST,uid);
        redisUtils.del(key);
    }

}
