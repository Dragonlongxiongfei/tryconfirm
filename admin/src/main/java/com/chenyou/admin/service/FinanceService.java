package com.chenyou.admin.service;

import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.dao.FnIORecordDao;
import com.chenyou.admin.dao.FnRechargeRecordDao;
import com.chenyou.admin.dao.FnWithdrawalRecordDao;
import com.chenyou.admin.domain.FnIORecord;
import com.chenyou.admin.domain.FnRechargeRecord;
import com.chenyou.admin.domain.FnWithdrawalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/25.
 */
@Service
public class FinanceService {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FnIORecordDao fnIORecordDao;
    @Autowired
    private FnRechargeRecordDao fnRechargeRecordDao;
    @Autowired
    private FnWithdrawalRecordDao fnWithdrawalRecordDao;

    public String rechargeList(HttpServletRequest request, int page, int size) {
        String result = null;
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount"),
                charType = request.getParameter("charType");
        Map<String, Object> param = new HashMap<>();
        param.put("uid", uid);
        param.put("userAccount", userAccount);
        param.put("charType", charType);
        param.put("page", page * size);
        param.put("size", size);
        List<FnRechargeRecord> list = fnRechargeRecordDao.findListByPage(param);
        int total = fnRechargeRecordDao.findTotal(param);
        result = ToolUtil.tableFormat(list, total);
        return result;
    }

    public List<FnRechargeRecord> rechargeListNoPage(HttpServletRequest request) {
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount"),
                charType = request.getParameter("charType");
        Map<String, Object> param = new HashMap<>();
        param.put("uid", uid);
        param.put("userAccount", userAccount);
        param.put("charType", charType);

        // TODO: 数据量大的话要分页查出来
        List<FnRechargeRecord> list = fnRechargeRecordDao.findList(param);
        return list;
    }


    public String FnIORecordList(HttpServletRequest request, Integer page, Integer size) {
        String result = null;
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount"),
                moneyType = request.getParameter("moneyType");

        Map<String, Object> param = new HashMap<>();
        param.put("uid", uid);
        param.put("userAccount", userAccount);
        param.put("moneyType", moneyType);
        param.put("page", page * size);
        param.put("size", size);
        List<FnIORecord> list = fnIORecordDao.findListByPage(param);
        int total = fnIORecordDao.findTotal(param);
        result = ToolUtil.tableFormat(list, total);
        return result;
    }

    public List<FnIORecord> getFnIORecordList(HttpServletRequest request) {
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount"),
                moneyType = request.getParameter("moneyType");
        Map<String, Object> param = new HashMap<>();
        param.put("uid", uid);
        param.put("userAccount", userAccount);
        param.put("moneyType", moneyType);

        List<FnIORecord> list = fnIORecordDao.findListByPage(param);
        return list;
    }


    public String FnWithdrawalList(HttpServletRequest request, Integer page, Integer size) {
        String result = null;
        Map<String, Object> param = new HashMap<>();
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount"),
                withdrawalType = request.getParameter("withdrawalType");
        param.put("uid", uid);
        param.put("userAccount", userAccount);
        param.put("withdrawalType", withdrawalType);
        if (page != null && size != null) {
            param.put("page", page * size);
            param.put("size", size);
        }

        List<FnWithdrawalRecord> list = fnWithdrawalRecordDao.findListByPage(param);
        int total = fnWithdrawalRecordDao.findTotal(param);
        result = ToolUtil.tableFormat(list, total);
        return result;
    }

    public List<FnWithdrawalRecord> getWithdrawalList(HttpServletRequest request, Integer page, Integer size) {
        Map<String, Object> param = new HashMap<>();
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount"),
                withdrawalType = request.getParameter("withdrawalType");
        param.put("uid", uid);
        param.put("userAccount", userAccount);
        param.put("withdrawalType", withdrawalType);
        if (page != null && size != null) {
            param.put("page", page * size);
            param.put("size", size);
        }

        List<FnWithdrawalRecord> list = fnWithdrawalRecordDao.findListByPage(param);
        return list;
    }










}
