package com.liucan.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class LedisBase {
    protected final Logger LOG = LoggerFactory.getLogger(LedisBase.class);
    private static Jedis jedis;
    public static int EXPIRE = 3000;

    //只初始化一次
    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("properties/redis");
            if (bundle != null) {
                EXPIRE = Integer.valueOf(bundle.getString("redis.expire"));

                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMinIdle(Integer.valueOf(bundle.getString("redis.pool.minIdle")));
                jedisPoolConfig.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
                jedisPoolConfig.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
                jedisPoolConfig.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
                jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
                jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));

                JedisPool jedisPool = new JedisPool(
                        jedisPoolConfig,
                        bundle.getString("redis.ip"),
                        Integer.valueOf(bundle.getString("redis.port")),
                        Integer.valueOf(bundle.getString("redis.timeout")),
                        bundle.getString("redis.password"));
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Jedis getJedis() {
        return LedisBase.jedis;
    }
}
