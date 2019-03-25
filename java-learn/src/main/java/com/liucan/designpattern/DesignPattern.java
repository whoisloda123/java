package com.liucan.designpattern;

import com.liucan.designpattern.structurepattern.StructurePatterns;
import org.springframework.stereotype.Component;

/**
 * 23种设计模式
 * 参考：http://c.biancheng.net/view/1317.html
 *
 * @author liucan
 * @version 19-3-19
 */
@Component
public class DesignPattern {

    public void test() {
        new StructurePatterns().test();
    }
}
