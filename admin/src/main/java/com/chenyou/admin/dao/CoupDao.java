package com.chenyou.admin.dao;

import com.chenyou.admin.domain.Coup;

import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/16.
 */
public interface CoupDao {
    List<Coup> findAllByPage(Map<String, Object> param);
    int findTotal();
    Coup findOne(long id);
    void updateOne(Coup coup);
    void insertOne(Coup Coup);
}
