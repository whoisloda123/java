package com.liucan.log;

import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @date 2018/7/3
 * @brief 目前java基本上都是用，日志框架(slf4j、apache commons logging)+日志系统（log4j、log4j2、LogBack、JUL等）的方式
 * 1.日志框架：提供日志调用的接口，实际的日志输出托付给日志系统实现
 * a.JCL(Jakarta Commons Logging)：比較流行的日志框架，非常多框架都依赖JCL，比如Spring等。
 * b.SLF4j：提供新的API，初衷是配合Logback使用，但同一时候兼容Log4j。
 * 2.日志系统：负责输出日志
 * a.Log4j：经典的一种日志解决方式。内部把日志系统抽象封装成Logger 、appender 、pattern 等实现。我们能够通过配置文件轻松的实现日志系统的管理和多样化配置。
 * b.Log4j2：Log4j的2.0版本号。对Log4j进行了优化。比方支持參数API、支持异步appender、插件式架构等
 * c.Logback：Log4j的替代产品。须要配合日志框架SLF4j使用
 * d.JUL(java.util.logging)：JDK提供的日志系统。较混乱，不经常使用
 */
@Component
public class Log {
    public void example() {

    }
}
