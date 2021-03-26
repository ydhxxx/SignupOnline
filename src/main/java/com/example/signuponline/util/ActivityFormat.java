package com.example.signuponline.util;

import com.alibaba.fastjson.JSONObject;
import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;

import java.beans.Introspector;
import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-13 14:07:11
 */
public class ActivityFormat {

    public static List<Activity> format(List<Activity> list){
        for(Activity activity :list){
            String startTime=activity.getStartTime();
            String endTime=activity.getEndTime();
            activity.setStartTime(TimeFormat.transForHome(startTime,endTime));
            String address=activity.getAddress();
            if("".equals(activity.getAddress())){
                activity.setAddress("线上活动");
            }
            else {
                activity.setAddress(AddressFormat.formateForHome(address));
            }
        }
        return list;
    }

    public static List<GatherActivity> formatForGather(List<GatherActivity> list){
        for(GatherActivity object :list){
            String startTime=object.getStartTime();
            String endTime=object.getEndTime();
            object.setStartTime(TimeFormat.transForHome(startTime,endTime));
        }
        return list;
    }
}
