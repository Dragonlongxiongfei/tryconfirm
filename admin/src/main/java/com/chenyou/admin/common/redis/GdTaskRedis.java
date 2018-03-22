package com.chenyou.admin.common.redis;

import com.chenyou.admin.Utils.RedisUtils;
import com.chenyou.admin.cache.CacheKeys;
import com.chenyou.admin.dao.GdTaskDao;
import com.chenyou.admin.domain.GdTask;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shell Li on 2018/2/1.
 */
@Component
public class GdTaskRedis {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GdTaskDao gdTaskDao;
    @Autowired
    private RedisUtils redisUtils;

    public GdTask getOne(String taskId) {
        Jedis jedis = null;
        GdTask gdTask = null;
        try {
            jedis = redisUtils.getJedis();
            String jsonString = redisUtils.hget(CacheKeys.GD_TASK, taskId);
            gdTask = (GdTask) JSONObject.toBean(JSONObject.fromObject(jsonString), GdTask.class);
        } catch (Exception e) {
            log.error("GdTaskRedis get one error: ", e);
        } finally {
            redisUtils.returnResource(jedis);
        }
        return gdTask;
    }

    public void editOne(GdTask gdTask) {
        Jedis jedis = null;
        try {
            jedis = redisUtils.getJedis();
            String json = JSONObject.fromObject(gdTask).toString();
            jedis.hset(CacheKeys.GD_TASK, gdTask.getTaskId(), json);
        } catch (Exception e) {
            log.error("GdTaskRedis edit one error: ", e);
        } finally {
            redisUtils.returnResource(jedis);
        }
    }

    /**
     * 获得用户任务id 列表
     * @param uid
     * @return
     */
    public List<String> getGdTaskIdList(long uid) {
        Jedis jedis = null;
        List<String> list = null;
        try {
            jedis = redisUtils.getJedis();
            String key = String.format(CacheKeys.TAOBAO_TASK_USER_LIST, uid);
            list = jedis.lrange(key, 0, -1);
        } catch (Exception e) {
            log.error("getGdTaskIdList error: ", e);
        } finally {
            redisUtils.returnResource(jedis);
        }
        return list;
    }

    /**
     * 获得用户任务列表
     * @param uid
     * @return List<GdTask>
     */
    public List<GdTask> getGdTaskPipeline(long uid) {
        Jedis jedis = null;
        List<GdTask> data = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            jedis = redisUtils.getJedis();
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();
            List<String> taskIdlist = getGdTaskIdList(uid);
            if (taskIdlist != null && taskIdlist.size() > 0 ) {
                for (String taskId : taskIdlist) {
                    pipeline.hget(CacheKeys.GD_TASK, taskId);
                }
                Response<List<Object>> response = pipeline.exec();
                pipeline.sync(); // 关闭pipeline
                List<Object> objects = response.get();
                for (Object o : objects) {
                    GdTask gdTask = (GdTask) JSONObject.toBean(JSONObject.fromObject(o), GdTask.class);
                    String addTimeFormat = gdTask.getAddTime().substring(0, 10);
                    gdTask.setAddTime(addTimeFormat);
                    data.add(gdTask);
                }
            }
        } catch (Exception e) {
            log.error("getGdTaskPipeline error: ", e);
        } finally {
            redisUtils.returnResource(jedis);
        }
        return data;
    }

}
