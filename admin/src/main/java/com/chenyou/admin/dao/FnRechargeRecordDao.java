package com.chenyou.admin.dao;

import com.chenyou.admin.domain.FnRechargeRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/25.
 */
public interface FnRechargeRecordDao {
    List<FnRechargeRecord> findListByPage(Map<String, Object> param);
    List<FnRechargeRecord> findList(Map<String, Object> param);


    int findTotal(Map<String, Object> param);

    int insertSelective(FnRechargeRecord fnRechargeRecord);
}
