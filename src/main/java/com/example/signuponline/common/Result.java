package com.example.signuponline.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * 结果类
 *
 * @author yudh
 * @date 2020-12-10 13:52:11
 */
@Data
@ApiModel(value = "响应结果")
public class Result<T> {
    /**
     * 返回码 非0即失败
     */
    @ApiModelProperty(value = "状态码，0：成功，-1：未登陆，1：运行异常，2：空指针异常，3:类型转换异常，4：IO异常，5：未知异常，6：数组越界异常...", example = "0")
    private int code;
    /**
     * 消息提示
     */
    @ApiModelProperty(value = "消息", example = "success")
    private String msg;
    /**
     * 返回的数据
     */
    @ApiModelProperty(value = "数据")
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
