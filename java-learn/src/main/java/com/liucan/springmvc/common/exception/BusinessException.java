package com.liucan.springmvc.common.exception;

/**
 * @author liucan
 * @date 2018/6/3
 * @brief 业务异常
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
