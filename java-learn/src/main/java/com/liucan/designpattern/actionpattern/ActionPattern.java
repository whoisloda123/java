package com.liucan.designpattern.actionpattern;

import com.liucan.designpattern.actionpattern.templatemethod.StudyAbroad;
import com.liucan.designpattern.actionpattern.templatemethod.StudyInAmerica;

/**
 * 行为型模式:用于描述程序在运行时复杂的流程控制，即描述多个类或对象之间怎样相互协作共同完成单个对象都无法单独完成的任务
 *
 * @author liucan
 * @version 19-3-28
 */
public class ActionPattern {

    public void test() {
        //模板方法模式
        StudyAbroad tm = new StudyInAmerica();
        tm.templateMethod();
    }
}
