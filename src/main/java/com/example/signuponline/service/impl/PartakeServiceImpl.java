package com.example.signuponline.service.impl;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.UserFlag;
import com.example.signuponline.mapper.PartakeMapper;
import com.example.signuponline.service.PartakeService;
import com.example.signuponline.util.ActivityFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-21 14:27:36
 */
@Service
public class PartakeServiceImpl implements PartakeService {

    @Autowired
    private PartakeMapper partakeMapper;

    @Override
    public boolean collect(Map<String, Object> map) {
        return partakeMapper.collect(map);
    }

    @Override
    public List<Activity> getCollect(String openid) {
        List<Activity> list=partakeMapper.getCollect(openid);
        return ActivityFormat.format(list);
    }

    @Override
    public boolean generalPartake(Map<String, Object> map) {
        return partakeMapper.generalPartake(map);
    }

    @Override
    public boolean partakeGather(Map<String, Object> map) {
        return partakeMapper.partakeGather(map);
    }

    @Override
    public UserFlag getUserFlag(String openid, Integer activityId) {
        UserFlag userFlag=new UserFlag(true,true);
        String userFlag1=partakeMapper.getUserFlagC(openid,activityId);
        String userFlag2=partakeMapper.getUserFlagP(openid,activityId);
        if(userFlag1==null){
            userFlag.setCollect(false);
        }
        if(userFlag2==null){
            userFlag.setPartake(false);
        }
        return userFlag;
    }

    @Override
    public UserFlag getGatherFlag(String openid, String gatherId) {
        String userFlag1=partakeMapper.getGatherFlag(openid,gatherId);
        UserFlag userFlag=new UserFlag(true,true);
        if(userFlag1==null){
            userFlag.setPartake(false);
        }
        return userFlag;
    }

    @Override
    public List<Activity> getActivity(String openid) {
        List<Activity> list=partakeMapper.getActivity(openid);
        return ActivityFormat.format(list);
    }

    @Override
    public List<GatherActivity> getGather(String openid) {
        List<GatherActivity> list=partakeMapper.getGather(openid);
        return ActivityFormat.formatForGather(list);
    }
}
