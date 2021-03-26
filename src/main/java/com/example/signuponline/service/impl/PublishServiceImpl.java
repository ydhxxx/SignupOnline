package com.example.signuponline.service.impl;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.Group;
import com.example.signuponline.mapper.PublishMapper;
import com.example.signuponline.service.PublishService;
import com.example.signuponline.util.ActivityFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Activity> getMyActivity(String openid) {
        List<Activity> list=publishMapper.getMyActivity(openid);
        return ActivityFormat.format(list);
    }

    @Override
    public List<GatherActivity> getMyGather(String openid) {
        List<GatherActivity> list=publishMapper.getMyGather(openid);
        return ActivityFormat.formatForGather(list);
    }
}
