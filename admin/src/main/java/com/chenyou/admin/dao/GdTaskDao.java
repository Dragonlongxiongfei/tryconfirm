package com.chenyou.admin.dao;

import com.chenyou.admin.domain.GdTask;

import java.util.List;
import java.util.Map;

/**
 * Created by Shell Li on 2017/12/28.
 */
public interface GdTaskDao {


    int approveTask(Map<String, Object> map);
    List<GdTask> getApproveTask(Map<String, Object> searchParam);
    GdTask findByTaskId(String taskId);
    int getApproveTaskCount(Map<String, Object> searchParam);
    void updateTask(GdTask gdTask);
    // 查询待抽奖的任务列表
    List<GdTask> findLotteryTasks(long goods_id);

    void batchUpdate(List<GdTask> list);
}
