package com.liucan.spring.transaction;

import com.liucan.mybatis.dao.UserOrderMapper;
import com.liucan.mybatis.mode.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * @author liucan
 * @date 2018/5/27
 * @brief spring声明式事务管理，可通过配合aop来，建议用此配置，通过注解方式
 */
@Component
public class StatementTransactionByAnno {
    @Autowired
    private UserOrderMapper userOrderMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Transactional(rollbackFor = {RuntimeException.class, IOException.class})
    @Transactional(rollbackForClassName = "RuntimeException")
    @Transactional(rollbackForClassName = {"RuntimeException", "IOException"})
    @Transactional(noRollbackFor = Exception.class, timeout = 2)
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
