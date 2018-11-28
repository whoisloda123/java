package com.liucan.regex;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 18-11-25
 */
public class RegexTest extends BaseJunit4Test {
    @Autowired
    Regex regex;

    @Test
    public void example() {
        regex.example();
    }
}