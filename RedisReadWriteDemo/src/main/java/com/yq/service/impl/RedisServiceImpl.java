package com.yq.service.impl;

import com.yq.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Simple to Introduction
 * className: RedisServiceImpl
 *
 * @author EricYang
 * @version 2018/8/4 23:00
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    //private RedisTemplate<String, String> template;
    private StringRedisTemplate template;


    @Override
    public String get(String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

    @Override
    public void set(String key, String value) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        ops.set(key, value);
    }

    @Override
    public String getHash(String key, String hashKey) {
        HashOperations<String, String, String> hashOps = this.template.opsForHash();

        String value = hashOps.get(key, hashKey);
        log.info("getHash() key={},hashKey={}, value={}, threadId={}", key, hashKey, value, Thread.currentThread().getId());
        return value;
    }

    @Override
    public void setHash(String key, String hashKey, String value) {
        HashOperations<String, String, String> hashOps = this.template.opsForHash();
        hashOps.put(key, hashKey, value);
    }
}


