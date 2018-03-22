package com.chenyou.admin.service;

import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.dao.BuyerDao;
import com.chenyou.admin.domain.SysUserBuyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/11/29.
 */
@Service
public class BuyerService {

    @Autowired
    private BuyerDao buyerDao;

    public String searchBuyers(HttpServletRequest request, int page, int size){
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

        List<SysUserBuyer> buyers = buyerDao.findBuyerPagable(searchParam);
        int total = buyerDao.getAllCount(searchParam);
        if (buyers != null && buyers.size() > 0) {
            result = ToolUtil.tableFormat(buyers, total);
        }

        return result;
    }

    public SysUserBuyer findOneBuyer(long uid){
        return buyerDao.findOne(uid);
    }

    @Transactional
    public void saveBuyer(SysUserBuyer sysUserBuyer){
        buyerDao.updateBuyer(sysUserBuyer);
    }

}
