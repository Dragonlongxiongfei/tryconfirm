package com.chenyou.admin.dao;

import com.chenyou.admin.domain.SysUserBuyer;

import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/11/29.
 */
public interface BuyerDao {

    List<SysUserBuyer> findBuyerPagable(Map<String, Object> params);
    SysUserBuyer findOne(long uid);
    int getAllCount(Map<String, Object> params);

    void updateBuyer(SysUserBuyer sysUserBuyer);

    List<String> getBuyerUidList(Map<String, Object> param);

    void updateWatiback(Map<String, Object> buyerMap);
}
