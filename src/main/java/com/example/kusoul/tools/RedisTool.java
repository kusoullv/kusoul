package com.example.kusoul.tools;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisTool {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增redis缓存
     * @param key
     * @param value
     */
    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key,value);
    }

    /**
     * 获取对应key的缓存
     * @param key
     * @return
     */
    public String getValue(String key) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get(key);
    }

    public void delValue(String key) {
        redisTemplate.delete(key);
    }



}
