package com.liucan.callback;

/*
* 回调函数caller
*/
public class InterfaceCaller {
    private InterfaceCallback callInterface;

    public void setCallback(InterfaceCallback callback) {
        this.callInterface = callback;
    }

    public void call() {
        if (this.callInterface != null) {
            this.callInterface.callbackFun();
        }
    }
}
