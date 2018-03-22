package com.chenyou.admin.dao;

import com.chenyou.admin.domain.SysUserSeller;

import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/11.
 */
public interface SellerDao {
    List<SysUserSeller> findBuyerPagable(Map<String, Object> params);
    SysUserSeller findOne(long uid);
    int getAllCount(Map<String, Object> params);

    void updateSeller(SysUserSeller sysUserSeller);
    void updateFrozen(SysUserSeller sysUserBuyer);

    void deleteByPrimaryKey(String uid);
}
