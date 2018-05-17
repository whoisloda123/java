package com.liucan.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ResourceBundle;

public class LedisBase {
    protected final Logger LOG = LoggerFactory.getLogger(LedisBase.class);
    private JedisPool jedisPool;
    protected int EXPIRE = 130;
    protected Jedis jedis;

    public void init() {
        ResourceBundle bundle = ResourceBundle.getBundle("db/redis");
        if (bundle != null) {
            EXPIRE = Integer.valueOf(bundle.getString("redis.expire"));

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMinIdle(Integer.valueOf(bundle.getString("redis.pool.minIdle")));
            jedisPoolConfig.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
            jedisPoolConfig.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
            jedisPoolConfig.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
            jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
            jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));

            jedisPool = new JedisPool(
                    jedisPoolConfig,
                    bundle.getString("redis.ip"),
                    Integer.valueOf(bundle.getString("redis.port")),
                    Integer.valueOf(bundle.getString("redis.timeout")),
                    bundle.getString("redis.password"));
            try {
                jedis = jedisPool.getResource();
            } catch (JedisConnectionException jce) {
                jce.printStackTrace();
            }
        }
    }

    public void returnResource() {
        jedisPool.destroy();
    }
}
