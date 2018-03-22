package com.chenyou.admin.service;

import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.dao.BuyerIconsDao;
import com.chenyou.admin.domain.BuyerIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/18.
 */
@Service
public class BuyerIconService {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuyerIconsDao dao;

    public String getDataList(HttpServletRequest request, int page, int size){
        String result = "";
        Map<String, Object> param = new HashMap<>();
        param.put("page", page * size);
        param.put("size", size);
        List<BuyerIcon> list = dao.findAllByPage(param);
        int total = dao.getTotalCount();
        result = ToolUtil.tableFormat(list, total);
        return result;
    }

    public BuyerIcon getOne(long id){
        return dao.findOne(id);
    }

    public void editOne(BuyerIcon buyerIcon){
        dao.updateOne(buyerIcon);
    }

    public void addOne(BuyerIcon buyerIcon){
        dao.insertOne(buyerIcon);
    }

}
