package com.example.signuponline.bean;

import lombok.Data;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-25 16:42:03
 */
@Data
public class GatherField {
    private String gatherId;
    private String field;
    private int type;
    private String selectValue;


    public GatherField(String gatherId, String field, int type, String selectValue) {
        this.gatherId = gatherId;
        this.field = field;
        this.type = type;
        this.selectValue = selectValue;
    }

    public GatherField(){}
}
