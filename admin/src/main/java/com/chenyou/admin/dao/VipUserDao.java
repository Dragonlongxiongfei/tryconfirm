package com.chenyou.admin.dao;

import com.chenyou.admin.domain.VipUser;

import java.util.List;

/**
 * Created by Shell Li on 2018/2/2.
 */
public interface VipUserDao {

    VipUser findOne(int uid);

    List<VipUser> findListByUid(long uid);
}
