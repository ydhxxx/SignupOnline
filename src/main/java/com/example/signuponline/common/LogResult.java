package com.example.signuponline.common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;


/**
 * 异常结果
 * @author yudh
 * @since 2020-11-25
 */
public class LogResult {

    public LogResult(){};

    public static String success() {
        return success(new HashMap<>(0));
    }
    public static <T> String success(T data) {
        return JSON.toJSONString(new Result(0, "解析成功", data));
    }

    public static String failed() {
        return failed("解析失败");
    }
    public static String failed(String msg) {
        return failed(null, msg);
    }
    public static <T> String failed(T data, String msg) {
        return JSON.toJSONString(new Result(-1, msg, data));
    }
}
