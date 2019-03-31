package com.liucan.designpattern.actionpattern.visitor;

/**
 * @author liucan
 * @version 19-3-31
 */
public abstract class Employee {
    //允许一个访问者过来访问
    public abstract void accept(IVisitor visitor);
}
