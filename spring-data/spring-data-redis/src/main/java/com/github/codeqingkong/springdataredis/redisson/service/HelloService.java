package com.github.codeqingkong.springdataredis.redisson.service;

import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: QingKong
 * @date: 2023/1/28
 */
@Service
public class HelloService {
    @Autowired
    private RedissonClient redissonClient;
    public void insert(){
        redissonClient.getBucket("name", new StringCodec("utf-8")).set("Qingkong");
    }

    public void delete(String key){
        redissonClient.getBucket(key, new StringCodec("utf-8")).delete();
    }

    public void update(String key,String value){
        redissonClient.getBucket(key, new StringCodec("utf-8")).set(value);
    }

    public String get(String key){
        return (String) redissonClient.getBucket(key, new StringCodec("utf-8")).get();
    }
}
