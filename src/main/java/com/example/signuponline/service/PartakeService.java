package com.example.signuponline.service;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.UserFlag;

import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-21 14:27:00
 */
public interface PartakeService {



    /**
     * 收藏或者取消收藏
     * @param map 存储openid 活动id 收藏或取消收藏标志
     * @return bool
     */
    boolean collect(Map<String, Object> map);

    /**
     * 获取收藏列表
     * @param openid openid
     * @return list返回活动列表
     */
    List<Activity> getCollect(String openid);

    /**
     * 普通活动报名
     * @param map openid、activityId
     * @return bool
     */
    boolean generalPartake(Map<String, Object> map);

    /**
     * 信息收集活动报名
     * @param map 活动id、openid以及表单信息
     * @return bool
     */
    boolean partakeGather(Map<String, Object> map);

    /**
     * 查询用户是否收藏和报名该活动
     * @param openid openid
     * @param activityId 群组id
     * @return userFlag
     */
    UserFlag getUserFlag(String openid, Integer activityId);

    /**
     * 查询用户是否报名该信息收集活动
     * @param openid openid
     * @param gatherId 群组id
     * @return userFlag
     */
    UserFlag getGatherFlag(String openid, String gatherId);

    /**
     * 查询用户参与的普通活动
     * @param openid openid
     * @return list
     */
    List<Activity> getActivity(String openid);

    /**
     * 查询用户参与的信息收集活动
     * @param openid openid
     * @return list
     */
    List<GatherActivity> getGather(String openid);
}
