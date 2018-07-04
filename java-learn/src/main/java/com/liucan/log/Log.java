package com.liucan.log;

import org.springframework.stereotype.Component;

/**
 * @author liucan
 * @date 2018/7/3
 * @brief 目前java基本上都是用，日志框架(slf4j、apache commons logging)+日志系统（log4j、log4j2、LogBack、JUL等）的方式
 *        1.日志框架：提供日志调用的接口，实际的日志输出托付给日志系统实现
 *          a.JCL(Jakarta Commons Logging)：比較流行的日志框架，非常多框架都依赖JCL，比如Spring等。
 *          b.SLF4j：提供新的API，初衷是配合Logback使用，但同一时候兼容Log4j。
 *        2.日志系统：负责输出日志
 *          a.Log4j：经典的一种日志解决方式。内部把日志系统抽象封装成Logger 、appender 、pattern 等实现。我们能够通过配置文件轻松的实现日志系统的管理和多样化配置。
 *          b.Log4j2：Log4j的2.0版本号。对Log4j进行了优化。比方支持參数API、支持异步appender、插件式架构等
 *          c.Logback：Log4j的替代产品。须要配合日志框架SLF4j使用
 *          d.JUL(java.util.logging)：JDK提供的日志系统。较混乱，不经常使用
 *        3.slf4j比log4j的优势
 *          a.门面日志，只提供接口，具体实现可以替换，很灵活
 *          b.{}占位符，在运行时才被某个提供的实际字符串所替换，提高性能，节省空间，而log4j是字符串连接
 *          c.在最终日志信息的字符串之前，debug或者info方法会检查一个特定的日志级别是不是打开了，
 *            这不仅降低了内存消耗而且预先降低了cpu去处理字符串连接命令的时间
 */
@Component
public class Log {
    public void example() {

    }
}
