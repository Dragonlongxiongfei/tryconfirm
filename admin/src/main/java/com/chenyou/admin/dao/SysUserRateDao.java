package com.chenyou.admin.dao;

import com.chenyou.admin.domain.SysUserRate;

/**
 * Created by Shell Li on 2018/2/2.
 */
public interface SysUserRateDao {

    SysUserRate findOne(long uid);
    void insertOne(SysUserRate sysUserRate);
    void updateOne(SysUserRate sysUserRate);
}
