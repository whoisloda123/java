package com.liucan.thread;

import com.liucan.BaseJunit4Test;
import com.liucan.multthread.Thread1;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 19-1-20
 */
public class Thread1Test extends BaseJunit4Test {
    @Autowired
    private Thread1 thread1;

    @Test
    public void example() {
        thread1.example();
    }
}