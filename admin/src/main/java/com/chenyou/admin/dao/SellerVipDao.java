package com.chenyou.admin.dao;

import com.chenyou.admin.domain.SellerVip;

import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/13.
 */
public interface SellerVipDao {
    SellerVip findOne(long vipId);

    int getAllCount(Map<String, Object> map);

    List<SellerVip> page(Map<String, Object> map);

    int insert(SellerVip sellerVip);

    int update(SellerVip sellerVip);

}
