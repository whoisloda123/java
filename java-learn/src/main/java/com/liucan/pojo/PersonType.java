package com.liucan.pojo;

import lombok.Getter;

/**
 * @author liucan
 * @version 18-12-16
 */
public enum PersonType {
    STUDENT((byte) 1, "学生"),
    TEACHEAER((byte) 2, "老师");

    @Getter
    private byte type;
    private String msg;

    PersonType(byte type, String msg) {
        this.type = type;
        this.msg = msg;
    }
}
