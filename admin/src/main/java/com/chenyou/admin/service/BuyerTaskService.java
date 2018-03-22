package com.chenyou.admin.service;

import com.chenyou.admin.dao.BuyerTaskDao;
import com.chenyou.admin.domain.BuyerTaskVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/4.
 */
@Service
public class BuyerTaskService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuyerTaskDao buyerTaskDao;

    public JSONObject searchTasks(HttpServletRequest request, int page, int size){
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount");
        JSONObject result = new JSONObject();;
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("size", size);
        if (!StringUtils.isEmpty(uid)) {
            param.put("uid", uid.trim());
        }
        if (!StringUtils.isEmpty(userAccount)) {
            param.put("userAccount", userAccount.trim());
        }

        List<BuyerTaskVO> tasks = buyerTaskDao.getTasksByParams(param);
        int total = buyerTaskDao.getTotal(param);
        if (tasks != null && tasks.size() > 0) {
            result.put("total", total);
            result.put("pageNumber", tasks.size());
            result.put("rows", JSONArray.fromObject(tasks));
        }

        return result;
    }

    public BuyerTaskVO getOne(String taskID) {
        return buyerTaskDao.findOne(taskID);
    }

}
