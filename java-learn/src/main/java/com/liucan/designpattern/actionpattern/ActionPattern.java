package com.liucan.designpattern.actionpattern;

import com.liucan.designpattern.actionpattern.Chainooresponsibility.Banzhang;
import com.liucan.designpattern.actionpattern.Chainooresponsibility.Student;
import com.liucan.designpattern.actionpattern.Chainooresponsibility.Teacher;
import com.liucan.designpattern.actionpattern.command.ConcreteCommand;
import com.liucan.designpattern.actionpattern.command.Invoker;
import com.liucan.designpattern.actionpattern.command.Recever;
import com.liucan.designpattern.actionpattern.memento.MementoManager;
import com.liucan.designpattern.actionpattern.memento.Originator;
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

        //命令模式+备忘录模式,实现动作撤销和恢复
        Originator originator = new Originator(new MementoManager());
        originator.doCommand(new ConcreteCommand(new Recever("命令1")));
        originator.doCommand(new ConcreteCommand(new Recever("命令2")));
        originator.undo();
        originator.resume();

        //职责链模式
        Teacher teacher = new Teacher(null, "老师", 3);
        Banzhang banzhang = new Banzhang(teacher, "班长", 1);
        new Student().requsetLeave(banzhang, 1);
        new Student().requsetLeave(banzhang, 2);
    }
}
