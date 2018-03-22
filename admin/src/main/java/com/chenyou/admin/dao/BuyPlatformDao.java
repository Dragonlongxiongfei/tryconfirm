package com.chenyou.admin.dao;

import com.chenyou.admin.domain.BuyPlatform;

import java.util.List;

/**
 * Created by Shell Li on 2017/12/12.
 */
public interface BuyPlatformDao {
    List<BuyPlatform> findAll();

    void saveOne(BuyPlatform buyPlatform);
    void updateOne(BuyPlatform buyPlatform);

    BuyPlatform findOne(long id);
}
