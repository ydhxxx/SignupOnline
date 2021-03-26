package com.example.signuponline.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * ...
 *
 * @author yudh
 * @date 2021-03-10 22:02:50
 */
@Data
public class Activity {
    private int id;
    private String openid;
    private String title;
    private String content;
    private String type1;
    private String type2;

    private String startTime;
    private String endTime;
    private String address;
    private String posterUrl;
    private String detailUrl;
    private String groupId;
    private int persons;


    public Activity(int id, String openid, String title, String content, String type1, String type2, String startTime,
                    String endTime, String address, String posterUrl, String detailUrl, String groupId,int persons) {
        this.id = id;
        this.openid = openid;
        this.title = title;
        this.content = content;
        this.type1 = type1;
        this.type2 = type2;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.posterUrl = posterUrl;
        this.detailUrl = detailUrl;
        this.groupId = groupId;
        this.persons=persons;
    }

    public Activity(){}



}
