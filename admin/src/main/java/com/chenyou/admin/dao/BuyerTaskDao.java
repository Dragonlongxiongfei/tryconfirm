package com.chenyou.admin.dao;

import com.chenyou.admin.domain.BuyerTaskVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/4.
 */
public interface BuyerTaskDao {

    List<BuyerTaskVO> getTasksByParams(Map<String, Object> param);
    int getTotal(Map<String, Object> param);

    BuyerTaskVO findOne(String taskID);
}
