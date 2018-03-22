package com.chenyou.admin.dao;

import com.chenyou.admin.domain.BuyAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/13.
 */
public interface BuyAccountDao {
    List<BuyAccount> getAllByPage(Map<String, Object> param);
    int getTotal(Map<String, Object> param);

    BuyAccount findOne(long id);
    void updateOne(HashMap<String, Object> params);
}
