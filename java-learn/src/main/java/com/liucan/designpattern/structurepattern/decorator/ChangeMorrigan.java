package com.liucan.designpattern.structurepattern.decorator;

/**
 * 抽象装饰
 * <p>
 * 抽象装饰角色：变形
 * 也可以不是抽象装饰，直接在ChangeMorrigan的display里面添加额外的功能
 *
 * @author liucan
 * @version 19-3-26
 */
public class ChangeMorrigan implements Morrigan {

    private Morrigan morrigan;

    public ChangeMorrigan(Morrigan morrigan) {
        this.morrigan = morrigan;
    }

    @Override
    public void display() {
        morrigan.display();
    }
}
