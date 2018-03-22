package utils;

/**
 * Created by amoslong on 2017/12/26.
 */
public interface CacheUtils<T> {
    boolean set(String key, Object value);
    boolean set(String key, Object value, Long expireTime);
    Object get(String key);
}
