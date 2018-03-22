package com.chenyou.admin.dao;

import com.chenyou.admin.domain.CoupRecord;

import java.util.List;

/**
 * Created by Shell Li on 2017/12/21.
 */
public interface CoupRecordDao {

    void insertOne(CoupRecord coupRecord);
    void insertList(List<CoupRecord> list);


}
