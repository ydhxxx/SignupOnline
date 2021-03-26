
package com.example.signuponline.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 日志实体类
 * @author yudh
 * @since 2020-12-23
 */
@Data
public class LogEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 请求用户ID */
//    private String userId;
    /** 请求的资源url */
    private String url;
    /** 请求调用的方法 */
    private String method;
    /** 请求参数 */
    private Object[] args;
    /** 请求的类型 */
    private String type;
    /** 请求的IP */
    private String ip;
}
