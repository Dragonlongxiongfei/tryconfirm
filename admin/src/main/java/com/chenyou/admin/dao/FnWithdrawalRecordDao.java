package com.chenyou.admin.dao;

import com.chenyou.admin.domain.FnWithdrawalRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/25.
 */
public interface FnWithdrawalRecordDao {

    List<FnWithdrawalRecord> findListByPage(Map<String, Object> param);
    int findTotal(Map<String, Object> parma);

}
