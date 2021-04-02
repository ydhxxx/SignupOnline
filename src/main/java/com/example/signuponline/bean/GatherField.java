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
    private int id;
    private String gatherId;
    private String field;
    private int type;
    private String selectValue;


    public GatherField(int id,String gatherId, String field, int type, String selectValue) {
        this.id=id;
        this.gatherId = gatherId;
        this.field = field;
        this.type = type;
        this.selectValue = selectValue;
    }

    public GatherField(){}
}
