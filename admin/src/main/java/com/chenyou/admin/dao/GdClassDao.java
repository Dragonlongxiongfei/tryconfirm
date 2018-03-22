package com.chenyou.admin.dao;

import com.chenyou.admin.domain.GdClass;

import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/11.
 */
public interface GdClassDao {

    List<GdClass> findGdClassPagable(Map<String, Object> param);

    int getAllCount(Map<String, Object> param);

    int insert(GdClass gdClass);

    int update(GdClass gdClass);

}
