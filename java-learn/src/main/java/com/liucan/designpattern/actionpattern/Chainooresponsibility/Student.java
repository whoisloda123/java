package com.liucan.designpattern.actionpattern.Chainooresponsibility;

/**
 * @author liucan
 * @version 19-3-30
 */
public class Student {

    public boolean requsetLeave(Leader leader, int days) {
        return leader.handleLeave(days);
    }
}
