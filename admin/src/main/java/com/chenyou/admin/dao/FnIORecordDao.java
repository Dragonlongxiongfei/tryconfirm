package com.chenyou.admin.dao;

import com.chenyou.admin.domain.FnIORecord;

import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/25.
 */
public interface FnIORecordDao {

    List<FnIORecord> findListByPage(Map<String, Object> param);
    int findTotal(Map<String, Object> param);

    int insertSelective(FnIORecord fnIORecord);
}
