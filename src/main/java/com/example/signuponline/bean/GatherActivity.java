package com.example.signuponline.bean;

import lombok.Data;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-25 15:36:51
 */
@Data
public class GatherActivity {
    private String id;
    private String openid;
    private String title;
    private String posterUrl;
    private String groupId;
    private String startTime;
    private String endTime;
    private int persons;


    public GatherActivity(String id, String openid, String title, String posterUrl, String groupId, String startTime, String endTime,int persons) {
        this.id = id;
        this.openid = openid;
        this.title = title;
        this.posterUrl = posterUrl;
        this.groupId = groupId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.persons=persons;
    }

    public GatherActivity(){}

}
