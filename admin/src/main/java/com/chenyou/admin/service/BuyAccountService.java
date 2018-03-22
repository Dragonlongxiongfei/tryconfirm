package com.chenyou.admin.service;


import com.alibaba.fastjson.JSONObject;
import com.chenyou.admin.Utils.RedisUtils;
import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.cache.CacheKeys;
import com.chenyou.admin.dao.BuyAccountDao;
import com.chenyou.admin.domain.BuyAccount;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Shell Li on 2017/12/13.
 */
@Service
public class BuyAccountService {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuyAccountDao buyAccountDao;
    @Autowired
    private RedisUtils redisUtils;

    public String datalist(HttpServletRequest request, int page, int size){
        String result = "";
        HashMap<String, Object> param = new HashMap<>();
        param.put("page", page * size);
        param.put("size", size);

        List<BuyAccount> results = buyAccountDao.getAllByPage(param);
        int total = buyAccountDao.getTotal(param);
        result = ToolUtil.tableFormat(results, total);
        return result;
    }

    public BuyAccount getOne(long id){
        return buyAccountDao.findOne(id);
    }

    public void approveById(long id, int bstatus, String comment, String refuseComment){
       HashMap<String, Object> param = new HashMap<>();
       if (!StringUtils.isEmpty(comment)) {
           param.put("comment", comment);
       }
       if (!StringUtils.isEmpty(refuseComment)) {
            param.put("refuseComment", refuseComment);
       }
       param.put("bstatus", bstatus);
       param.put("id", id);
       buyAccountDao.updateOne(param);
       BuyAccount buyAccount = buyAccountDao.findOne(id);
       buyAccount.setBstatus(bstatus);
       buyAccount.setRefuseComment(refuseComment);
       buyAccount.setComment(comment);
       String key = CacheKeys.BUY_ACCOUNT_TAOBAO;
       String jsonString = JSONObject.toJSONString(buyAccount);
       redisUtils.hset(key, buyAccount.getBuyAccount(), jsonString);
    }


}
