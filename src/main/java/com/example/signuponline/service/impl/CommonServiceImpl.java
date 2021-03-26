package com.example.signuponline.service.impl;

import com.example.signuponline.bean.*;
import com.example.signuponline.mapper.CommonMapper;
import com.example.signuponline.service.CommonService;
import com.example.signuponline.util.ActivityFormat;
import com.example.signuponline.util.TransGather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-10 21:21:06
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<Activity> getActivityForHome() {
        List<Activity> list=commonMapper.getActivityForHome();
        return ActivityFormat.format(list);
    }

    @Override
    public List<Activity> getActivityDetails(Integer id) {
        return commonMapper.getActivityDetails(id);
    }

    @Override
    public void browse(Integer id) {
        commonMapper.browse(id);
    }



    @Override
    public List<Activity> getClassifyActivity(String type1) {
        List<Activity> list=commonMapper.getClassifyActivity(type1);
        return ActivityFormat.format(list);
    }

    @Override
    public List<Activity> search(Map<String,Object> map) {
        commonMapper.addSearch(map);
        List<Activity> list=commonMapper.search(map);
        return ActivityFormat.format(list);
    }

    @Override
    public List<String> getHotSearch() {
        return commonMapper.getHotSearch();
    }

    @Override
    public List<String> getHistorySearch(String openid) {
        return commonMapper.getHistorySearch(openid);
    }

    @Override
    public int clearHistory(String openid) {
        return commonMapper.clearHistory(openid);
    }

    @Override
    public boolean createGroup(Map<String, String> map) {
        return commonMapper.createGroup(map);
    }

    @Override
    public boolean joinGroup(Map<String, String> map) {
        return commonMapper.joinGroup(map);
    }

    @Override
    public Group queryGroup(String id) {
        return commonMapper.queryGroup(id);
    }

    @Override
    public List<Group> getGroupByMember(String openid) {
        return commonMapper.getGroupByMember(openid);
    }

    @Override
    public List<Object> getActivityByGroup(String groupId) {
        return commonMapper.getActivityByGroup(groupId);
    }

    @Override
    public boolean exitGroup(Map<String, String> map) {
        return commonMapper.exitGroup(map);
    }

    @Override
    public List<Object> getCarousel() {
        return commonMapper.getCarousel();
    }

    @Override
    public List<Object> getGatherByGroup(String groupId) {
        return commonMapper.getGatherByGroup(groupId);
    }

    @Override
    public Map<String,Object> getGatherDetails(String id) {
        GatherActivity gatherActivity=commonMapper.getGatherDetails(id);
        List<GatherField> listField=commonMapper.getGatherDetailsField(id);
        Map<String,Object> map=new HashMap<>();
        return TransGather.transGather(map,gatherActivity,listField);
    }


}
