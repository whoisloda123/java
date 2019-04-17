package com.liucan.spring.transaction;

import com.liucan.mybatis.dao.UserOrderMapper;
import com.liucan.mybatis.mode.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

/**
 * @author liucan
 * @date 2018/5/27
 * @brief spring声明式事务管理，可通过配合aop来，建议用此配置，通过注解方式，使用的是aop的around切面
 */
@Component
public class StatementTransactionByAnno {
    @Autowired
    private UserOrderMapper userOrderMapper;

    /**
     * 1.@Transactional(rollbackFor = RuntimeException.class)
     * 2.@Transactional(rollbackFor = {RuntimeException.class, IOException.class})
     * 3.@Transactional(rollbackForClassName = "RuntimeException")
     * 4.@Transactional(rollbackForClassName = {"RuntimeException", "IOException"})
     * 5.@Transactional(noRollbackFor = exception.class, timeout = 2)
     * 6.@Transactional(noRollbackForClassName = "RuntimeException")
     */
    @Transactional(propagation = Propagation.REQUIRED, //传播级别，一个事务方法调用另外一个事务方法
            isolation = Isolation.REPEATABLE_READ, //隔离基本，2个事务同时运行
            readOnly = false,
            timeout = 5000,
            rollbackFor = Exception.class)
    public void insert() {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(354545);
        userOrder.setOrderId("31313");
        userOrder.setOrderStat(8);
        userOrder.setPrice(60);
        userOrder.setCreateTime(Date.from(Instant.now()));
        userOrder.setUpdateTime(Date.from(Instant.now()));
        userOrderMapper.insert(userOrder);
        throw new RuntimeException("sfs"); //抛出unchecked异常，触发事物，回滚
    }
}
