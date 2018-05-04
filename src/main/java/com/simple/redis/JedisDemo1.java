package com.simple.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Create by S I M P L E on 2018/05/04 13:52:13
 */
@Slf4j
class JedisDemo1 {

    @Test
    void demo1() {
        Jedis jedis = new Jedis("39.108.60.4", 6379);
        jedis.set("name", "simple");
        String value = jedis.get("name");
        log.info("value:{}", value);
    }

    @Test
    void demo2() {
        // 获取连接池的配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxIdle(30);
        // 设置最大空闲数
        jedisPoolConfig.setMinIdle(10);
        // 获取连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "39.108.60.4", 6379);
        //获取核心对象
        Jedis jedis = null;
        try {
            // 通过连接池获取连接
            jedis = jedisPool.getResource();
            // 设置数据
            jedis.set("age", "22");
            // 获取数据
            log.info("value:{}",jedis.get("age"));
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            // 释放资源
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }


}
