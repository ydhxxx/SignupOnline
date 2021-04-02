package com.example.signuponline.service.impl;

import com.example.signuponline.bean.*;
import com.example.signuponline.mapper.PublishMapper;
import com.example.signuponline.service.PublishService;
import com.example.signuponline.util.ActivityFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-11 21:08:17
 */
@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private PublishMapper publishMapper;


    @Override
    public void newActivity(Map<String, String> map) {
        publishMapper.newActivity(map);
    }

    @Override
    public List<Group> getMyGroup(String openid) {
        return publishMapper.getMyGroup(openid);
    }

    @Override
    public boolean newGather(Map<String, Object> map) {
        return publishMapper.newGather(map);
    }

    @Override
    public boolean newGatherField(Map<String, Object> map) {
        return publishMapper.newGatherField(map);
    }

    @Override
    public List<Activity> getMyActivity(String openid,boolean isAll) {
        if(isAll){
            return publishMapper.getMyActivity(openid);
        }
        List<Activity> list=publishMapper.getMyActivity(openid);
        return ActivityFormat.format(list);
    }

    @Override
    public List<GatherActivity> getMyGather(String openid,boolean isAll) {
        if(isAll){
            return publishMapper.getMyGather(openid);
        }
        List<GatherActivity> list=publishMapper.getMyGather(openid);
        return ActivityFormat.formatForGather(list);
    }

    @Override
    public boolean delActivity(Integer id) {
        return publishMapper.delActivity(id);
    }

    @Override
    public boolean updateActivity(Map<String, String> map) {
        return publishMapper.updateActivity(map);
    }

    @Override
    public boolean delGather(String id) {
        return publishMapper.delGather(id);
    }

    @Override
    public boolean updateGather(Map<String, String> map) {
        return publishMapper.updateGather(map);
    }

    @Override
    public List<Object> getAcPartake(int id) {
        return publishMapper.getAcPartake(id);
    }

    @Override
    public List<Object> getField(String id) {
        List<GatherField> field=publishMapper.getField(id);
        List<Object> list=new ArrayList<>();
        Map<String,Object> check=new HashMap<>();
        check.put("checkbox",true);
        list.add(check);
        for(GatherField gatherField :field){
            Map<String,Object> map=new HashMap<>();
            map.put("field",String.valueOf(gatherField.getId()));
            map.put("title",gatherField.getField());
            map.put("align","center");
            map.put("valign","middle");
            list.add(map);
        }

        return list;
    }

    @Override
    public List<Object> getGaPartake(String id) {
        List<GatherAnswer> list=publishMapper.getGaPartake(id);
        HashMap<String,Integer> openids=new HashMap<>();
        List<Object> valueList=new ArrayList<>();
        for(GatherAnswer gatherAnswer:list){
            String openid=gatherAnswer.getOpenid();
            if(openids.containsKey(openid)){
                int i=openids.get(openid);
                openids.put(openid,i+1);
            }
            else{
                openids.put(openid,1);

            }
        }
        Map<String,Object> map=new HashMap<>();
        for(GatherAnswer gatherAnswer:list){
            String openid=gatherAnswer.getOpenid();
            int fieldId=gatherAnswer.getId();
            map.put(String.valueOf(fieldId),gatherAnswer.getValue());
            int i=openids.get(openid);

            if(i==1){
                valueList.add(map);
                map=new HashMap<>();
            }
            openids.put(openid,i-1);
        }
        return valueList;
    }
}
