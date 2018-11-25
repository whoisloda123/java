package com.liucan.template;

import com.liucan.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liucan
 * @version 18-11-24
 */
public class TemplateTest extends BaseJunit4Test {
    @Autowired
    private Template template;

    @Test
    public void example() {
        template.example();
    }
}