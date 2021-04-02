package com.example.signuponline.bean;

import lombok.Data;

/**
 * ...
 *
 * @author yudh
 * @date 2021-04-01 16:05:15
 */
@Data
public class GatherAnswer {
    private int id;
    private String value;
    private String openid;

    public GatherAnswer(int id, String value,String openid) {
        this.id = id;
        this.value = value;
        this.openid=openid;
    }

    public GatherAnswer(){}
}
