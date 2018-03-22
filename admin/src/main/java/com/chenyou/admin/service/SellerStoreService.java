package com.chenyou.admin.service;

import com.chenyou.admin.Utils.RedisUtils;
import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.cache.CacheKeys;
import com.chenyou.admin.dao.SellerStoreDao;
import com.chenyou.admin.domain.SellerStore;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/12.
 *
 * 店铺管理
 */
@Service
public class SellerStoreService {

    @Autowired
    private SellerStoreDao sellerStoreDao;

    @Autowired
    private RedisUtils redisUtils;

    public String page(HttpServletRequest request,int page,int size){
        Map<String,Object> params = new HashedMap();
        Map<String,String[]> map = request.getParameterMap();
        for (Map.Entry<String,String[]> pa: map.entrySet()) {
            String key = pa.getKey();
            String [] value = pa.getValue();
            if("page".equals(key)|| "size".equals(key))
                continue;
            if(value.length==1)
                params.put(key,value[0]);
            else
                params.put(key,value);
        }
        params.put("page",page*size);
        params.put("size",size);
        List<SellerStore> sellerStores = sellerStoreDao.findAll(params);
        int total = sellerStoreDao.getTotal(params);
        return ToolUtil.tableFormat(sellerStores,total);
    }

    public void update(String storeId,String uid){
        sellerStoreDao.deleteByPrimaryKey(storeId);
        String key = String.format(CacheKeys.SELLER_STORE_LIST,uid);
        redisUtils.del(key);
    }
}
