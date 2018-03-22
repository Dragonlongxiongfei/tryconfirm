package com.chenyou.admin.service;

import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.dao.SellerVipDao;
import com.chenyou.admin.domain.SellerVip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/13.
 */
@Service
public class SellerVipService {

    @Autowired
    private SellerVipDao sellerVipDao;

    public SellerVip findOne(long vipId){
        return sellerVipDao.findOne(vipId);
    }

    public String page(HttpServletRequest request,int page,int size){
        String result = "";

        Map<String, Object> searchParam = new HashMap<>();
        Map<String, String[]> requestMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry: requestMap.entrySet()){
            String key = entry.getKey();
            String[] value  = entry.getValue();
            if ("page".equals(key) || "size".equals(key))
                continue;
            if (value.length == 1){
                searchParam.put(key, value[0]);
            } else {
                searchParam.put(key, value);
            }
        }
        searchParam.put("page", page * size);
        searchParam.put("size", size);

        List<SellerVip> buyers = sellerVipDao.page(searchParam);
        int total = sellerVipDao.getAllCount(searchParam);
        if (buyers != null && buyers.size() > 0) {
            result = ToolUtil.tableFormat(buyers, total);
        }

        return result;
    }

    public int update(SellerVip sellerVip){
        return sellerVipDao.update(sellerVip);
    }

    public int insert(SellerVip sellerVip){
        java.util.Date date = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        sellerVip.setAddTime(simpleDateFormat.format(date));
        return sellerVipDao.insert(sellerVip);
    }

}
