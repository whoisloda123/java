package com.liucan.spring.transaction;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @date 2018/5/27
 * @brief
 */
public class TransactionTest extends BaseJunit4Test {
    @Autowired
    Transaction transaction;

    @Test
    public void testExample() {
        transaction.example();
    }
}