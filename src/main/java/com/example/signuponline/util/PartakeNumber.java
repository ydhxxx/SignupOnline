package com.example.signuponline.util;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-04-10 13:38:41
 */
public class PartakeNumber {

    public static Map<String,Object> getNumber(List<Activity> activityList,List<GatherActivity> gatherList){
        Map<String,Object> map=new HashMap<>(2);
        int during=0;
        int done=0;
        for(Activity activity:activityList){
            if("进行中".equals(activity.getStartTime())){
                during++;
            }
            else if("已结束".equals(activity.getStartTime())){
                done++;
            }
        }
        for(GatherActivity gatherActivity:gatherList){
            if("进行中".equals(gatherActivity.getStartTime())){
                during++;
            }
            else if("已结束".equals(gatherActivity.getStartTime())){
                done++;
            }
        }
        map.put("during",during);
        map.put("done",done);
        return map;
    }
}
