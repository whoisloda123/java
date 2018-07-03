package com.liucan.springmvc.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author liucan
 * @date 2018/7/1
 * @brief redis配置
 */
@Getter
@Configuration
@PropertySource(value = "classpath:properties/redis.properties")
public class RedisConfig {
    @Value("${redis.timeout}")
    private String timeout;
    @Value("${redis.pool.maxActive}")
    private String maxActive;
    @Value("${redis.pool.maxIdle}")
    private String maxIdle;
    @Value("${redis.pool.minIdle}")
    private String minIdle;
    @Value("${redis.pool.maxWait}")
    private String maxWait;
    @Value("${redis.pool.testOnBorrow}")
    private String testOnBorrow;
    @Value("${redis.pool.testOnReturn}")
    private String testOnReturn;
    @Value("${redis.ip}")
    private String ip;
    @Value("${redis.port}")
    private String port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.expire}")
    private String expire;
}
