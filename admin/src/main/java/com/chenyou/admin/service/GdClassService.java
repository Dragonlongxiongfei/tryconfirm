package com.chenyou.admin.service;

import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.dao.GdClassDao;
import com.chenyou.admin.domain.GdClass;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/11.
 */

@Service
public class GdClassService {
    @Autowired
    private GdClassDao gdClassDao;

    public String page(HttpServletRequest request,int page ,int size){
        String result = "";
        Map<String,Object> params  = new HashedMap();
        Map<String, String[]> requestMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry: requestMap.entrySet()){
            String key = entry.getKey();
            String[] value  = entry.getValue();
            if ("page".equals(key) || "size".equals(key))
                continue;
            if (value.length == 1){
                params.put(key, value[0]);
            } else {
                params.put(key, value);
            }
        }
        params.put("page", page * size);
        params.put("size", size);
        List<GdClass> list = gdClassDao.findGdClassPagable(params);
        int total = gdClassDao.getAllCount(params);
        if(list!= null && list.size()>0){
            result = ToolUtil.tableFormat(list,total);
        }
        return  result;
    }

    public int update(GdClass gdClass){
        return gdClassDao.update(gdClass);
    }

    public int insert(GdClass gdClass){
        return gdClassDao.insert(gdClass);
    }
}
