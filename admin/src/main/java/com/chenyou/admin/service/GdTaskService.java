package com.chenyou.admin.service;

import com.chenyou.admin.Utils.RedisUtils;
import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.cache.CacheKeys;
import com.chenyou.admin.common.redis.GdTaskRedis;
import com.chenyou.admin.dao.BuyerDao;
import com.chenyou.admin.dao.FnIORecordDao;
import com.chenyou.admin.dao.GdTaskDao;
import com.chenyou.admin.dao.SysUserDao;
import com.chenyou.admin.domain.FnIORecord;
import com.chenyou.admin.domain.GdTask;
import com.chenyou.admin.domain.SysUser;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
public class GdTaskService {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GdTaskDao gdTaskDao;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private GdTaskRedis gdTaskRedis;

    @Autowired
    private FnIORecordDao fnIORecordDao;

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private BuyerDao buyerDao;

    public String getList(HttpServletRequest request,int page,int size){
        List<GdTask> gdTaskList = new ArrayList<>();
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
        gdTaskList = gdTaskDao.getApproveTask(searchParam);
        int total = gdTaskDao.getApproveTaskCount(searchParam);
        if (gdTaskList != null && gdTaskList.size() > 0) {
            result = ToolUtil.tableFormat(gdTaskList, total);
        }

        return result;
    }


    @Transactional
    public int approveTask(String taskId,String sellerId,String buyerId,boolean passFlag){
        Map<String,Object> taskMap = new HashMap<>();
        taskMap.put("taskId",taskId);
        String taskApproveTime = DateFormatUtils.format(new Date(),"yyyyMMdd hh:mm:ss");
        taskMap.put("taskApproveTime",taskApproveTime);

        String gd = redisUtils.hget(CacheKeys.GD_TASK,taskId);
        GdTask gdTask = com.alibaba.fastjson.JSONObject.parseObject(gd,GdTask.class);
        if(gdTask==null){
            gdTask = gdTaskDao.findByTaskId(taskId);
        }
        //插入两条记录到进账出账表 减少商家冻结的金额 增加试客的金额comment_confirm_time
		SysUser sysSeller = sysUserDao.selectByPrimaryKey(new Long(gdTask.getSellerUid()).intValue());
		SysUser sysBuyer = sysUserDao.selectByPrimaryKey(new Long(gdTask.getUid()).intValue());
        FnIORecord sellerIORecord = new FnIORecord();
        sellerIORecord.setFreeMoney(sysSeller.getFreeMoney());
        sellerIORecord.setMoney(gdTask.getTradeMoney());
        sellerIORecord.setComment("试客完成子任务");
        String date = DateFormatUtils.format(new Date(),"yyyy-MM-dd hh:mm:ss");
        sellerIORecord.setAddTime(date);
        sellerIORecord.setUid(gdTask.getSellerUid());
        sellerIORecord.setReferenceID(taskId);
        sellerIORecord.setMoneyType(2);
        sellerIORecord.setUserAccount(sysSeller.getUserAccount());

        FnIORecord buyerIORecord = new FnIORecord();
        BigDecimal buyerFree = sysBuyer.getFreeMoney();
        buyerFree = buyerFree.add(gdTask.getTradeMoney());
        buyerIORecord.setFreeMoney(buyerFree);
        buyerIORecord.setComment("完成任务");
        buyerIORecord.setAddTime(date);
        buyerIORecord.setMoney(gdTask.getTradeMoney());
        buyerIORecord.setUid(gdTask.getUid());
        buyerIORecord.setReferenceID(taskId);
        buyerIORecord.setMoneyType(1);
        buyerIORecord.setUserAccount(gdTask.getUserAccount());
        Map<String,Object> sellerMap = new HashMap<>();
        sellerMap.put("frozenDeposit",-gdTask.getTradeMoney().doubleValue());
        sellerMap.put("uid",gdTask.getSellerUid());
        Map<String,Object> buyerMap = new HashMap<>();
        buyerMap.put("frozenDeposit",gdTask.getTradeMoney());
        buyerMap.put("uid",gdTask.getUid());
        String jsonString = null;
        if(passFlag) {
            gdTask.setTaskStatus(6);
            gdTask.setTaskApproveTime(date);
            gdTaskDao.approveTask(taskMap);
            //商家出账，在用户表中减去当前任务的交易额，增加一条记录到资金明细表
            sysUserDao.updateFrozen(sellerMap);
            fnIORecordDao.insertSelective(sellerIORecord);
            //试客进账，在用户表将待返现的钱放到freeMoney可用余额，增加一条记录到资金明细表
            buyerDao.updateWatiback(buyerMap);
            List<String> taskIdList = new ArrayList<>(); taskIdList.add(taskId);
            fnIORecordDao.insertSelective(buyerIORecord);     //加缓存
            String key = String.format(CacheKeys.USER_PULL_TASK,gdTask.getUid());
            redisUtils.setList(key,taskIdList);
            //减去库存，增加prize_num中奖数量

            jsonString = com.alibaba.fastjson.JSONObject.toJSONString(gdTask);
            //修改缓存里面的任务状态
            redisUtils.hset(CacheKeys.GD_TASK, gdTask.getTaskId(), jsonString);
        }else {
            gdTask.setTaskStatus(7);
            gdTaskDao.updateTask(gdTask);
            jsonString = com.alibaba.fastjson.JSONObject.toJSONString(gdTask);
            redisUtils.hset(CacheKeys.GD_TASK, gdTask.getTaskId(), jsonString);
        }
        return 1;
    }

    public List<GdTask> getUserGdTaskList(long uid) {
        List<GdTask> list = gdTaskRedis.getGdTaskPipeline(uid);
        return list;
    }

    /**
     * 查询待抽奖GdTask List
     * @param goodsId
     * @return
     */
    public List<GdTask> getLotteryTasks(long goodsId) {
        List<GdTask> loteryList = gdTaskDao.findLotteryTasks(goodsId);
        return loteryList;
    }

    /**
     * 把抽奖到的数据更新成中奖状态
     * @param list
     */
    public void batchUpdateLuckyTasks(List<GdTask> list) {
        if (list == null || list.size() == 0 )
            return;
        gdTaskDao.batchUpdate(list);
        for (GdTask gdTask : list) {
            gdTaskRedis.editOne(gdTask);
        }
    }
    public void update(GdTask gdTask){
        gdTaskDao.updateTask(gdTask);
    }
}
