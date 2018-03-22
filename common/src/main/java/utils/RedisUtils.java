package utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by amoslong on 2017/12/26.
 */
@Component
public class RedisUtils<T>{
    private final  Logger log = LoggerFactory.getLogger(RedisUtils.class);
    @Autowired
    private  JedisPool jedisPool;

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized  Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Redis缓存获取Jedis实例 出错！", e);
            return null;
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public  void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 向缓存中设置字符串内容
     *
     * @param key
     *            key
     * @param value
     *            value
     * @return
     * @throws Exception
     */
    public  boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if(jedis != null){
                jedis.set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("Redis缓存设置key值 出错！", e);
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 判断key是否存在
     */
    public  boolean exists(String key){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if (jedis == null) {
                return false;
            } else {
                return jedis.exists(key);
            }
        } catch (Exception e) {
            log.error("Redis缓存判断key是否存在 出错！", e);
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 删除缓存中的对象，根据key
     * @param key
     * @return
     */
    public  boolean del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }


    //*******************key-value****************start

    /**
     * 向缓存中设置对象
     *
     * @param key
     * @param value
     * @return
     */
    public  boolean set(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String objectJson = JSONObject.toJSONString(value).toString();
            jedis = getJedis();
            if (jedis != null) {
                jedis.set(key, objectJson);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    public void hset(String prefix,String key ,Object value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String jsonString = com.alibaba.fastjson.JSONObject.toJSONString(value);
            jedis.hset(prefix, key, jsonString);
        } catch (Exception e) {
            log.error("hset cache error: ", e);
        } finally {
            returnResource(jedis);
        }
        return ;
    }

    public String hget(String prefix,String key) {
        Jedis jedis = null;
        String jsonString =  null;
        try {
            jedis = getJedis();
            jsonString = jedis.hget(prefix, key);
        } catch (Exception e) {
            log.error("hget cache error: ", e);
        } finally {
            returnResource(jedis);
        }
        return jsonString;
    }

    /**
     * 根据key 获取内容
     *
     * @param key
     * @return
     */
    public  Object get(String key) {
         Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Object value = jedis.get(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public  <T> T get(String key, Class<T> clazz) {
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if (jedis != null) {
                return (T) JSONObject.parseObject(JSONObject.toJSONString(jedis.get(key)), clazz);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }
    //*******************key-value****************end

    //*************** 操作list****************start
    /**
     * 向缓存中设置对象 
     * @param key
     * @param list
     * T string calss
     * @return
     */
    public  <T> boolean setList(String key,List<T> list){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                for (T vz : list) {
                    if (vz instanceof String) {
                        jedis.lpush(key, (String) vz);
                    } else {
                        String objectJson = JSONObject.toJSONString(vz).toString();
                        jedis.lpush(key, objectJson);
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }


    @SuppressWarnings("unchecked")
    /*public  <T> List<T> getListEntity(String key,Class<T> entityClass){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if (jedis != null) {
                List<String> valueJson = jedis.lrange(key, 0, -1);
                JSONObject json = new JSONObject();
                json.putAll(valueJson);
//                JSONObject jsonArray = JSONObject.parseObject(json.toString());
                return (List<T>) JSONArray.parseArray(json.toString(), entityClass);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }*/

    public  List<String> getListString(String key){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if (jedis != null) {
                return jedis.lrange(key, 0, -1);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }
    //*************** 操作list****************end

    //*************** 操作map****************start
    public  <K,V> boolean setMap(String key,Map<String,V> map){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                Set<Map.Entry<String, V>> entry = map.entrySet();
                for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext();) {
                    Map.Entry<String, V> kv = ite.next();
                    if (kv.getValue() instanceof String) {
                        jedis.hset(key, kv.getKey(), (String) kv.getValue());
                    }else if (kv.getValue() instanceof List) {
                        jedis.hset(key, kv.getKey(), JSONArray.toJSONString(kv.getValue()).toString());
                    } else {
                        jedis.hset(key, kv.getKey(), JSONObject.toJSONString(kv.getValue()).toString());
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    public  boolean setMapKey(String key,String mapKey,Object value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                if (value instanceof String) {
                    jedis.hset(key, mapKey, String.valueOf(value));
                } else if (value instanceof List) {
                    jedis.hset(key, mapKey, JSONArray.toJSONString(value).toString());
                } else {
                    jedis.hset(key, mapKey, JSONObject.toJSONString(value).toString());
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * seconds key和value 保存的有效时间（单位：秒）
     * @return
     */
    public  boolean setMapKeyExpire(String key,String mapKey,Object value, int seconds){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                if (value instanceof String) {
                    jedis.hset(key, mapKey, String.valueOf(value));
                } else if (value instanceof List) {
                    jedis.hset(key, mapKey, JSONArray.toJSONString(value).toString());
                } else {
                    jedis.hset(key, mapKey, JSONObject.toJSONString(value).toString());
                }
                jedis.expire(key, seconds);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("unchecked")
    public  <K,V> Map<String,V> getMap(String key){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if (jedis != null) {
                Map<String, V> map = (Map<String, V>) jedis.hgetAll(key);
                return map;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("unchecked")
    public  <K,V> Map<String,V> getMapEntityClass(String key,Class<V> clazz){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if (jedis != null) {
                Map<String, V> map = (Map<String, V>) jedis.hgetAll(key);
                Set<Map.Entry<String, V>> entry = map.entrySet();
                for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext();) {
                    Map.Entry<String, V> kv = ite.next();
                    map.put(kv.getKey(), (V) JSONObject.parseObject(JSONObject.toJSONString(kv.getValue()), clazz));
                }
                return map;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }

//    @SuppressWarnings("unchecked")
   /* public  <K,V> Map<String,List<V>> getMapList(String key,Class<V> clazz){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if (jedis != null) {
                Map<String, V> map = (Map<String, V>) jedis.hgetAll(key);
                Set<Map.Entry<String, V>> entry = map.entrySet();
                for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext();) {
                    Map.Entry<String, V> kv = ite.next();
                    JSONArray jsonArray = JSONObject.parseArray(kv.getValue());
                    map.put(kv.getKey(), (V) JSONArray.pa(jsonArray, clazz));
                }
                return (Map<String, List<V>>) map;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("unchecked")
    public  <T> List<T> getMapKeyListEntity(String key,String mapKey,
                                                  Class<T> entityClass){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if (jedis != null) {
                String valueJson = jedis.hget(key, mapKey);
                JSONArray jsonArray = JSONArray.fromObject(valueJson);
                return (List<T>) JSONArray.toCollection(jsonArray, entityClass);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }*/

    @SuppressWarnings("unchecked")
    public  <T> T getMapKeyEntity(String key,String mapKey,
                                        Class<T> entityClass){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if(jedis != null){
                String valueJson=jedis.hget(key, mapKey);
                return (T) JSONObject.parseObject(JSONObject.toJSONString(valueJson), entityClass);
            }else{return null;}
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    public  Object getMapKey(String key,String mapKey){
         Jedis jedis = null;
        try {
            jedis =  getJedis();
            if(jedis != null){
                return jedis.hget(key, mapKey);
            }else{return null;}
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    public  boolean delMapKey(String key,String mapKey){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hdel(key, mapKey);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    public  boolean hexists(String key,String mapKey){
         Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hexists(key,mapKey);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }
}
