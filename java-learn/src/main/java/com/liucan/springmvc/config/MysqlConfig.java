package com.liucan.springmvc.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author liucan
 * @date 2018/7/1
 * @brief mysql配置
 */
@Getter
@Configuration
@PropertySource(value = "classpath:db/mysql.properties")
public class MysqlConfig {
    @Value("${mysql.driver}")
    private String driver;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;
}
