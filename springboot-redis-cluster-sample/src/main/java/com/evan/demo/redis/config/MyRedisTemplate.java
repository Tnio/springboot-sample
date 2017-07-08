package com.evan.demo.redis.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;

@Component
public class MyRedisTemplate {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MyRedisTemplate.class);

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private RedisProperties redisProperties;

    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * * 设置缓存
     * * @param prefix 缓存前缀（用于区分缓存，防止缓存键值重复）
     * * @param key    缓存key
     * * @param value  缓存value
     */
    public void set(String prefix, String key, String value) {
        set(prefix + KEY_SPLIT + key, value);
    }

    /**
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        jedisCluster.set(key, value);
        LOGGER.debug("RedisUtil:set cache key={},value={}", key, value);
    }

    /**
     * @param prefix
     * @param key
     * @param value
     */
    public void set(String prefix, String key, Object value) {
        set(prefix + KEY_SPLIT + key, value);
    }

    /**
     * *缓存对象
     */

    public void set(String key, Object value) {
        try {
            jedisCluster.set((key).getBytes(), objectMapper.writeValueAsBytes(value));
            LOGGER.debug("RedisUtil:set cache key={},value={}", key, value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    /**
     * * 设置缓存，并且自己指定过期时间
     * * @param prefix
     * * @param key
     * * @param value
     * * @param expireTime 过期时间
     */
    public void setWithExpireTime(String prefix, String key, String value, int expireTime) {
        setWithExpireTime(prefix + KEY_SPLIT + key, value, expireTime);
    }

    /**
     * * 设置缓存，并且由配置文件指定过期时间
     * * @param prefix
     * * @param key
     * * @param value
     */
    public void setWithExpireTimeDefalut(String prefix, String key, String value) {
        setWithExpireTimeDefalut(prefix + KEY_SPLIT + key, value);
    }

    /**
     * @param prefix
     * @param key
     * @param value
     * @param expireTime
     */
    public void setWithExpireTime(String prefix, String key, Object value, int expireTime) {
        setWithExpireTime(prefix + KEY_SPLIT + key, value, expireTime);
    }

    /**
     * @param prefix
     * @param key
     * @param value
     */
    public void setWithExpireTimeDefalut(String prefix, String key, Object value) {
        setWithExpireTimeDefalut(prefix + KEY_SPLIT + key, value);
    }

    /**
     * @param key
     * @param value
     */
    public void setWithExpireTime(String key, String value, int expireTime) {
        jedisCluster.setex(key, expireTime, value);
        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value,
                expireTime);
    }

    /**
     * @param key
     * @param value
     */
    public void setWithExpireTimeDefalut(String key, String value) {
        int EXPIRE_SECONDS = redisProperties.getExpireSeconds();
        setWithExpireTime(key, value, EXPIRE_SECONDS);
        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value,
                EXPIRE_SECONDS);
    }

    /**
     * @param key
     * @param value
     * @param expireTime
     */
    public void setWithExpireTime(String key, Object value, int expireTime) {
        try {
            jedisCluster.setex((key).getBytes(), expireTime, objectMapper.writeValueAsBytes(value));
            LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value,
                    expireTime);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key
     * @param value
     */
    public void setWithExpireTimeDefalut(String key, Object value) {
        try {
            int EXPIRE_SECONDS = redisProperties.getExpireSeconds();
            setWithExpireTime(key, value, EXPIRE_SECONDS);
            LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value,
                    EXPIRE_SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * * 获取指定key的缓存
     * * @param prefix
     * * @param key
     */
    public String get(String prefix, String key) {
        String value = get(key);
        return value;
    }

    /**
     * @param key
     * @return
     */
    public String get(String key) {
        String value = jedisCluster.get(key);
        LOGGER.debug("RedisUtil:get cache key={},value={}", key, value);
        return value;
    }

    /**
     * 获取对象
     */
    public <T> T get(String prefix, final String key, final Class<T> clazz1) {
        return get(prefix + KEY_SPLIT + key, clazz1);
    }

    /**
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(final String key, final Class<T> clazz) {
        if (jedisCluster.exists(key)) {
            byte[] data = jedisCluster.get(key.getBytes());
            try {
                return objectMapper.readValue(data, clazz);
            } catch (IOException e) {
                LOGGER.error("MyRedisTemplate.get(): " + e.getMessage());
            }
        }
        return null;
    }

    /**
     * * 删除指定key的缓存
     * * @param prefix
     * * @param key
     */
    public void deleteWithPrefix(String prefix, String key) {
        delete(prefix + KEY_SPLIT + key);
    }

    /**
     * @param key
     */
    public void delete(String key) {
        jedisCluster.del(key);
        LOGGER.debug("RedisUtil:delete cache key={}", key);
    }

}
