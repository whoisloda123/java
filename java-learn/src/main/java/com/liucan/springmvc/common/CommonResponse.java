package com.liucan.springmvc.common;

import lombok.Data;

/**
 * @author liucan
 * @date 2018/6/3
 * @brief 通用返回response
 */
@Data
public class CommonResponse {
    private int code; //返回code
    private String message; //返回信息
    private Object data; //返回数据

    private CommonResponse() {

    }

    public static CommonResponse ok(Object data) {
        CommonResponse response = new CommonResponse();
        response.setCode(0);
        response.setMessage("返回成功!");
        response.setData(data);
        return response;
    }

    public static CommonResponse ok(int code, String message, Object data) {
        CommonResponse response = new CommonResponse();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static CommonResponse error(int code, String message) {
        CommonResponse response = new CommonResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
