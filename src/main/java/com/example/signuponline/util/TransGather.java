package com.example.signuponline.util;

import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.GatherField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-25 17:02:57
 */
public class TransGather {


    public static Map<String,Object> transGather(Map<String,Object> map, GatherActivity gatherActivity,List<GatherField> listField){

        map.put("id",gatherActivity.getId());
        map.put(("openid"),gatherActivity.getOpenid());
        map.put("title",gatherActivity.getTitle());
        map.put("posterUrl",gatherActivity.getPosterUrl());
        map.put("groupId",gatherActivity.getGroupId());
        map.put("startTime",gatherActivity.getStartTime());
        map.put("endTime",gatherActivity.getEndTime());
        map.put("isAddress",false);

        List<String> inputList=new ArrayList<>();
        List<Map<String,Object>> selectList=new ArrayList<>();
        List<Map<String,Object>> selectMulList=new ArrayList<>();
        List<String> areaList=new ArrayList<>();


        for(GatherField gatherField : listField){
            switch(gatherField.getType()){
                case 0:
                    inputList.add(gatherField.getField());
                    break;
                case 1:
                    Map<String,Object> map1=new HashMap<>();
                    map1.put("name",gatherField.getField());
                    map1.put("selects",gatherField.getSelectValue().split("#"));
                    selectList.add(map1);
                    break;
                case 2:
                    Map<String,Object> map2=new HashMap<>();
                    map2.put("name",gatherField.getField());
                    map2.put("selects",gatherField.getSelectValue().split("#"));
                    selectMulList.add(map2);
                    break;
                case 3:
                    areaList.add(gatherField.getField());
                    break;
                case 4:
                    map.put("isAddress",true);
                    break;
                default:
                    break;
            }
        }
        map.put("inputList",inputList);
        map.put("selectList",selectList);
        map.put("selectMulList",selectMulList);
        map.put("areaList",areaList);

        return map;
    }
}
