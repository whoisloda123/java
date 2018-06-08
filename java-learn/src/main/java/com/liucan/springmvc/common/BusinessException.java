package com.liucan.springmvc.common;

/**
 * @author liucan
 * @date 2018/6/3
 * @brief
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
