package com.example.signuponline.bean;

import lombok.Data;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-15 16:47:33
 */
@Data
public class Group {
    private String id;
    private String openid;
    private String name;
    private String createTime;

    public Group(String id, String openid, String name, String createTime) {
        this.id = id;
        this.openid = openid;
        this.name = name;
        this.createTime = createTime;
    }
    public Group(){}
}
