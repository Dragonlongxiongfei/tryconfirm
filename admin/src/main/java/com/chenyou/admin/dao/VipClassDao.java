package com.chenyou.admin.dao;

import com.chenyou.admin.domain.VipClass;

import java.util.List;

/**
 * Created by Shell Li on 2017/12/11.
 */
public interface VipClassDao {

    List<VipClass> getAll();
    VipClass findOne(long vipIp);

    void saveOne(VipClass vipClass);
    void updateOne(VipClass vipClass);

}
