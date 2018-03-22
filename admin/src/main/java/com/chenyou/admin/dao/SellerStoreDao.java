package com.chenyou.admin.dao;

import com.chenyou.admin.domain.SellerStore;

import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/12.
 */
public interface SellerStoreDao {
    int getTotal(Map<String, Object> params);

    List<SellerStore> findAll(Map<String, Object> params);

    void deleteByUid(String uid);

    void deleteByPrimaryKey(String storeId);
}
