package com.liucan.designpattern.actionpattern;

import com.liucan.designpattern.actionpattern.command.ConcreteCommand;
import com.liucan.designpattern.actionpattern.command.Invoker;
import com.liucan.designpattern.actionpattern.command.Recever;
import com.liucan.designpattern.actionpattern.strategy.CrabCookingStrategyFactory;
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

        //策略方式模式
        new CrabCookingStrategyFactory().getCrabCookingStrategy("braisedCrabs").cookingMethod();

        //命令模式
        Invoker invoker = new Invoker(new ConcreteCommand(new Recever()));
        invoker.invoke();
    }
}
