package com.example.signuponline.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * @author yudh
 * @date 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MyException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * 返回码 非0即失败
     */
    private int code;
    /**
     * 消息提示
     */
    private String msg;

    public MyException() {}

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;

    }
}
