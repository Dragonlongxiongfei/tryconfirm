package com.chenyou.admin.dao;

import com.chenyou.admin.domain.BuyerIcon;

import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/18.
 */
public interface BuyerIconsDao {
    List<BuyerIcon> findAllByPage(Map<String, Object> param);
    int getTotalCount();
    BuyerIcon findOne(long id);
    void updateOne(BuyerIcon buyerIcon);
    void insertOne(BuyerIcon buyerIcon);
}
